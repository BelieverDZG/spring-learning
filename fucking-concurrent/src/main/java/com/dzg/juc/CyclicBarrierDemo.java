package com.dzg.juc;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Slf4j(topic = "c.CyclicBarrierDemo")
public class CyclicBarrierDemo {

    public static void main(String[] args) {
        //线程池的线程数和计数保持一致
        ExecutorService pool = Executors.newFixedThreadPool(2);

        CyclicBarrier barrier = new CyclicBarrier(2, () -> {
            log.debug("task1 task2 finished.");
        });
        for (int i = 0; i < 3; i++) {
            pool.submit(() -> {
                try {
                    log.debug("task1 start...");
                    Thread.sleep(1000);
                    barrier.await();
                } catch (InterruptedException | BrokenBarrierException e) {
                    e.printStackTrace();
                }
            });

            pool.submit(() -> {
                try {
                    log.debug("task2 start...");
                    Thread.sleep(2000);
                    barrier.await();
                } catch (InterruptedException | BrokenBarrierException e) {
                    e.printStackTrace();
                }
            });
        }

        pool.shutdown();
    }
}
