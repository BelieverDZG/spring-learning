package com.dzg.stream;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamMap {
    public static void main(String[] args) {
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 7, 7, 8, 9, 9);
        //将所有元素distinct()后乘2
        List<Integer> res = list.stream().distinct().map(i -> i * 2).collect(Collectors.toList());
        System.out.println(res);

        listDish().stream().map(dish -> dish.getName()).forEach(System.out::println);

        List<String> dishes = listDish().stream().map(Dish::getName).collect(Collectors.toList());
        System.out.println(dishes);

        //Stream扁平化
        String[] strings = {"Come on China","WuHan Gonna Win"};

        //{},{}两个字符数组
        Stream<String[]> stream = Arrays.stream(strings).map(str -> str.split(""));
        System.out.println(stream);

        Stream<String> stringStream = stream.flatMap(Arrays::stream);
        System.out.println(stringStream);
        //输出单个字符
        stringStream.distinct().forEach(System.out::print);
    }

    private static List<Dish> listDish() {
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
        return menu;
    }
}
