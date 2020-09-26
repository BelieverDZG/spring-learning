package com.dzg.cutil;


import lombok.extern.slf4j.Slf4j;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashSet;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 自定义一个线程池
 */
@Slf4j(topic = "c.DefineThreadPool")
public class DefineThreadPool {
    public static void main(String[] args) {
        ThreadPool pool = new ThreadPool(1, 1000,
                TimeUnit.MILLISECONDS, 1, ((queue, task) -> {
            //死等策略
            queue.put(task);
            //2.带超时等待
//            queue.offer(task,1500,TimeUnit.MILLISECONDS);
            // 3.让调用者自己放弃任务执行,什么也不做，就等价于放弃了执行任务

            //4.让调用者抛出异常
//            throw new RuntimeException("任务执行失败" + task);
            // 5.让调用者自己执行任务
            task.run();
        }));
        for (int i = 0; i < 3; i++) {
            int j = i;
            pool.execute(() -> {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                log.debug("{}", j);
            });
        }
    }
}

@FunctionalInterface
interface RejectPolicy<T> {
    void reject(BlockingQueue<T> queue, T task);
}

@Slf4j(topic = "c.ThreadPool")
class ThreadPool {
    //任务队列
    private BlockingQueue<Runnable> taskQueue;

    //线程集合
    private HashSet<Worker> workers = new HashSet<>();

    //核心线程数
    private int coreSize;

    //获取任务的超时时间
    private long timeout;

    private TimeUnit timeUnit;

    private RejectPolicy<Runnable> rejectPolicy;

    public ThreadPool(int coreSize, long timeout, TimeUnit timeUnit, int queueCapacity) {

        this.coreSize = coreSize;
        this.timeout = timeout;
        this.timeUnit = timeUnit;
        taskQueue = new BlockingQueue<>(queueCapacity);
    }

    public ThreadPool(int coreSize, long timeout, TimeUnit timeUnit, int queueCapacity, RejectPolicy<Runnable> rejectPolicy) {

        this.coreSize = coreSize;
        this.timeout = timeout;
        this.timeUnit = timeUnit;
        taskQueue = new BlockingQueue<>(queueCapacity);
        this.rejectPolicy = rejectPolicy;
    }

    //执行任务
    public void execute(Runnable task) {
        //当任务数没有超过coresize时候，直接交给worker对象执行
        //当任务数超过coresize时候，加入任务队列暂存
        synchronized (workers) {
            if (workers.size() < coreSize) {
                Worker worker = new Worker(task);
                log.debug("新增worker{},{}", worker, task);
                workers.add(worker);
                worker.start();
            } else {
//                taskQueue.put(task);
                /**
                 * 任务队列满时，可以执行下述选择
                 * 1.死等
                 * 2.带超时等待
                 * 3.让调用者自己放弃任务执行
                 * 4.让调用者抛出异常
                 * 5.让调用者自己执行任务
                 */
                taskQueue.tryPut(rejectPolicy, task);
            }
        }

    }

    class Worker extends Thread {
        private Runnable task;

        public Worker(Runnable task) {
            this.task = task;
        }

        @Override
        public void run() {
            //执行任务
            //当task不为空，执行任务
            //当task执行完毕，从任务队列获取任务并执行
//            while (task != null || (task = taskQueue.take()) != null){ 死等
            //超时等待
            while (task != null || (task = taskQueue.poll(1000, TimeUnit.MILLISECONDS)) != null) {
                try {
                    log.debug("执行任务{}", task);
                    task.run();
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    task = null;
                }
            }
            //????
            synchronized (workers) {
                log.debug("worker 被移除 {}", this);
                workers.remove(this);
            }
        }
    }
}

@Slf4j(topic = "c.BlockingQueue")
class BlockingQueue<T> {
    //1.任务队列
    private Deque<T> queue = new ArrayDeque<>();

    //2.锁
    private ReentrantLock lock = new ReentrantLock();

    //3.生产者条件变量
    private Condition fullWaitSet = lock.newCondition();

    //4.消费者条件变量
    private Condition emptyWaitSet = lock.newCondition();

    //5.容量
    private int capacity;

    public BlockingQueue(int capacity) {
        this.capacity = capacity;
    }

    //超时阻塞获取
    public T poll(long time, TimeUnit unit) {
        lock.lock();
        try {
            //将time统一转换为纳秒
            long nanos = unit.toNanos(time);
            while (queue.isEmpty()) {
                try {
                    //返回的是剩余时间
                    if (nanos <= 0) {
                        return null;
                    }
                    nanos = emptyWaitSet.awaitNanos(nanos);
//                    emptyWaitSet.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            T t = queue.removeFirst();
            fullWaitSet.signal();
            return t;
        } finally {
            lock.unlock();
        }
    }

    //阻塞获取
    public T take() {
        lock.lock();
        try {
            while (queue.isEmpty()) {
                try {
                    emptyWaitSet.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            T t = queue.removeFirst();
            fullWaitSet.signal();
            return t;
        } finally {
            lock.unlock();
        }

    }

    //阻塞添加
    public void put(T task) {
        lock.lock();
        try {
            while (queue.size() == capacity) {
                try {
                    log.debug("等待加入任务队列 {} ...", task);
                    fullWaitSet.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            log.debug("加入任务队列 {}", task);
            queue.addLast(task);
            emptyWaitSet.signal();
        } finally {
            lock.unlock();
        }
    }

    //带超时时间阻塞添加
    public boolean offer(T task, long timeout, TimeUnit timeUnit) {
        lock.lock();
        try {
            long nanos = timeUnit.toNanos(timeout);
            while (queue.size() == capacity) {
                try {
                    if (nanos <= 0) {
                        return false;
                    }
                    log.debug("等待加入任务队列{}...", task);
                    nanos = fullWaitSet.awaitNanos(nanos);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            log.debug("加入任务队列{}...", task);
            queue.addLast(task);
            emptyWaitSet.signal();
            return true;
        } finally {
            lock.unlock();
        }
    }

    //队列大小
    public int getCapacity() {
        return capacity;
    }

    public void tryPut(RejectPolicy<T> rejectPolicy, T task) {
        lock.lock();
        try {
            //判断队列是否已满
            if (queue.size() == capacity) {
                rejectPolicy.reject(this, task);
            } else {
                log.debug("加入任务队列{}...", task);
                queue.addLast(task);
                emptyWaitSet.signal();
            }
        } finally {
            lock.unlock();
        }
    }
}