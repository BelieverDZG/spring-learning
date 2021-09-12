package com.dzg.practice;

import java.util.concurrent.locks.LockSupport;

public class PrintOneTwoByLockSupport {

    /**
     * 使用LockSupport来先打印2后打印1
     */
    public static void test() {
        Thread t1 = new Thread(() -> {
            LockSupport.park();
            System.out.println(1);
        }, "t1");

        Thread t2 = new Thread(() -> {
            System.out.println(2);
            LockSupport.unpark(t1);
        }, "t2");

        t1.start();
        t2.start();
    }


    public static void main(String[] args) {
        test();
    }
}
