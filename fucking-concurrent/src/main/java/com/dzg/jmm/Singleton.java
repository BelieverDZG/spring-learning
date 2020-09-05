package com.dzg.jmm;

public class Singleton {

    private Singleton(){

    }
    private static volatile Singleton INSTANCE = null;

    public static Singleton getInstance() {
        // 实例没创建，才会进入内部的 synchronized代码块
        /*if (INSTANCE == null) { 共享变量没有完全受到synchronized的保护，不能保证有序性
            synchronized (Singleton.class) {
                // 也许有其它线程已经创建实例，所以再判断一次
                if (INSTANCE == null) {
                    INSTANCE = new Singleton();
                }
            }
        }
        return INSTANCE;*/


        if (INSTANCE != null) {
            return INSTANCE;
        }
        synchronized (Singleton.class) {
            // 也许有其它线程已经创建实例，所以再判断一次
            if (INSTANCE == null) {
                INSTANCE = new Singleton();
            }
            return INSTANCE;
        }
    }
}
