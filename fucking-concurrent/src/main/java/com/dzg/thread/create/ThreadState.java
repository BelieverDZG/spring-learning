package com.dzg.thread.create;

public class ThreadState {

    public static void main(String[] args) {
        //new
        Thread t1 = new Thread(() -> {

            System.out.println("t1....");
        });

        //runnable
        Thread t2 = new Thread(() -> {
            while (true){

            }
        });
        t2.start();

        //terminated 执行完就结束了
        Thread t3 = new Thread("t3"){
            @Override
            public void run() {
                System.out.println("t3.....");
            }
        };
        t3.start();

        //超时等待timed_waiting
        Thread t4 = new Thread("t4"){
            public void run() {
                synchronized (ThreadState.class){
                    System.out.println("t4.....");
                    try {
                        Thread.sleep(10000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };
        t4.start();

        //waiting 状态
        Thread t5 = new Thread("t5"){
            @Override
            public void run() {
                try {
                    //等待t2运行结束的结果后，才能继续向下运行
                    t2.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        t5.start();

        //t6处于阻塞状态，当前锁被t4占用中
        Thread t6 = new Thread("t6"){
            @Override
            public void run() {
                synchronized(ThreadState.class){
                    try {
                        Thread.sleep(10000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };
        t6.start();

        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("t1的状态："+t1.getState());
        System.out.println("t2的状态："+t2.getState());
        System.out.println("t3的状态："+t3.getState());
        System.out.println("t4的状态："+t4.getState());
        System.out.println("t5的状态："+t5.getState());
        System.out.println("t6的状态："+t6.getState());
    }
}
