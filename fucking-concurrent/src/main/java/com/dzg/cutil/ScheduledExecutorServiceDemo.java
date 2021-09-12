package com.dzg.cutil;

import lombok.extern.slf4j.Slf4j;

import java.time.DayOfWeek;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@Slf4j(topic = "c.ScheduledExecutorServiceDemo")
public class ScheduledExecutorServiceDemo {

    public static void main(String[] args) {
        //任务调度线程池
        ScheduledExecutorService pool = Executors.newScheduledThreadPool(2);

//        m1(pool);
//        scheduled(pool);
        //当前时间
        LocalDateTime now = LocalDateTime.now();
        //获取周四时间
        LocalDateTime time = now.withHour(18).withMinute(0).
                withSecond(0).withNano(0).with(DayOfWeek.THURSDAY);
        if (now.compareTo(time) > 0) {
            time = time.plusWeeks(1);
        }

        long initialDelay = Duration.between(now, time).toMillis();
        //每周的时间间隔
        long period = 1000 * 60 * 60 * 24 * 7;
        pool.scheduleAtFixedRate(() -> {
        }, initialDelay, period, TimeUnit.MILLISECONDS);
    }

    private static void scheduled(ScheduledExecutorService pool) {
        //2秒执行一次
        log.debug("start...");
        pool.scheduleAtFixedRate(() -> {
            log.debug("running...");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, 1, 1, TimeUnit.SECONDS);

        //3秒执行一次
        pool.scheduleWithFixedDelay(() -> {
            log.debug("running...");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, 1, 1, TimeUnit.SECONDS);
    }

    private static void m1(ScheduledExecutorService pool) {
        pool.schedule(() -> {
            log.debug("task1");
            int i = 1 / 0;
        }, 1, TimeUnit.SECONDS);

        pool.schedule(() -> {
            log.debug("task2");
        }, 1, TimeUnit.SECONDS);
    }


}
