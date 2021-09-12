package com.dzg.thread.sync;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

/**
 * 线程八锁问题一 观察synchronized锁的对象是谁
 */
@Slf4j(topic = "c.ThreadEightLockOne")
public class ThreadEightLockOne {

    public static void main(String[] args) {
        Number3 num = new Number3();
        new Thread(num::a).start();
        new Thread(num::b).start();
        log.debug("start...");
    }
}

/**
 * 情况1：12 或 21
 */
@Slf4j(topic = "c.Number")
class Number {

    /**
     * 当前对象加锁
     * 等价于
     * <p>
     * public void a(){
     * synchronized(this){
     * <p>
     * }
     * }
     * </p>
     */
    public synchronized void a() {
        log.debug("1");
    }

    public synchronized void b() {
        log.debug("2");
    }
}

/**
 * 情况2：1s后12，或 2 1s后 1
 */
@Slf4j(topic = "c.Number1")
class Number1 {

    @SneakyThrows
    public synchronized void a() {
        Thread.sleep(1000);
        log.debug("1");
    }

    @SneakyThrows
    public synchronized void b() {
        Thread.sleep(2000);
        log.debug("2");
    }
}

/**
 * 3 1s 后12
 * 或23 1s后 1
 * 或 32 1s后 1
 */
@Slf4j(topic = "c.Number1")
class Number2 {

    @SneakyThrows
    public synchronized void a() {
        Thread.sleep(1000);
        log.debug("1");
    }

    public synchronized void b() {
        log.debug("2");
    }

    public void c() {
        log.debug("3");
    }
}

//情况4：2 1s 后 1
@Slf4j(topic = "c.Number3")
class Number3 {
    @SneakyThrows
    public synchronized void a() {
        Thread.sleep(1000);
        log.debug("1");
    }

    public synchronized void b() {
        log.debug("2");
    }
}