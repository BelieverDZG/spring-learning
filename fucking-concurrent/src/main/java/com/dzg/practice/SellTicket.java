package com.dzg.practice;

import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class SellTicket {
    public static void main(String[] args) {
        //模拟多人购票
        TicketWindow window = new TicketWindow(1000);

        //所有线程的集合
        List<Thread> threadList = new ArrayList<>();
        //统计卖出的票数
//        List<Integer> amount = new Vector<>();
        //导致NPE问题？？
        List<Integer> amount = new ArrayList<>();

        for (int i = 0; i < 2000; i++) {
            Thread t = new Thread(() -> {
                int cnt = window.sell(randomAmount(5));
                try {
                    //线程休眠一会，给指令交错提供些时间
                    Thread.sleep(randomAmount(5));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
//                System.out.println(cnt);

                amount.add(cnt);
            });
            threadList.add(t);
            t.start();
        }

        threadList.forEach(t -> {
            try {
                t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
//        System.out.println(amount.size());
        Stream<Integer> stream = amount.stream();
        System.out.println(stream);
        //传进来的lambda表达式有可能为空
        IntStream intStream = stream.mapToInt(a -> a != null ? a : Integer.valueOf(0));
//        IntStream intStream = stream.mapToInt(a -> a);
//        System.out.println(stream.mapToInt(a -> a));
        System.out.println("卖出去的票数: " + intStream.sum());
        System.out.println("剩余的票数: " + window.getCount());

        /*long sum = Stream.of(1, 1, null, 2, 3, 4, null, 5, 6, 7, 8, 9, 10)
                .filter(Objects::nonNull)
                .distinct()
                .mapToLong(a -> a * 2)
                .peek(System.out::println)
                .skip(2)
                .limit(4)
                .sum();

        System.out.println(sum);*/
    }

    static Random random = new Random();

    public static int randomAmount(int num) {
        return random.nextInt(num) + 1;
    }
}

class TicketWindow {

    private int count;

    public TicketWindow(int count) {
        this.count = count;
    }

    public int getCount() {
        return count;
    }

    //加 synchronized 保证线程安全
    public int sell(int num) {
        if (this.count >= num) {
            this.count -= num;
            return num;
        } else {
            return 0;
        }
    }
}
