package com.dzg.jmm;

public class KjianXing {

    //    volatile保证内存可见性 static boolean run = true;
    static boolean run = true;

    public static void main(String[] args) throws InterruptedException {
        //run会被拷贝到t线程的本地内存中，主线程对其修改之后，t线程并不知道
        Thread t = new Thread(() -> {
            while (run) {
//                System.out.println("1");
            }
        });
        t.start();
        Thread.sleep(1000);
        System.out.println("停止t.....");

        run = false;
    }
}
