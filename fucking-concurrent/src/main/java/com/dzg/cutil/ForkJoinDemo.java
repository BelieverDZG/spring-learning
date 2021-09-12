package com.dzg.cutil;

import lombok.extern.slf4j.Slf4j;

import java.util.*;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

public class ForkJoinDemo {

    public static void main(String[] args) {
        ForkJoinPool pool = new ForkJoinPool(4);
        Integer invoke = pool.invoke(new AddTask3(1, 5));
        System.out.println(invoke);
        Map<Integer, Integer> maps = new TreeMap<>();
    }

    public int[] topKFrequent(int[] nums, int k) {
        if (nums == null || nums.length == 0) {
            return new int[0];
        }
        int[] ans = new int[k];
        Map<Integer, Integer> count = new TreeMap<>();
        for (int i = 0; i < nums.length; i++) {
            count.put(nums[i], count.getOrDefault(nums[i], 0) + 1);
        }

        PriorityQueue<Integer> heap = new PriorityQueue<>(k);
        for (Map.Entry<Integer, Integer> one : count.entrySet()) {
            if (k > 0) {
                heap.add(one.getValue());
                k--;
            }else if (heap.peek() < one.getValue()){
                heap.poll();
                heap.add(one.getValue());
            }
        }
        int idx = 0;
        while (!heap.isEmpty()) {
            ans[idx++] = heap.poll();
        }
        return ans;
    }
}

/**
 * 求解1-N的和
 */
class MyTask extends RecursiveTask<Integer> {

    private int n;

    public MyTask(int n) {
        this.n = n;
    }

    @Override
    protected Integer compute() {
        if (n == 1) {
            return 1;
        }
        MyTask t1 = new MyTask(n - 1);
        t1.fork();
        int ans = n + t1.join();
        return ans;
    }

}

@Slf4j(topic = "c.AddTask3")
class AddTask3 extends RecursiveTask<Integer> {

    int begin;

    int end;

    public AddTask3(int begin, int end) {
        this.begin = begin;
        this.end = end;
    }

    @Override
    public String toString() {
        return "{" + begin + "," + end + '}';
    }

    @Override
    protected Integer compute() {
// 5, 5
        if (begin == end) {
            log.debug("join() {}", begin);
            return begin;
        }
// 4, 5

        if (end - begin == 1) {
            log.debug("join() {} + {} = {}", begin, end, end + begin);
            return end + begin;
        }
// 1 5
        int mid = (end + begin) / 2; // 3
        AddTask3 t1 = new AddTask3(begin, mid); // 1,3
        t1.fork();
        AddTask3 t2 = new AddTask3(mid + 1, end); // 4,5
        t2.fork();
        log.debug("fork() {} + {} = ?", t1, t2);
        int result = t1.join() + t2.join();
        log.debug("join() {} + {} = {}", t1, t2, result);
        return result;
    }
}