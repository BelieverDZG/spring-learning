package com.dzg.juc;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 读读可以并发，读写或者写写是互斥的
 */
@Slf4j(topic = "c.ReadWriteLockDemo")
public class ReadWriteLockDemo {
    public static void main(String[] args) throws InterruptedException {
        DataContainer dataContainer = new DataContainer();
        new Thread(() -> {
            dataContainer.write();
        }, "t1").start();

        Thread.sleep(100);

        new Thread(dataContainer::write, "t2").start();

    }
}

@Slf4j(topic = "c.DataContainer")
class DataContainer {

    private Object data;

    private ReentrantReadWriteLock rw = new ReentrantReadWriteLock();
    private ReentrantReadWriteLock.ReadLock r = rw.readLock();

    private ReentrantReadWriteLock.WriteLock w = rw.writeLock();

    public Object read() {
        log.debug("获取读锁");
        r.lock();
        try {
            log.debug("读取...");
            return data;
        } finally {
            log.debug("释放读锁...");
            r.unlock();
        }
    }

    public void write() {
        w.lock();
        log.debug("获取写锁...");
        try {
            log.debug("写入...");
        } finally {
            log.debug("释放写锁...");
            w.unlock();
        }
    }
}
