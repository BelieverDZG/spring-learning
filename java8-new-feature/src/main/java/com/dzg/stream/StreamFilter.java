package com.dzg.stream;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.toList;

public class StreamFilter {
    public static void main(String[] args) {
//        IntStream integerStream = Arrays.stream(new int[]{1, 2, 3, 4, 5, 6, 7, 8});

        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 7, 7, 8, 9, 9);
        /**
         * 过滤得到所有的偶数
         *  Stream<Integer> integerStream1 = list.stream().filter(integer -> integer % 2 == 0);
         *     List<Integer> collect = integerStream1.collect(toList());
         */
        List<Integer> res = list.stream().filter(integer -> integer % 2 == 0).collect(toList());
        res.forEach(System.out::print);

        res = list.stream().distinct().collect(toList());
        System.out.println(res);

        /**
         * Returns a stream consisting of the remaining elements of this stream
         *      * after discarding the first {@code n} elements of the stream.
         *      * If this stream contains fewer than {@code n} elements then an
         *      * empty stream will be returned.
         */
        res = list.stream().skip(10).collect(toList());
        System.out.println(res);

        /**
         * Returns a stream consisting of the elements of this stream, truncated
         *      * to be no longer than {@code maxSize} in length.
         */
        res = list.stream().limit(7).collect(toList());
        System.out.println(res);

//        list.forEach(System.out::println);
//        list.forEach(integer -> System.out.println(integer));
//        list.forEach((Integer i) -> System.out.print(i+" "));
        for (int i : list){
            System.out.print(i+" ");
        }
    }
}
