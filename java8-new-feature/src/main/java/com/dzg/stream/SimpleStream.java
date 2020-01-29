package com.dzg.stream;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 举个简单的例子，表明Stream与Collection之间操作对象的区别
 */
public class SimpleStream {

    public static void main(String[] args) {
        //have a dish list (menu)

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

        //将菜单名称拼接成一个字符串
        String nameString = menu.stream().map(Dish::getName).collect(Collectors.joining("-"));
        System.out.println(nameString);
        //计算所有热量总和
        int totalCalories = menu.stream().collect(Collectors.summingInt(Dish::getCalories));
        System.out.println(totalCalories);

        System.out.println("=============");
        //将菜单分为鱼类的和其他
        menu.stream().collect(Collectors.groupingBy(dish ->
                dish.getType().equals(Dish.Type.FISH))).entrySet().forEach(System.out::println);

        //获取集合对应的流方式一
        Stream<Dish> menus = menu.stream();
        menus.forEach(System.out::println);
        System.out.println("===================");
        //方式二
        Stream<Dish> dishStream = Stream.of(new Dish("prawns", false, 300, Dish.Type.FISH),
                new Dish("salmon", false, 450, Dish.Type.FISH));
        dishStream.forEach(System.out::println);

        System.out.println("===================");
        //获取热量大于300的菜的3个名单
        List<String> res = menu.stream().filter(
                d -> {
                    System.out.println(d.getName());
                    return d.getCalories() > 300;
                }
        )
                .map(d -> {
                    System.out.println("map ->" + d.getName());
                    return d.getName();
                }).limit(3).collect(Collectors.toList());
        System.out.println(res);

       /* List<String> caloriesLessThan400 = getDishNamesByCollections(menu);
        caloriesLessThan400.forEach(System.out::println);
        System.out.println("==================");
        List<String> getNameByStream = getDishNamesByStream(menu);
        getNameByStream.forEach(System.out::println);*/
    }

    public static List<String> getDishNamesByCollections(List<Dish> dishes) {
        List<Dish> lowCalories = new ArrayList<>();
        //过滤查找热量小于400的菜
        for (Dish dish :
                dishes) {
            if (dish.getCalories() < 400) {
                lowCalories.add(dish);
            }
        }

        //睡眠10秒钟，查看进程状况
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
//        Collections.sort(lowCalories, Comparator.comparingInt(Dish::getCalories));
        Collections.sort(lowCalories, (d1, d2) -> Integer.compare(d1.getCalories(), d2.getCalories()));
        List<String> targetDishes = new ArrayList<>();

        for (Dish dish : lowCalories) {
            targetDishes.add(dish.getName());

        }
        return targetDishes;
    }

    public static List<String> getDishNamesByStream(List<Dish> dishes) {
        return dishes.parallelStream().filter(
                dish -> dish.getCalories() < 400
        ).sorted(Comparator.comparing(Dish::getCalories)).map(Dish::getName).collect(Collectors.toList());
    }
}
