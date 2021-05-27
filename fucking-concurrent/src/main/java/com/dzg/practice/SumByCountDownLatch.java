package com.dzg.practice;

import java.util.concurrent.CountDownLatch;

/**
 * 用五个线程求取1-10000之间的和
 * @author BelieverDzg
 * @date 2021/5/27 11:19
 */
public class SumByCountDownLatch {

    /*
    public class AddOperationThreadTest {

    public static void main(String[] args) throws InterruptedException {
        long start = System.currentTimeMillis();

        for (int i = 1; i <= threadNum; i++) {
            addThreads[i - 1] = new AddOperationThread((i - 1) * count + 1, count * i, countDownLatch);
            new Thread(addThreads[i - 1]).start();
        }
        countDownLatch.await();
        for (int i = 1; i <= threadNum; i++) {
            sum = sum + addThreads[i - 1].getSum();
        }
        System.out.println(sum);
        long end = System.currentTimeMillis();
        System.out.println(end - start);
    }

}
    * */
    //方法1：使用CountDownLatch保证拿到最终的计算结果
    public static void main(String[] args) throws InterruptedException {
        int sum = 0;
        int threadNum = 5;
        int count = 2000;
        AddOperationThread[] addThreads = new AddOperationThread[threadNum];
        CountDownLatch latch = new CountDownLatch(threadNum);

        for (int i = 1; i <= threadNum ; i++) {
            addThreads[i-1] = new AddOperationThread((i-1)*count + 1 , count * i , latch);
            new Thread(addThreads[i-1]).start();
        }

        latch.await();//等待所有线程完成任务

        for (int i = 1; i <= threadNum ; i++) {
            sum += addThreads[i-1].getSum();
        }

        System.out.println(sum);
    }
    //求和
}

class AddOperationThread extends Thread{
    private int start;
    private int end;
    private CountDownLatch countDownLatch;

    private int sum;

    public AddOperationThread(int start, int end , CountDownLatch countDownLatch){
        this.start = start;
        this.end = end;
        this.countDownLatch = countDownLatch;
    }

    @Override
    public void run() {
        for (int i = start; i <= end ; i++) {
            sum += i;
        }
        countDownLatch.countDown();
    }

    public int getSum() {
        return sum;
    }
}