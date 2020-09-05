package com.dzg.parallel.stream;

import java.util.function.Function;
import java.util.stream.LongStream;
import java.util.stream.Stream;

/**
 * 并行处理
 */
public class ParallelProcessing {
    public static void main(String[] args) {
        //处理器核数
//        System.out.println(Runtime.getRuntime().availableProcessors());
        System.out.println("The process time of normalAdd method is " + measureSumPerformance(ParallelProcessing::normalAdd, 100_000_000L) + "MS");
//        System.out.println("The process time of iterateStream method is " + measureSumPerformance(ParallelProcessing::iterateStream, 100_000_000L) + "MS");
//        System.out.println("The process time of parallelStream method is " + measureSumPerformance(ParallelProcessing::parallelStream, 100_000_000L) + "MS");
//        System.out.println("The process time of parallelStream2 method is " + measureSumPerformance(ParallelProcessing::parallelStream2, 100_000_000L) + "MS");
        System.out.println("The process time of parallelStream3 method is " + measureSumPerformance(ParallelProcessing::parallelStream3, 100_000_000L) + "MS");

//        System.out.println(iterateStream(10L));
    }

    private static Long measureSumPerformance(Function<Long, Long> fun, Long limit) {
        Long fastest = Long.MAX_VALUE;
        Long res = 0L;
        for (int i = 0; i < 10; i++) {
            long begin = System.currentTimeMillis();
            res = fun.apply(limit);
            long duration = System.currentTimeMillis() - begin;
//            System.out.println("The result of sum is " + res);
            if (duration < fastest)
                fastest = duration;
        }
        System.out.println("The result of sum is " + res);
        return fastest;
    }

    public static Long iterateStream(Long lim) {
        return Stream.iterate(1L, i -> i + 1)
                .limit(lim).reduce(0L, Long::sum);
    }

    public static Long parallelStream(Long lim) {
        return Stream.iterate(1L, i -> i + 1)
                .parallel()
                .limit(lim).reduce(0L, Long::sum);
    }

    public static Long parallelStream2(Long lim) {
        return Stream.iterate(1L, i -> i + 1)
                .mapToLong(Long::longValue)
                .parallel()
                .limit(lim).reduce(0L, Long::sum);
    }

    //The process time of normalAdd method is 774MS
    //The process time of parallelStream3 method is 25MS
    public static Long parallelStream3(Long lim) {
        return LongStream.rangeClosed(1L, lim).parallel().reduce(0L, Long::sum);
    }

    private static Long normalAdd(Long limit) {
        Long res = 0L;
        for (Long i = 0L; i <= limit; i++) {
            res += i;
        }
        return res;
    }
}
