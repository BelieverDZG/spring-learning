package com.dzg.thread.create;

/**
 * 栈与栈帧
 * 线程执行的时候，栈内存是相互独立的
 */
public class TestFrames {

    public static void main(String[] args) {
        Thread t1 = new Thread(){
            @Override
            public void run() {
                m1(100);
            }
        };
        t1.setName("t1");
        t1.start();
        m1(10);
    }

    public static void m1(int x){
        int y = x + 1;
        Object m = m2();
        System.out.println(m);
    }

    private static Object m2() {
        Object n = new Object();
        return n;
    }


}
