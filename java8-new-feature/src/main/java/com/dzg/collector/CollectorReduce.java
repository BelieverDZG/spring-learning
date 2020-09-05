package com.dzg.collector;

import com.dzg.stream.Dish;

import java.util.*;
import java.util.stream.Collectors;

public class CollectorReduce {

    public static void main(String[] args) {
        List<Dish> menu = Arrays.asList(
                new Dish("pork", false, 800, Dish.Type.MEAT),
                new Dish("beef", false, 700, Dish.Type.MEAT),
                new Dish("chicken", false, 400, Dish.Type.MEAT),
                new Dish("french fries", true, 530, Dish.Type.OTHER),
                new Dish("rice", true, 350, Dish.Type.OTHER),
                new Dish("season fruit", true, 120, Dish.Type.OTHER),
                new Dish("pizza", true, 550, Dish.Type.OTHER),
                new Dish("prawns", false, 300, Dish.Type.FISH),
                new Dish("salmon", false, 450, Dish.Type.FISH));

        long count = menu.stream().filter(d -> d.isVegetarian()).count();
        System.out.println(count);
        Long collectCount = menu.stream().filter(d -> d.isVegetarian()).collect(Collectors.counting());
        System.out.println(collectCount);

//        Optional<Integer> reduce = menu.stream().map(Dish::getCalories).reduce(Integer::max);
//        int value = reduce.get().intValue();
//        System.out.println(value);

        //获取热量最大的Dish
        Optional<Dish> maxCalories = menu.stream()
                .reduce((d1, d2) -> d1.getCalories() > d2.getCalories() ? d1 : d2);
//        System.out.println(maxCalories.get());
        maxCalories.ifPresent(System.out::println);

        Optional<Dish> maxCaloriesCollect = menu.stream().collect(Collectors.maxBy(Comparator.comparingInt(Dish::getCalories)));
        maxCaloriesCollect.ifPresent(System.out::println);

        Integer collect = menu.stream().collect(Collectors.collectingAndThen(Collectors.toList(), t -> t.size()));
        System.out.println(collect);

        Map<Dish.Type, List<Dish>> collectorsGroupByType = menu.stream().collect(Collectors.groupingBy(Dish::getType));
        System.out.println(collectorsGroupByType);

        Map<Dish.Type, Integer> collect1 = menu.stream().collect(Collectors.groupingBy(Dish::getType, Collectors.summingInt(Dish::getCalories)));
        System.out.println(collect1);

        Map<Dish.Type, Double> collect2 = menu.stream().collect(Collectors.groupingBy(Dish::getType, Collectors.averagingInt(Dish::getCalories)));
        System.out.println(collect2);

    }
}
