package com.dzg.future.complete;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;

import static java.util.stream.Collectors.toList;


public class CompletableFutureInAction5 {

    public static void main(String[] args) throws InterruptedException {
        /*CompletableFuture.supplyAsync(() -> {
            System.out.println("one");
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return 1;
        }).runAfterBoth(CompletableFuture.supplyAsync(() -> {
            System.out.println("two");
            return 2;
        }), () -> {
            System.out.println("runnable action");
        });*/

        /*CompletableFuture.supplyAsync(() -> {
            System.out.println("I AM FUTURE ONE");
            return CompletableFutureInAction1.get();
        }).applyToEither(CompletableFuture.supplyAsync(() -> {
            System.out.println("FUTURE TWO");
            return CompletableFutureInAction1.get();
        }), i -> 10 * i).thenAccept(System.out::println);*/

        /*CompletableFuture.supplyAsync(() -> {
            System.out.println("I AM acceptEither ONE");
            return CompletableFutureInAction1.get();
        }).acceptEither(CompletableFuture.supplyAsync(() -> {
            System.out.println("acceptEither TWO");
            return CompletableFutureInAction1.get();
        }), System.out::println);*/

        /*CompletableFuture.supplyAsync(() -> {
            System.out.println("I AM runAfterEither ONE");
            return CompletableFutureInAction1.get();
        }).runAfterEither(CompletableFuture.supplyAsync(() -> {
            System.out.println("runAfterEither TWO");
            return CompletableFutureInAction1.get();
        }), ()->System.out.println("finished"));*/

        List<CompletableFuture<Double>> collect = Arrays.asList(1, 2, 3, 4)
                .stream()
                .map(i -> CompletableFuture.supplyAsync(CompletableFutureInAction1::get))
                .collect(toList());
        CompletableFuture.anyOf(collect.toArray(new CompletableFuture[collect.size()]))
                .thenRun(() -> System.out.println("FINISHED"));

        /*
        allOf():
        get :0.9023064789568177
        get :0.4972968152676286
        get :0.1822421914359421
        get :0.8407109172500183
        FINISHED
        * */

        /*
        anyOf():
        get :0.7976115021863147
        FINISHED
        get :0.35245356822330476
        get :0.18032617925290606
        get :0.8905121363958416
        * */
        Thread.currentThread().join();

    }

}