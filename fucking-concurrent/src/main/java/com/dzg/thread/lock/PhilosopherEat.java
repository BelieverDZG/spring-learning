package com.dzg.thread.lock;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.locks.ReentrantLock;

/**
 * 哲学家就餐问题，导致死锁的现象
 */
public class PhilosopherEat {

    private static Logger logger = LoggerFactory.getLogger(PhilosopherEat.class);

    public static void main(String[] args) {
        Chopstick c1 = new Chopstick("1");
        Chopstick c2 = new Chopstick("2");
        Chopstick c3 = new Chopstick("3");
        Chopstick c4 = new Chopstick("4");
        Chopstick c5 = new Chopstick("5");
        new Philosopher("苏格拉底", c1, c2).start();
        new Philosopher("柏拉图", c2, c3).start();
        new Philosopher("亚里士多德", c3, c4).start();
        new Philosopher("赫拉克利特", c4, c5).start();
        //顺序加锁，导致饥饿现象
//        new Philosopher("阿基米德", c1, c5).start();
        new Philosopher("阿基米德", c5, c1).start();
    }
}

/**
 * 把筷子当成锁对象
 */
class Chopstick extends ReentrantLock {
    String name;

    public Chopstick(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "筷子{" + name + '}';
    }
}

class Philosopher extends Thread {

    private static Logger logger = LoggerFactory.getLogger(Philosopher.class);

    Chopstick left;
    Chopstick right;

    public Philosopher(String name, Chopstick left, Chopstick right) {
        super(name);
        this.left = left;
        this.right = right;
    }

    @Override
    public void run() {
        while (true) {
            //先获取左手边的筷子
            if (left.tryLock()){
                try {
                    //尝试获取右手边的筷子
                    if (right.tryLock()){
                        try {
                            eat();
                        }finally {
                            right.unlock();
                        }
                    }
                }finally {
                    left.unlock();
                }
            }
        }
    }

    private void eat() {
//        System.out.println("eating...");
        logger.info("eating....");
        try {
            sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}