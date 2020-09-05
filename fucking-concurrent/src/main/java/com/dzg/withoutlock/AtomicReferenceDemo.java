package com.dzg.withoutlock;

import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.atomic.AtomicStampedReference;

public class AtomicReferenceDemo {

    //ABA问题
//    static AtomicReference<String> ref = new AtomicReference<>("A");

    //加一个版本号，避免ABA问题
    static AtomicStampedReference<String> ref = new AtomicStampedReference<>("A", 0);

    public static void main(String[] args) throws InterruptedException {
        System.out.println("main start...");
        // 获取值 A
        // 这个共享变量被它线程修改过？
        String prev = ref.getReference();
        int stamp = ref.getStamp();

        other();
        Thread.sleep(1000);
        // 尝试改为 C
        System.out.println("change A->C " + ref.compareAndSet(prev, "C", stamp, stamp + 1));
    }

    /**
     * 在线程T对共享变量进行读操作的之前，有其他的线程对变量进行了修改操作，并还原为原来的值
     * 但是T并没有察觉到变化，认为共享变量没有发生变化，仍然成功进行CAS操作
     *
     * @throws InterruptedException
     */
    private static void other() throws InterruptedException {

        new Thread(() -> {
            int stamp = ref.getStamp();
            System.out.println("change A->B " + ref.compareAndSet(ref.getReference(),
                    "B", stamp, stamp + 1));
        }, "t1").start();
        Thread.sleep(500);

        new Thread(() -> {
            int stamp = ref.getStamp();
            System.out.println("change B->A " + ref.compareAndSet(ref.getReference(), "A",
                    stamp, stamp + 1));
        }, "t2").start();
    }

    /*public static void main(String[] args) throws InterruptedException {
        System.out.println("main start...");
        // 获取值 A
        // 这个共享变量被它线程修改过？
        String prev = ref.get();
        other();
        Thread.sleep(1000);
        // 尝试改为 C
        System.out.println("change A->C " + ref.compareAndSet(prev, "C"));
    }

    *//**
     * 在线程T对共享变量进行读操作的之前，有其他的线程对变量进行了修改操作，并还原为原来的值
     * 但是T并没有察觉到变化，认为共享变量没有发生变化，仍然成功进行CAS操作
     *
     * @throws InterruptedException
     *//*
    private static void other() throws InterruptedException {

        new Thread(() -> {
            System.out.println("change A->B " + ref.compareAndSet(ref.get(), "B"));
        }, "t1").start();
        Thread.sleep(500);

        new Thread(() -> {
            System.out.println("change B->A " + ref.compareAndSet(ref.get(), "A"));
        }, "t2").start();
    }*/
}
