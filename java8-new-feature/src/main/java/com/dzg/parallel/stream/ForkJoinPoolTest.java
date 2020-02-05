package com.dzg.parallel.stream;

import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;
import java.util.concurrent.RecursiveTask;
import java.util.stream.Collectors;
import java.util.stream.LongStream;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

public class ForkJoinPoolTest {

    private static int[] data = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};

    public static void main(String[] args) {

        System.out.println("normal sum => " + cal());
        AccumulatorRecursiveTask task = new AccumulatorRecursiveTask(0, data.length, data);
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        Integer result = forkJoinPool.invoke(task);
        System.out.println("AccumulatorRecursiveTask res =>" + result);

        AccumulatorRecursiveAction action = new AccumulatorRecursiveAction(0, data.length, data);
        forkJoinPool.invoke(action);
        System.out.println("AccumulatorRecursiveAction res =>" + AccumulatorRecursiveAction.AccumulatorHelper.getResult());

        Fibonacci fibonacci = new Fibonacci(8);
        int fibRes = forkJoinPool.invoke(fibonacci);
        System.out.println("Fibonacci res =>" + fibRes);

        System.out.println("==============================");
        Random random = new Random(System.currentTimeMillis());
        long[] array = new long[200];
        for (int i = 0; i < 200; i++) {
            array[i] = random.nextLong();
        }
        SortAction sortAction = new SortAction(array);
        forkJoinPool.invoke(sortAction);
//        Arrays.asList(array).stream().collect(toList()).forEach(i -> System.out.print(i + " "));
        LongStream.of(array).
                forEach(a-> System.out.print(a+","));
    }

    static int cal() {
        int sum = 0;
        for (int i = 0; i < data.length; i++) {
            sum += data[i];
        }
        return sum;
    }

    /**
     * 源码案例 斐波那契数列
     */
    static class Fibonacci extends RecursiveTask<Integer> {
        final int n;

        Fibonacci(int n) {
            this.n = n;
        }

        @Override
        protected Integer compute() {
            if (n <= 1) {
                return n;
            }
            Fibonacci f1 = new Fibonacci(n - 1);
            f1.fork();
            Fibonacci f2 = new Fibonacci(n - 2);
            return f1.join() + f2.compute();
        }
    }

    /**
     * 源码案例 排序
     */
    static class SortAction extends RecursiveAction {

        final long[] array;
        final int low;
        final int high;

        private final static int THRESHOLD = 10;

        public SortAction(long[] array, int low, int high) {
            this.array = array;
            this.low = low;
            this.high = high;
        }

        SortAction(long[] array) {
            this(array, 0, array.length);
        }

        @Override
        protected void compute() {
            if ((high - low) < THRESHOLD) {
                sortSequentially(low, high);
            } else {
                int mid = (low + high) >>> 1;
                invokeAll(new SortAction(array, low, mid),
                        new SortAction(array, mid, high));
                merge(low, mid, high);
            }
        }

        private void sortSequentially(int low, int high) {
            Arrays.sort(array, low, high);
        }

        private void merge(int low, int mid, int high) {
            long[] buf = Arrays.copyOfRange(array, low, mid);
            for (int i = 0, j = low, k = mid; i < buf.length; j++) {
                array[j] = (k == high || buf[i] < array[k]) ? buf[i++] : array[k++];
            }
        }

    }
}
