package com.dzg.juc;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

@Slf4j(topic = "c.UnReentrantLock")
public class UnReentrantLock {
    public static void main(String[] args) {
        MyLock lock = new MyLock();
        new Thread(() -> {
            lock.lock();
            log.debug("12233...");
            lock.lock();
            try {
                log.debug("locking...");
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                log.debug("unlock");
                lock.unlock();
            }

        }, "t1").start();

        new Thread(() -> {
            lock.lock();
            try {
                log.debug("locking...");
            } finally {
                log.debug("unlock");
                lock.unlock();
            }
        }, "t2").start();

    }
}

//自定义锁，不可重入锁
class MyLock implements Lock {

    //自定义同步器类
    class MySync extends AbstractQueuedSynchronizer {
        @Override
        protected boolean tryAcquire(int arg) {
            if (compareAndSetState(0, 1)) {
                //加上锁了,设置owner为当前线程
                setExclusiveOwnerThread(Thread.currentThread());
                return true;
            }
            return false;
        }

        @Override
        protected boolean tryRelease(int arg) {
            setExclusiveOwnerThread(null);
            setState(0);
            return true;
        }

        @Override
        protected boolean isHeldExclusively() {
            return getState() == 1;
        }

        public Condition newCondition() {
            return new ConditionObject();
        }

    }

    private MySync sync = new MySync();

    @Override // 加锁 ，不成功，则进入阻塞队列等待
    public void lock() {
        sync.acquire(1);
    }

    @Override // 可打断的加锁
    public void lockInterruptibly() throws InterruptedException {
        sync.acquireInterruptibly(1);
    }

    //尝试加锁 一次
    @Override
    public boolean tryLock() {
        return sync.tryAcquire(1);
    }

    // 尝试加锁，带超时的
    @Override
    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
        return sync.tryAcquireNanos(1, unit.toNanos(time));
    }

    //解锁
    @Override
    public void unlock() {
        sync.release(0);
    }

    //创建一个条件变量
    @Override
    public Condition newCondition() {
        return sync.newCondition();
    }
}
