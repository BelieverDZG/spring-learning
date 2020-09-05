package com.dzg.future.complete;

import java.util.Optional;
import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CompletableFutureInAction2 {

    public static void main(String[] args) throws InterruptedException {

        ExecutorService executor = Executors.newFixedThreadPool(2,t->{
            Thread thread = new Thread(t);
            thread.setDaemon(false);
            return thread;
        });
        CompletableFuture.supplyAsync(CompletableFutureInAction1::get,executor)
                .whenComplete((v, t) -> {
                    Optional.ofNullable(v).ifPresent(System.out::println);
                    Optional.ofNullable(t).ifPresent(x -> x.printStackTrace());
                });
        System.out.println("I AM NO BLOCK");
//        Thread.currentThread().join();//join之后，运行程序才有输出数值

        executor.shutdown();
    }

}
