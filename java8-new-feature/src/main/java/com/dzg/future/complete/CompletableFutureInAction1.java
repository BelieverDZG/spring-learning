package com.dzg.future.complete;

import java.util.Optional;
import java.util.Random;
import java.util.concurrent.CompletableFuture;

public class CompletableFutureInAction1 {

    public static final Random RANDOM = new Random(System.currentTimeMillis());

    public static void main(String[] args) {
        //提供异步支持
        CompletableFuture<Double> future = new CompletableFuture<>();
        new Thread(() -> {
            double value = get();
            future.complete(value);
        }).start();
        System.out.println("....没有阻塞....");
        future.whenComplete((v, t) -> {
            Optional.ofNullable(v).ifPresent(System.out::println);
            Optional.ofNullable(t).ifPresent(x -> x.printStackTrace());
        });
    }

    static double get() {
        try {
            Thread.sleep(RANDOM.nextInt(10000));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        double res = RANDOM.nextDouble();
        System.out.println("get :"+res);
        return res;
    }
}
