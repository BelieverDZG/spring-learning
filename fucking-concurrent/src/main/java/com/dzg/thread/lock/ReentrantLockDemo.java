package com.dzg.thread.lock;

import com.dzg.cutil.LogUtil;
import org.slf4j.Logger;

import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockDemo {

    private static ReentrantLock lock = new ReentrantLock();
    private static final Logger logger = LogUtil.getLogger(ReentrantLock.class);
    public static void main(String[] args) {
//        lock.lock();
//        try{
//            System.out.println("enter main");
//            m1();
//        }finally {
//            lock.unlock();
//        }

        Thread t1 = new Thread(() -> {
            try {
//                System.out.println("尝试获得锁");
                logger.info("尝试获得锁");
                lock.lockInterruptibly();
            } catch (InterruptedException e) {
                e.printStackTrace();
                System.out.println("没有获得锁");
                return;
            }
            try {
                System.out.println("获取到锁");
            }finally {
                lock.unlock();
            }
        },"t1");

        lock.lock();
        t1.start();
        try {
            Thread.sleep(1000);
//            System.out.println("1秒后打断t1");
            logger.info("1秒后打断t1");
            t1.interrupt();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    private static void m1() {
        lock.lock();
        try{
            System.out.println("enter m1");
            m2();
        }finally {
            lock.unlock();
        }
    }

    private static void m2() {
        lock.lock();
        try{
            System.out.println("enter m2");
        }finally {
            lock.unlock();
        }
    }


}
