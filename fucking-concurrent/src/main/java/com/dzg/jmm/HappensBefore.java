package com.dzg.jmm;

public class HappensBefore {

    //1.线程解锁 m 之前对变量的写，对于接下来对 m 加锁的其它线程对该变量的读可见
    static int x;
    static Object m = new Object();

    public static void main(String[] args) {
        new Thread(() -> {
            synchronized (m) {
                x = 10;
            }
        }, "t1").start();
        new Thread(() -> {
            synchronized (m) {
                System.out.println(x);
            }
        }, "t2").start();

        Integer a = 129;
        Integer b = 129;
        System.out.println(a == b);
        System.out.println(a.equals(b));
    }

    //线程对 volatile 变量的写，对接下来其它线程对该变量的读可见
    volatile static int x2;

    public static void m2() {

        new Thread(() -> {
            x2 = 10;
        }, "t1").start();
        new Thread(() -> {
            System.out.println(x2);
        }, "t2").start();
    }

    //3.线程 start 前对变量的写，对该线程开始后对该变量的读可见
    static int x3;

    public static void m3() {

        x3 = 10;
        new Thread(() -> {
            System.out.println(x3);
        }, "t2").start();
    }

    //4线程结束前对变量的写，对其它线程得知它结束后的读可见（比如其它线程调用 t1.isAlive() 或 t1.join()等待
    //它结束）
    static int x4;

    public static void m4() throws InterruptedException {
        Thread t1 = new Thread(() -> {
            x4 = 10;
        }, "t1");
        t1.start();
        t1.join();
        System.out.println(x4);
    }

    //5.线程 t1 打断 t2（interrupt）前对变量的写，对于其他线程得知 t2 被打断后对变量的读可见（通过
    //t2.interrupted 或 t2.isInterrupted）

    //6.对变量默认值（0，false，null）的写，对其它线程对该变量的读可见

    //7.具有传递性，如果 x hb-> y 并且 y hb-> z 那么有 x hb-> z ，配合 volatile 的防指令重排，有下面的例子
}
