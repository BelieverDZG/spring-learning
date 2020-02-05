package com.dzg.future.complete;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

/**
 * 流水线工作
 */
public class CompletableFutureInAction3 {

    public static void main(String[] args) throws InterruptedException {

        ExecutorService executor = Executors.newFixedThreadPool(2, t -> {
            Thread thread = new Thread(t);
            thread.setDaemon(false);
            return thread;
        });
        /*CompletableFuture.supplyAsync(CompletableFutureInAction1::get, executor)
                .thenApply(CompletableFutureInAction3::multiply)
                .whenComplete((a,b)->{
                    Optional.ofNullable(a).ifPresent(System.out::println);
                    Optional.ofNullable(b).ifPresent(x->x.printStackTrace());
                });//后台进程会一直运行，除非关闭线程
//        executor.shutdown();*/
        List<Integer> listIDs = Arrays.asList(1, 2, 3);
        Optional.of(listIDs.stream()
                .map(integer -> CompletableFuture.supplyAsync(() -> queryProduction(integer), executor))
                .map(future -> future.thenApply(CompletableFutureInAction3::multiply))
                .map(CompletableFuture::join)
                .collect(Collectors.toList()))
                .ifPresent(System.out::println);
        executor.shutdown();

    }

    public static double multiply(double value) {
        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return value * 10;
    }

    public static double queryProduction(int i) {
        return CompletableFutureInAction1.get();
    }
}
