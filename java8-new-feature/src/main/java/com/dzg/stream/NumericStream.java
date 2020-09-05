package com.dzg.stream;

import java.util.Arrays;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class NumericStream {

    public static void main(String[] args) {
        Stream<Integer> stream = Arrays.stream(new Integer[]{1, 2, 3, 4, 5, 6, 7});
//        int sum = stream.mapToInt(i -> i.intValue()).filter(i -> i > 3).sum();//22
        IntStream intStream = stream.mapToInt(Integer::intValue);
        System.out.println(intStream.filter(i -> i % 2 == 0).sum());//12

        stream = Arrays.stream(new Integer[]{1, 2, 3, 4, 5, 6, 7});
        int max = stream.mapToInt(Integer::intValue).max().getAsInt();
        System.out.println(max);

        int a = 9;
        //1...1000
        //resullt int[a,b,c]

        /**
         * 1...100中与9构成勾股定理的数字
         */
        IntStream.rangeClosed(1, 100)
                .filter(i -> Math.sqrt(i * i + a * a) % 1 == 0)
                .boxed()
                .map(i -> new int[]{a, i, (int) Math.sqrt(i * i + a * a)})
                .forEach(ints -> System.out.println("a=" + ints[0] + " b=" + ints[1] + " c=" + ints[2]));

//        System.out.println(Math.sqrt(4));开根号

        IntStream.rangeClosed(1,100)
                .filter(b->Math.sqrt(a*a+b*b)%1==0)
                .mapToObj(i -> new int[]{a, i, (int) Math.sqrt(i * i + a * a)})
                .forEach(ints -> System.out.println("a=" + ints[0] + " b=" + ints[1] + " c=" + ints[2]));
    }
}
