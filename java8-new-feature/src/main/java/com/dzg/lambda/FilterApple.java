package com.dzg.lambda;

import sun.java2d.pipe.AlphaPaintPipe;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FilterApple{

    /**
     * 为查找不同的苹果，创建一个接口，根据颜色、重量等进行查找不同的苹果
     */
    @FunctionalInterface //表明是函数时接口，用于实现Lambda表达式，也可省略不写
    public interface AppleFilter{
        boolean filter(Apple apple);
    }

    /**
     * 查找绿色的且重量大于160的苹果
     */
    public static class GreenAndWeight160Filter implements AppleFilter{
        @Override
        public boolean filter(Apple apple) {
            return apple.getColor().equals("green") && apple.getWeight() >=160;
        }
    }

    /**
     * 查找红色且小于200的苹果
     */
    public static class RedAndWeight200Filter implements AppleFilter{

        @Override
        public boolean filter(Apple apple) {
            return apple.getColor().equals("red") && apple.getWeight()<=200;
        }
    }

    /**
     * @param apples
     * @param appleFilter 使用过滤器进行不同条件的过滤
     * @return
     */
    public static List<Apple> findApple(List<Apple> apples,AppleFilter appleFilter){
        List<Apple> appleList = new ArrayList<>();

        for (Apple apple:
             apples) {
            if (appleFilter.filter(apple)){
                appleList.add(apple);
            }
        }
        return appleList;
    }
    /**
     * 普通的查找绿色苹果方法
     * @param apples
     * @return
     */
    public static List<Apple> filterGreenApple(List<Apple> apples){
        List<Apple> list = new ArrayList<>();
        for (Apple apple:
             apples) {
            if ("green".equals(apple.getColor())){
                list.add(apple);
            }
        }
        return list;
    }

    /**
     * 查找不同颜色的苹果
     * @param lists
     * @param color
     * @return
     */
    public static List<Apple> findApple(List<Apple> lists,String color){
        List<Apple> apples = new ArrayList<>();
        for (Apple apple:lists){
            if (color.equals(apple.getColor())){
                apples.add(apple);
            }
        }
        return apples;
    }
    public static void main(String[] args) throws InterruptedException {
        List<Apple> appleList = Arrays.asList(new Apple("green",180),
                new Apple("red",190),new Apple("green",160),new Apple("yellow",160));


        List<Apple> lambdaResult = findApple(appleList, (apple)-> "green".equals(apple.getColor()));
        System.out.println(lambdaResult);

        //以下实例用线程来表明lambda表达式和普通方式的区别
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName());
            }
        });
        thread.start();

        new Thread(() -> System.out.println(Thread.currentThread().getName())).start();

        //线程有可能在主线程结束时候还没有开始，所以需要join一下等待该线程死亡
        Thread.currentThread().join();

//        List<Apple> apples = filterGreenApple(appleList);
//        List<Apple> apples = findApple(appleList,"re2d");
       /* GreenAndWeight160Filter filter = new GreenAndWeight160Filter();
        List<Apple> apples = findApple(appleList,filter);
        System.out.println(apples);

        List<Apple> yellowApples = findApple(appleList, new AppleFilter(){

            @Override
            public boolean filter(Apple apple) {
                return "yellow".equals(apple.getColor());
            }
        });
        System.out.println(yellowApples);*/


    }
}
