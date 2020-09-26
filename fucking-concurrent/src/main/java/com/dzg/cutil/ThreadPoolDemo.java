package com.dzg.cutil;

import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;

@Slf4j(topic = "c.ThreadPoolDemo")
public class ThreadPoolDemo {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService pool = Executors.newFixedThreadPool(2);
        //submit(pool);
//        invokeAll(pool);
        invokeAny(pool);
    }

    private static void invokeAny(ExecutorService pool) throws InterruptedException, ExecutionException {
        String str = pool.invokeAny(Arrays.asList(
                () -> {
                    log.debug("begin..1");
                    Thread.sleep(1000);
                    log.debug("end..1");
                    return "1";
                },
                () -> {
                    log.debug("begin...2");
                    Thread.sleep(1000);
                    log.debug("end..2");
                    return "2";
                },
                () -> {
                    log.debug("begin...3");
                    Thread.sleep(1000);
                    log.debug("end..3" +
                            "");
                    return "3";
                }
        ));

        log.debug("invokeAny : {}", str);
    }


    private static void invokeAll(ExecutorService pool) throws InterruptedException {
        List<Future<String>> futures = pool.invokeAll(Arrays.asList(
                () -> {
                    log.debug("begin...");
                    Thread.sleep(1000);
                    return "1";
                },
                () -> {
                    log.debug("begin...");
                    Thread.sleep(500);
                    return "2";
                },
                () -> {
                    log.debug("begin...");
                    Thread.sleep(2000);
                    return "3";
                }
        ));

        futures.forEach(stringFuture -> {
            try {
                log.debug("{}", stringFuture.get());
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        });
    }

    private static void submit(ExecutorService executorService) throws InterruptedException, ExecutionException {
        Future<String> future = executorService.submit(() -> {
            log.debug("running....");
            Thread.sleep(1000);
            return "ok";
        });

        log.debug("执行结果{}", future.get());
    }
}
