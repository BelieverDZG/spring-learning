package com.dzg.future;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;

/***
 * Future基本原理
 * 实现一个异步的基于事件回调的案例
 */
public class FutureInActionOfficial2 {

    public static void main(String[] args) {
        Future future = invoke(()->{
            try {
                Thread.sleep(5000);
                return "I am finished";
            } catch (InterruptedException e) {
                return "ERROR";
            }
        });

        future.setCompletable(new Completable<String>() {
            @Override
            public void complete(String string) {
                System.out.println(string);
            }

            @Override
            public void exception(Throwable cause) {
                System.out.println("ERROR");
                cause.printStackTrace();
            }
        });
        System.out.println("........todo.......");
        System.out.println(future.get());//null?
    }

    private static <T> T block(Callable<T> callable) {
        return callable.action();
    }

    private static <T> Future<T> invoke(Callable<T> callable) {

        AtomicReference<T> reference = new AtomicReference<>();
        AtomicBoolean finished = new AtomicBoolean(false);
        Future<T> future = new FutureInActionOfficial2.Future<T>() {
            private Completable<T> completable;

            @Override
            public T get() {
                return reference.get();
            }

            @Override
            public boolean isDone() {
                return finished.get();
            }

            @Override
            public void setCompletable(Completable<T> completable) {
                this.completable = completable;
            }

            @Override
            public Completable<T> getCompletable() {
                return completable;
            }
        };
        Thread t = new Thread(() -> {
            try {
                T value = callable.action();
                reference.set(value);
                finished.set(true);
                if (future.getCompletable() != null) {
                    future.getCompletable().complete(value);
                }
            } catch (Throwable e) {
                if (future.getCompletable() != null) {
                    future.getCompletable().exception(e);
                }
            }
        });
        t.start();
        return future;
    }

    private interface Future<T> {

        T get();

        boolean isDone();

        void setCompletable(Completable<T> completable);

        Completable<T> getCompletable();
    }

    private interface Callable<T> {
        T action();
    }

    private interface Completable<T> {

        void complete(T t);

        void exception(Throwable cause);
    }
}
