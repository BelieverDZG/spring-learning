package com.dzg.lambda;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.*;

/**
 * Lambda表达式的使用
 */
public class LambdaUsage {

    /**
     * 根据给定的条件过滤获取相应的结果
     * @param source
     * @param predicate Represents a predicate (boolean-valued function) of one argument.
     * @return
     */
    public static List<Apple> filter(List<Apple> source, Predicate<Apple> predicate){
        List<Apple> apples = new ArrayList<>();
        for (Apple a:
             source) {
            if (predicate.test(a)){
                apples.add(a);
            }
        }
        return apples;
    }

    /**
     * 根据重量过滤得到相应的苹果
     * @param source
     * @param longPredicate
     * @return
     */
    public static List<Apple> filterByWeight(List<Apple> source, LongPredicate longPredicate){
        List<Apple> result = new ArrayList<>();
        for (Apple a:source){
            if (longPredicate.test(a.getWeight())){
                result.add(a);
            }
        }
        return result;
    }

    /**
     * boolean test(T t, U u);
     * 使用BiPredicate 根据两个传入的参数进行判断，符合条件的则返回true
     * @param source
     * @param biPredicate
     * @return
     */
    public static List<Apple> filterByBiPredicate(List<Apple> source, BiPredicate<String,Long> biPredicate){
        List<Apple> result = new ArrayList<>();
        for (Apple apple:source){
            if (biPredicate.test(apple.getColor(), (long) apple.getWeight())){
                result.add(apple);
            }
        }
        return result;
    }

    /**
     *
     * @param source
     * @param consumer Performs this operation on the given argument.
     */
    public static void simpleTestConsumer(List<Apple> source, Consumer<Apple> consumer){
        for (Apple a:source){
            consumer.accept(a);
        }
    }

    /**
     *
     * @param source
     * @param biConsumer Performs this operation on the given arguments. void 类型
     */
    public static void testBiConsumer(String str, List<Apple> source, BiConsumer<Apple, String> biConsumer){
        for (Apple a : source){
            biConsumer.accept(a,str);
        }
    }

    /**
     *
     * @param source
     * @param fun  Function对应的第一个为输入参数，第二个为输出参数
     * @return
     */
    public static String testFunction(Apple source,Function<Apple,String> fun){
        return fun.apply(source);
    }

    /**
     * Represents a function that accepts two arguments and produces a result.
     * This is the two-arity specialization of {@link Function}.
     * @param color
     * @param weight
     * @param biFunction
     * @return
     */
    public static Apple testBiFunction(String color,Long weight,BiFunction<String,Long,Apple> biFunction){
        return biFunction.apply(color,weight);
    }

    public static Apple createApple(Supplier<Apple> supplier){
        return supplier.get();
    }
    public static void main(String[] args) {
        /*Runnable r1 = new Runnable() {
            @Override
            public void run() {
                System.out.println("hello deng1");
            }
        };
        process(r1);
        Runnable r2 = () -> System.out.println("hello deng2");
        process(r2);
        process(()-> System.out.println("hello deng3"));*/
        List<Apple> appleList = Arrays.asList(new Apple("blue",120),new Apple("red",180)
                                        ,new Apple("yellow",140));
        //调用filter方法，传递的参数是一个Predicate对应的Lambda
        List<Apple> redList = filter(appleList, apple -> apple.getColor().equals("red"));
        System.out.println(redList);

        List<Apple> byWeight = filterByWeight(appleList, value -> value > 100);
        System.out.println(byWeight);

        //传入的参数为两个，必须加括号括起来，后面若是加{}
        //则需要为如下形式
        /*(s, w) -> {
            return s.equals("yellow") && w >= 120;
        }*/
        List<Apple> biPredicateApples = filterByBiPredicate(appleList, (s, w) -> s.equals("yellow") && w >= 120);
        System.out.println(biPredicateApples);


        System.out.println("Consumer的使用");
        //传入的参数根据方法推断 等价于 apple -> System.out.println(apple)
        simpleTestConsumer(appleList, System.out::println);

        System.out.println("====================");
        //w对应传入的第一个参数
        testBiConsumer("deng",appleList,(s,w) -> System.out.println(w+"颜色："+s.getColor()+","+"重量："+s.getWeight()));

        System.out.println("=====Function接口的使用=======");

        String testFunction = testFunction(new Apple("yellow", 100), a -> a.toString());
        System.out.println(testFunction);

        Apple apple = testBiFunction("hhh", 121L, (c, w) -> new Apple(c, w.intValue()));
        System.out.println(apple);

        System.out.println("=============");
        Supplier<String> str = String::new; //方法推导
        System.out.println(str.get().getClass());

        System.out.println("Supplier的使用");
        Apple apple1 = createApple(() ->new Apple("supplier",123));
        System.out.println(apple1);

        int i = 0;
        Runnable r = new Runnable() {
            @Override
            public void run() {
//                i++;不能正确引用
                System.out.println(i);//可以使用
            }
        };
        r.run();
        Runnable r2 = () -> System.out.println(i);
        r2.run();

        //方法推断演练
        BiFunction<String, Integer, Integer> stringIntegerIntegerBiFunction = Integer::parseInt;
        appleList.sort(Comparator.comparing(Apple::getWeight));

        Function<String, Integer> stringIntegerFunction = String::length;
        System.out.println(stringIntegerFunction);

        BiConsumer<Test, String> say = Test::say;
        Consumer<String> consumer = System.out::println;
    }

    interface Test{
        void say(String s);
    }

    public static void useTest(BiConsumer<Test,String> consumer,List<String> list){

    }

    public static void process(Runnable runnable){
        runnable.run();
    }
}
