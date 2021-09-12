package com.dzg.practice;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.LockSupport;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 交替打印ABCABCABC....
 */
public class PrintABCByWaitNotify {

    public static void main(String[] args) {
        WaitNotify wn = new WaitNotify(1, 5);
        Thread t1 = new Thread(() -> {
            wn.print("a", 1, 2);
        }, "t1");
        Thread t2 = new Thread(() -> {
            wn.print("b", 2, 3);
        }, "t2");
        Thread t3 = new Thread(() -> {
            wn.print("c", 3, 1);
        }, "t3");

        t1.start();
        t2.start();
        t3.start();

    }
}

/**
 * 输出内容  等待标记  下一个标记
 * a          1          2
 * b          2          3
 * c          3          1
 */

class WaitNotify {

    //等待标记
    private int flag;
    //循环等待次数
    private int loopNumber;

    public WaitNotify(int flag, int loopNumber) {
        this.flag = flag;
        this.loopNumber = loopNumber;
    }

    /**
     * 当等待标记 等于当前的标记时 打印当前字符
     *
     * @param str
     * @param waitFlag
     * @param nextFlag
     */
    public void print(String str, int waitFlag, int nextFlag) {
        for (int i = 0; i < loopNumber; i++) {
            synchronized (this) {
                //当标记不相等时候，进行等待
                while (flag != waitFlag) {
                    try {
                        this.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                //打印输出当前字符
                System.out.println(str);
                //设置下一个待打印的标记
                flag = nextFlag;
                //唤醒等待的线程，进行资源的竞争
                this.notifyAll();
            }
        }
    }

}
