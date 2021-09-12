package com.dzg.thread.sync;

public class SynchronizedDemo {

    static int cnt = 0;

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(() ->{
            for (int i = 0; i < 5000; i++) {
                synchronized (SynchronizedDemo.class){
                    cnt++;
                }
            }
        });

        Thread t2 = new Thread(() ->{
            for (int i = 0; i < 5000; i++) {
                synchronized (SynchronizedDemo.class){
                    cnt--;
                }
            }
        });
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println(cnt);
    }
}
