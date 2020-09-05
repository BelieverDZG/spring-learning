package com.dzg.practice;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class PrintOneTwo {

    static final Object lock = new Object();

    static boolean t2True = false;

    public static void main(String[] args) {
        /*Thread t1 = new Thread(() -> {
            synchronized (lock) {
                while (!t2True) {
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
            System.out.println(1);
        }, "t1");

        Thread t2 = new Thread(() -> {
            synchronized (lock) {
                System.out.println(2);
                t2True = true;
                lock.notify();
            }
        }, "t2");

        t1.start();
        t2.start();*/
        Print p = new Print(1);
        Condition odd = p.newCondition();
        Condition even = p.newCondition();
        new Thread(() ->
                p.print(1, odd, even), "t1").start();

        new Thread(() ->
                p.print(2, even, odd), "t2").start();

        try {
            Thread.sleep(1000);
            p.lock();
            even.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            p.unlock();
        }
    }
}

class Print extends ReentrantLock {

    private int loopNumber;

    public Print(int loopNumber) {
        this.loopNumber = loopNumber;
    }

    public void print(int num, Condition curr, Condition next) {
        for (int i = 0; i < loopNumber; i++) {
            lock();
            try {
                curr.await();
                System.out.println(num);
                next.signal();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                unlock();
            }
        }
    }
}

