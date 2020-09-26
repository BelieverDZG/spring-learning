package com.dzg.juc;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.locks.StampedLock;

public class StampedLockDemo {

    public static void main(String[] args) throws InterruptedException {
        DataContainerStamped stamped = new DataContainerStamped(1);
        new Thread(() -> {
            stamped.read(1);
        },"t1").start();
        Thread.sleep(500);
        new Thread(() -> {
            stamped.write(1000);
        },"t2").start();
    }

}

@Slf4j(topic = "c.DataContainerStamped")
class DataContainerStamped {
    private int data;

    private final StampedLock lock = new StampedLock();
    public DataContainerStamped(int data) {
        this.data = data;
    }
    public int read(int readTime) {
        long stamp = lock.tryOptimisticRead();
        log.debug("optimistic read locking...{}", stamp);
        try {
            Thread.sleep(readTime);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if (lock.validate(stamp)) {
            log.debug("read finish...{}, data:{}", stamp, data);
            return data;
        }
// 锁升级 - 读锁
        log.debug("updating to read lock... {}", stamp);
        try {
            stamp = lock.readLock();
            log.debug("read lock {}", stamp);
            try {
                Thread.sleep(readTime);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            log.debug("read finish...{}, data:{}", stamp, data);
            return data;
        } finally {
            log.debug("read unlock {}", stamp);
            lock.unlockRead(stamp);
        }
    }

    public void write(int newData) {
        long stamp = lock.writeLock();
        log.debug("write lock {}", stamp);
        try {
            Thread.sleep(2000);
            this.data = newData;
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            log.debug("write unlock {}", stamp);
            lock.unlockWrite(stamp);
        }
    }

}
