package com.dzg.thread.sync;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

/**
 * 线程八锁问题二 观察synchronized锁的对象是谁
 */
@Slf4j(topic = "c.ThreadEightLockTwo")
public class ThreadEightLockTwo {

    public static void main(String[] args) {
//        Number4 num = new Number4();
//        new Thread(Number5::b).start();
//        new Thread(Number5::a).start();
//        Number6.test();
        Number7 n1 = new Number7();
        Number7 n2 = new Number7();
        new Thread(() -> {
            n1.a();
        }).start();
        new Thread(() -> {
            n2.b();
        }).start();

        log.debug("start...");
    }
}

/**
 * 情况5：2 1s 后 1
 */
@Slf4j(topic = "c.Number4")
class Number4 {
    /**
     * 锁的是当前整个类对象
     */
    @SneakyThrows
    public static synchronized void a() {
        Thread.sleep(1000);
        log.debug("1");
    }

    public synchronized void b() {
        log.debug("2");
    }
}

/**
 * 情况6：1s 后12， 或 2 1s后 1
 */
@Slf4j(topic = "c.Number5")
class Number5 {
    /**
     * 锁的是当前整个类对象
     */
    @SneakyThrows
    public static synchronized void a() {
        Thread.sleep(1000);
        log.debug("1");
    }

    public static synchronized void b() {
        log.debug("2");
    }
}

/**
 * 情况7：2 1s 后 1
 */
@Slf4j(topic = "c.Number6")
class Number6 {

    @SneakyThrows
    public static synchronized void a() {
        Thread.sleep(1000);
        log.debug("1");
    }

    public synchronized void b() {
        log.debug("2");
    }

    public static void test() {
        Number6 n1 = new Number6();
        Number6 n2 = new Number6();
        new Thread(() -> {
            n1.a();
        }).start();
        new Thread(() -> {
            n2.b();
        }).start();
    }
}

/**
 * 情况8：1s 后12， 或 2 1s后 1
 */
@Slf4j(topic = "c.Number7")
class Number7 {

    @SneakyThrows
    public static synchronized void a() {
        Thread.sleep(1000);
        log.debug("1");
    }

    public static synchronized void b() {
        log.debug("2");
    }

    public static void test() {
        Number7 n1 = new Number7();
        Number7 n2 = new Number7();
        new Thread(() -> {
            n1.a();
        }).start();

        new Thread(() -> {
            n2.b();
        }).start();
    }
}