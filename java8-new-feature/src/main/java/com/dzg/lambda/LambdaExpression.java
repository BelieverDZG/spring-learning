package com.dzg.lambda;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;

/**
 * Lambda表达式书写练习
 */
public class LambdaExpression {

    public static void main(String[] args) {
        //匿名内部类的方式实现
        Comparator<Apple> byColor = new Comparator<Apple>() {
            @Override
            public int compare(Apple o1, Apple o2) {
                return o1.getColor().compareTo(o2.getColor());
            }
        };

        List<Apple> list = Collections.emptyList();
        list.sort(byColor);
        System.out.println(byColor);
        //lambda表达式
        Comparator<Apple> lambdaByColor = (o1,o2) -> o1.getColor().compareTo(o2.getColor());
        //函数推断
        Comparator<Apple> lambdaByColor2 = Comparator.comparing(Apple::getColor);

        //传入一个String类型的数据，返回一个Integer类型的数据（最后一个参数是返回类型值）
        Function<String,Integer> flambda = s -> s.length();

        Predicate<Apple> predicate = (Apple a) -> a.getColor().equals("green");

        Runnable r = () ->{};

        Function<Apple,Boolean> res = a -> a.getColor().equals("red");
    }
}
