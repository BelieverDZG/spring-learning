package com.dzg.practice;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class PrintOneTwoByReentrantLock {

    static ReentrantLock lock = new ReentrantLock();

    static Condition condition1 = lock.newCondition();
    static Condition condition2 = lock.newCondition();

    static boolean isPrint = false;

    public static void main(String[] args) {
        Thread t1 = new Thread(() -> {
            while (isPrint) {
                lock.unlock();
                try {
                    condition1.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    lock.unlock();
                }
                System.out.println(1);
            }
        }, "t1");

        Thread t2 = new Thread(() -> {
            lock.lock();
            try {
                isPrint = true;
                System.out.println(2);
            } finally {
                lock.unlock();
            }
        }, "t2");

        t1.start();
        t2.start();
    }
}

