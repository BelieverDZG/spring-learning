package com.dzg.practice;

import java.util.concurrent.locks.LockSupport;

public class PrintABCByParkUnpark {

    //用于ParkUnPark测试
    static Thread t1, t2, t3;

    public static void main(String[] args) {
        ParkUnPark parkUnPark = new ParkUnPark(5);
        t1 = new Thread(() -> parkUnPark.print("a", t2));
        t2 = new Thread(() -> parkUnPark.print("b", t3));
        t3 = new Thread(() -> parkUnPark.print("c", t1));
        t1.start();
        t2.start();
        t3.start();
        try {
            Thread.sleep(1000);
            LockSupport.unpark(t1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}



class ParkUnPark {

    private int loopNumber;

    public ParkUnPark(int loopNumber) {
        this.loopNumber = loopNumber;
    }

    public void print(String str, Thread next) {
        for (int i = 0; i < loopNumber; i++) {
            LockSupport.park();
            System.out.print(str);
            LockSupport.unpark(next);
        }
    }
}