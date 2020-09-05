package com.dzg.future.complete;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

/**
 *CompletableFuture 接口方法试验
 */
public class CompletableFutureInAction4 {

    public static void main(String[] args) throws InterruptedException {

       /* CompletableFuture.supplyAsync(()->1)
                .thenApply(i->Integer.sum(i,10))
                .whenComplete((v,e)->{
                    Optional.of(v).ifPresent(System.out::println);
                });//11*/

        /*CompletableFuture.supplyAsync(() -> 2)
                .handle((v, e) -> Integer.sum(v, 10))
                .whenComplete((v, t) -> {
                    Optional.of(v).ifPresent(System.out::println);
                })
                .thenRun(() -> {
                    System.out.println("thenRun todo here...");
                });*/

       /* CompletableFuture.supplyAsync(()->3)
                .thenApply(i->Integer.sum(i,10))
                .thenAccept(System.out::println);*/

      /*  CompletableFuture.supplyAsync(() -> 4)
                .thenCompose(i->CompletableFuture.supplyAsync(()->i*10))
                .thenAccept(System.out::println);*/

        /*CompletableFuture.supplyAsync(() -> 5)
                .thenCombine(CompletableFuture.supplyAsync(() -> 10.0d), (r1, r2) -> r1 * r2)
                .thenAccept(System.out::println);*/

        CompletableFuture.supplyAsync(() -> 6)
                .thenAcceptBoth(CompletableFuture.supplyAsync(() -> 2.0d), (a, b) -> {
                    System.out.println(a);//6
                    System.out.println(b);//2.0
                    System.out.println(a + b);//8.0
                });

        Thread.sleep(1000L);
    }
}