package com.dzg.stream;

import java.util.Arrays;
import java.util.Optional;
import java.util.stream.Stream;

public class StreamReduce {

    public static void main(String[] args) {

        Stream<Integer> stream = Arrays.stream(new Integer[]{1, 2, 3, 4, 5, 6, 7});
        Integer sum = stream.reduce(0, Integer::sum);
        System.out.println(sum);

        stream = Arrays.stream(new Integer[]{1, 2, 3, 4, 5, 6, 7});
        Optional<Integer> reduce = stream.reduce(Integer::max);
        Integer max = reduce.get();
        System.out.println(max);

        stream = Arrays.stream(new Integer[]{1, 2, 3, 4, 5, 6, 7});
        stream.reduce((i, j) -> i + j).ifPresent(System.out::println);

        stream = Arrays.stream(new Integer[]{1, 2, 3, 4, 5, 6, 7});
        stream.reduce(Integer::min).ifPresent(System.out::println);

        stream = Arrays.stream(new Integer[]{1, 2, 3, 4, 5, 6, 7});
        stream.reduce((a, b) -> a > b ? a : b).ifPresent(System.out::println);//get max value

        stream = Arrays.stream(new Integer[]{1, 2, 3, 4, 5, 6, 7});
        Stream<Integer> evenStream = stream.filter(integer -> integer % 2 == 0);
//        evenStream.forEach(System.out::print);
        //求所有偶数的乘积
        Integer reduce1 = evenStream.reduce(1, (i, j) -> i * j);
        Optional.of(reduce1).ifPresent(System.out::println);
    }
}
