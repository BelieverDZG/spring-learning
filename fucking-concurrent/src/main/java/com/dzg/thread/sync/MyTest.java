package com.dzg.thread.sync;

import org.springframework.http.converter.json.GsonBuilderUtils;

public class MyTest {

    static {
        i = 0;
//        System.out.println(i);//非法向前引用
    }
    static int i;
    public void method() {
        synchronized (this) {
            System.out.println("Method 1 test");
        }
    }

    public synchronized void method2() {
        System.out.println("Method 2 test");
    }

    static int a = 100;
    static final int b = 100;
    static final Object lock = new Object();

    public static void main(String[] args) throws InterruptedException {
//        Thread.currentThread().wait();
//        Thread.currentThread().notify();
        synchronized (lock){
            lock.wait(1000);
        }
        System.out.println(test());//100
        System.out.println(SubClass.B);
    }

    public static int test(){
        try {
            int i = 1 / 0;
            System.out.println(1);
        }catch (Exception e){
            e.printStackTrace();
            return 10;
        }finally {
            return 100;
        }
    }

    static class Parent{
        public static int A = 1;

        static {
            A = 2;
            System.out.println(A);
        }
    }

    static class SubClass extends Parent{
        public static int B = A;
    }
}

