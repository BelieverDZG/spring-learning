package com.dzg.future;

import java.util.concurrent.Callable;
import java.util.concurrent.Future;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;

/***
 * Future基本原理
 */
public class FutureInAction {

    public static void main(String[] args) {
        /*Future<String> future = invoke(()->{
            try {
                Thread.sleep(10000);
                return "I AM FINISHED";
            } catch (InterruptedException e) {
                return "ERROR";
            }
        });
        //没有得到值，但是结果立马得到了返回
        *//*
         * 可在此时做其他的事情
         * *//*
        System.out.println(future.get()); //null

        //十秒之后得到返回值 I AM FINISHED
        while (!future.isDone()){
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println(future.get());//I AM FINISHED*/


        /*
        模拟线程阻塞的实现，只有等待返回值后，当前线程或者进程才能进行其他的操作
        * */
        String value = block(() -> {
            try {
                Thread.sleep(5000);
                return "I have been waited 5 seconds";
            } catch (InterruptedException e) {
                return "ERROR";
            }
        });
        System.out.println(value);//I have been waited 5 seconds
    }

    private static <T> T block(Callable<T> callable) {
        return callable.action();
    }

    /**
     * 线程异步实现？
     *
     * @param callable
     * @param <T>
     * @return
     */
    private static <T> Future<T> invoke(Callable<T> callable) {

        AtomicReference<T> reference = new AtomicReference<>();
        AtomicBoolean finished = new AtomicBoolean(false);
        Thread t = new Thread(() -> {
            T value = callable.action();
            reference.set(value);
            finished.set(true);
        });
        t.start();

        Future<T> future = new FutureInAction.Future<T>() {
            @Override
            public T get() {
                return reference.get();
            }

            @Override
            public boolean isDone() {
                return finished.get();
            }
        };
        return future;
    }

    private interface Future<T> {

        T get();

        boolean isDone();
    }

    private interface Callable<T> {
        T action();
    }

}
