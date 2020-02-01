package com.dzg.collector;

import com.dzg.lambda.Apple;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Stream;

public class MyCollectorAction {

    public static void main(String[] args) {

        Collector<String, List<String>, List<String>> myCollector = new ToListCollector<>();
        String[] tests = new String[]{"Come", "On", "Wu Han", "China", "Lilard", "All Star", "Hero"};

        Arrays.asList(tests).stream().filter(a -> a.length() > 5).forEach(System.out::println);

        List<String> collect = Arrays.asList(tests)
                .stream()
                .filter(a -> a.length() >= 7)
                .collect(myCollector);
        System.out.println(collect);
    }
}
