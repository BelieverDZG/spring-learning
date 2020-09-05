package com.dzg.practice;

import static java.lang.Thread.sleep;

/**
 * 烧水泡茶
 */
public class DrinkTea {

    public static void main(String[] args) {
        Thread t1 = new Thread(()->{
            try {
                System.out.println("洗水壶");
                sleep(1);
                System.out.println("烧开水");
                sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"老王");

        Thread t2 = new Thread(() ->{
            try {
                System.out.println("洗茶壶");
                sleep(1);
                System.out.println("洗茶杯");
                sleep(2);
                System.out.println("拿茶叶");
                sleep(1);

                t1.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() +" 可以泡茶了");
        },"老李");

        t1.start();
        t2.start();
    }

}
