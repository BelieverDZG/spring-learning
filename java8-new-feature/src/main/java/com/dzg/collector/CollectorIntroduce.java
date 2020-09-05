package com.dzg.collector;

import com.dzg.lambda.Apple;

import java.util.*;
import java.util.stream.Collectors;

public class CollectorIntroduce {
    public static void main(String[] args) {
        List<Apple> list = Arrays.asList(new Apple("green", 150)
                , new Apple("yellow", 120)
                , new Apple("green", 170)
                , new Apple("green", 150)
                , new Apple("yellow", 120)
                , new Apple("green", 170));
        List<Apple> greenApple = list.stream().filter(apple -> apple.getColor().equals("green") & apple.getWeight() > 150)
                .collect(Collectors.toList());
        Optional.of(greenApple).ifPresent(a -> {
            System.out.println(a.getClass());
            System.out.println(a);
        });
        System.out.println("groupByColorNormalMethod.........");
        Optional.of(groupByColorNormalMethod(list)).ifPresent(System.out::println);

        System.out.println("groupByFunction.............");
        Optional.of(groupByFunction(list)).ifPresent(System.out::println);

        System.out.println("groupByCollector...........");
        Optional.of(groupByCollector(list)).ifPresent(System.out::println);
    }


    //根据苹果颜色进行分组
    public static Map<String, List<Apple>> groupByColorNormalMethod(List<Apple> apples) {
        Map<String, List<Apple>> map = new HashMap<>();
        for (Apple a : apples) {
            List<Apple> tempList = map.get(a.getColor());
            if (tempList == null) {
                tempList = new ArrayList<>();
                map.put(a.getColor(), tempList);
            }
            tempList.add(a);
        }
        return map;
    }

    /**
     * 使用Function函数的功能进行分组
     * @param apples
     * @return
     */
    public static Map<String, List<Apple>> groupByFunction(List<Apple> apples) {
        Map<String, List<Apple>> map = new HashMap<>();
        apples.parallelStream().forEach(apple -> {
            List<Apple> colorList = Optional.ofNullable(map.get(apple.getColor())).orElseGet(() -> {
                List<Apple> appleList = new ArrayList<>();
                map.put(apple.getColor(), appleList);
                return appleList;
            });
            colorList.add(apple);
        });
        return map;
    }

    /**
     * 无情！
     * @param apples
     * @return
     */
    public static Map<String, List<Apple>> groupByCollector(List<Apple> apples) {
        return apples.parallelStream().collect(Collectors.groupingBy(Apple::getColor));
    }
}
