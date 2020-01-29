package com.dzg.stream;

import java.util.Arrays;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Stream;

public class StreamFind {
    public static void main(String[] args) {
        Stream<Integer> stream = Arrays.stream(new Integer[]{1, 2, 3, 4, 5, 6, 7});
        Optional<Integer> result = stream.filter(i -> i % 2 == 0).findAny();
        Integer integer = result.get();
        System.out.println(integer);//2

        stream = Arrays.stream(new Integer[]{1, 2, 3, 4, 5, 6, 7});
        Optional<Integer> optional = stream.filter(integer1 -> integer < 10).findAny();

        //Return the value if present, otherwise return {@code other}.
        System.out.println(optional.orElse(-1));//1

        stream = Arrays.stream(new Integer[]{1, 2, 3, 4, 5, 6, 7});
        Optional<Integer> optional1 = stream.filter(i -> i % 3 == 0).findFirst();
        System.out.println(optional1.get());//3

        int[] values = {12, 23, 34, 45, 56, 67, 78};
        int res = find(values, -1, pre -> pre % 5 == 0);
        System.out.println(res);
    }

    public static int find(int[] values, int defaultValue, Predicate<Integer> predicate) {
        for (int i : values) {
            if (predicate.test(i)) {
                return i;
            }
        }
        return defaultValue;
    }
}
