package com.dzg.juc;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.Semaphore;

@Slf4j(topic = "c.SemaphoreDemo")
public class SemaphoreDemo {

    public static void main(String[] args) {
        Semaphore semaphore = new Semaphore(3);
        for (int i = 0; i < 10; i++) {
            int j = i;
            new Thread(() -> {
                //先获取一个执行资源
                try {
                    semaphore.acquire();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                try {
                    log.debug("running..." + j);
                    Thread.sleep(1000);
                    log.debug("ended..." + j);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }finally {
                    //用完之后，释放资源
                    semaphore.release();
                }
            },"t"+i).start();
        }
    }
}
