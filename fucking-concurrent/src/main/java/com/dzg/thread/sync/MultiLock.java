package com.dzg.thread.sync;

public class MultiLock {

    public static void main(String[] args) {
        BigRoom room = new BigRoom();
//        Test a = new Test();
        new Thread(() -> {
            room.sleep();
        }, "t1").start();

        new Thread(() -> {
            room.study();
        }, "t2").start();
    }
}

class BigRoom {
    //细粒度锁的划分
    private final Object studyRoom = new Object();
    private final Object sleepRoom = new Object();

    public void sleep() {
        synchronized (sleepRoom) {
            System.out.println("sleeping 2 小时");
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void study() {
        synchronized (studyRoom) {
            System.out.println("study 1 小时");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

class Test{
    Test a = new Test();
}