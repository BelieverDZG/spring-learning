package com.dzg.juc;

import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

@Slf4j(topic = "c.CountDownLatchDemo")
public class CountDownLatchDemo {

    public static void main(String[] args) throws InterruptedException {

        AtomicInteger num = new AtomicInteger(0);
        ExecutorService pool = Executors.newFixedThreadPool(10,r -> {
            return new Thread(r,"t"+num.getAndIncrement());
        });
        CountDownLatch latch = new CountDownLatch(10);
        Random r = new Random();
        String[] all = new String[10];
        for (int i = 0; i < 10; i++) {
            int k = i;
            pool.submit(() -> {
                for (int j = 0; j <=100 ; j++) {
                    try {
                        Thread.sleep(r.nextInt(100));
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    all[k] = Thread.currentThread().getName()+ "(" + j + "%" + ")";
                    System.out.print("\r" + Arrays.toString(all));
                }
            });
            latch.countDown();
        }
        latch.await();
        System.out.println("\n游戏开始");
        pool.shutdown();
    }

    /**
     * 搭配线程池的使用
     */
    private static void test2() {
        CountDownLatch latch = new CountDownLatch(3);
        ExecutorService pool = Executors.newFixedThreadPool(4);
        pool.submit(() -> {
            log.debug("begin...");
            try {
                Thread.sleep(1000);
                latch.countDown();
                log.debug("end...{}",latch.getCount());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        pool.submit(() -> {
            log.debug("begin...");
            try {
                Thread.sleep(2000);
                latch.countDown();
                log.debug("end...{}",latch.getCount());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        pool.submit(() -> {
            log.debug("begin...");
            try {
                Thread.sleep(1500);
                latch.countDown();
                log.debug("end...{}",latch.getCount());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        pool.submit(() -> {
            try {
                log.debug("waiting...");
                latch.await();
                log.debug("wait end...");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
    }

    private static void testLatch() throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(3);
        new Thread(() -> {
            log.debug("begin...");
            try {
                Thread.sleep(1000);
                latch.countDown();
                log.debug("end...{}",latch.getCount());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
        new Thread(() -> {
            log.debug("begin...");
            try {
                Thread.sleep(3000);
                latch.countDown();
                log.debug("end...{}",latch.getCount());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
        new Thread(() -> {
            log.debug("begin...");
            try {
                Thread.sleep(1500);
                latch.countDown();
                log.debug("end...{}",latch.getCount());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        log.debug("waiting...");
        latch.await();
        log.debug("wait end...");
    }
}
