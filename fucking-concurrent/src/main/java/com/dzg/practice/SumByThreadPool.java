package com.dzg.practice;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * 求取1-10000的和
 *
 * 使用线程池的的future模式获取线程的计算结果
 * @author BelieverDzg
 * @date 2021/5/27 16:00
 */
public class SumByThreadPool {

    public static void main(String[] args) {
        int sum = 0;
        int threadNum = 5;
        int count = 2000;
//        ExecutorService fixedThreadPool = Executors.newFixedThreadPool(10);
        ThreadPoolExecutor fixedThreadPool = new ThreadPoolExecutor(
                5,
                10,
                1,
                TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<>());

        List<Future<?>> futures = new ArrayList<>();
        for (int i = 1; i <= threadNum ; i++) {
            AddOperationPool addPool = new AddOperationPool((i-1)*count + 1 , i * count);
            Future<?> submit = fixedThreadPool.submit(addPool);
            futures.add(submit);
        }
        if (!futures.isEmpty()){
            for (Future<?> f : futures){
                try {
                    Integer res = (Integer) f.get();
                    sum += res;
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        System.out.println(sum);
        fixedThreadPool.shutdown();
    }
}

class AddOperationPool implements Callable<Integer>{

    private int start;
    private int end;
    private int sum;

    public AddOperationPool(int start, int end) {
        this.start = start;
        this.end = end;
    }

    @Override
    public Integer call() throws Exception {
        for (int i = start; i <= end ; i++) {
            sum += i;
        }
        return sum;
    }
}
