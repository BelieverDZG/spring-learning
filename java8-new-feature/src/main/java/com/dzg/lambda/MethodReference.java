package com.dzg.lambda;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * Lambda之方法推断
 */
public class MethodReference {

    public static <T> void useConsumer(Consumer<T> consumer,T t){
        consumer.accept(t);
    }

    public static void main(String[] args) {
        useConsumer(s -> System.out.println(s),"hello world");
        useConsumer(System.out ::println,"hello method reference");

        List<Apple> list = Arrays.asList(new Apple("abc", 123), new Apple("Green", 110), new Apple("red", 123));
        System.out.println(list);

        list.sort(Comparator.comparing(a -> a.getColor()));
        System.out.println(list);

        list.sort((a1,a2) -> a1.getColor().compareTo(a2.getColor()));
        System.out.println(list);

        System.out.println("=====================");
        list.stream().forEach(apple -> System.out.println(apple));
        System.out.println("-------------------");
        list.stream().forEach(System.out::println);

        Integer integer = Integer.parseInt("1234");
        System.out.println(integer);

        BiFunction<String, Integer, Character> f1 = String::charAt;
        Character happy_new_year = f1.apply("Happy New Year", 7);
        System.out.println(happy_new_year);

        String str = new String("原新年，胜旧年");
        Function<Integer, Character> integerCharacterFunction = str::charAt;
        Character apply = integerCharacterFunction.apply(6);
        System.out.println(apply);

        Supplier<String> supplier = String::new;
        String s = supplier.get();
        System.out.println(s.length());

        BiFunction<String, Integer, Apple> appleBiFunction = Apple::new;
        Apple apple = appleBiFunction.apply("redred", 1232);
        System.out.println(apple);

        ThreeFunction<String, Long, String, ComplexApple> newApple = ComplexApple::new;
        ComplexApple complexApple = newApple.apply("黑", 123L, "black");
        System.out.println(complexApple);


    }
}
