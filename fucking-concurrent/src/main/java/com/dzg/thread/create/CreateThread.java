package com.dzg.thread.create;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * 创建线程的三种方式
 */
public class CreateThread {

    public static void main(String[] args) {

        //线程创建方式1
        Thread t1 = new Thread(){
            @Override
            public void run() {
                System.out.println("t1...running...");
            }
        };
        t1.setName("t1");
        t1.start();


        //线程创建方式2
        Runnable r = new Runnable() {
            @Override
            public void run() {
                System.out.println("t2..running");
            }
        };
        Thread t2 = new Thread(r,"t2");
        t2.start();

        Thread t3 = new Thread(()->{
            System.out.println("lambda...");
        },"t3");
        t3.start();

        //线程创建方式3
        FutureTask<Integer> task = new FutureTask<>(new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                Thread.sleep(1000);
                return 100;
            }
        });

        try {
            Thread t4 = new Thread(task);
            t4.start();
            //主线程阻塞，等待结果的返回
            System.out.println(task.get());
        } catch (Exception e) {
            e.printStackTrace();
        }

        //两个线程交替执行
        new Thread(()->{
            while (true){
                System.out.println("aaaaaa");
            }
        }).start();

        new Thread(()->{
            while (true){
                System.out.println("bbbbb");
            }
        }).start();
    }


}
