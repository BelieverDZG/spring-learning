package com.dzg.collector;

import com.dzg.stream.Dish;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ConcurrentSkipListMap;
import java.util.concurrent.ConcurrentSkipListSet;
import java.util.function.BinaryOperator;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.*;

public class CollectorsInAction {

    public static void testAveragingXXX() {
        System.out.println("testAveragingDouble");
        Optional.ofNullable(menu.stream().collect(averagingDouble(Dish::getCalories)))
                .ifPresent(System.out::println);

        System.out.println("testAveragingInt");
        Optional.ofNullable(menu.stream().collect(averagingInt(Dish::getCalories)))
                .ifPresent(System.out::println);

        System.out.println("testAveragingLong");
        Optional.ofNullable(menu.stream().collect(averagingLong(Dish::getCalories)))
                .ifPresent(System.out::println);
    }

    public static void testCollectingAndThen() {
        System.out.println("testCollectingAndThen");
        Optional.ofNullable(menu.stream().collect(Collectors.collectingAndThen(
                averagingInt(Dish::getCalories), a -> "The average calories is about " + a.intValue()
        ))).ifPresent(System.out::println);

        //Exception in thread "main" java.lang.UnsupportedOperationException
        /*List<Dish> dishList = menu.stream().filter(dish -> dish.getType().equals("MEAT"))
                .collect(collectingAndThen(toList(), Collections::unmodifiableList));
        dishList.add(new Dish("", false, 100, Dish.Type.OTHER));
        System.out.println(dishList);*/
    }

    public static void testCounting() {
        Optional.ofNullable(menu.stream().collect(Collectors.counting()))
                .ifPresent(System.out::println);
    }

    public static void testGroupingByFunction() {
        Optional.ofNullable(menu.stream().collect(Collectors.groupingBy(Dish::getType)))
                .ifPresent(System.out::println);
    }

    public static void testGroupingByFunctionAndCollector() {
        Optional.ofNullable(menu.stream().collect(Collectors.groupingBy(Dish::getType, averagingInt(Dish::getCalories))))
                .ifPresent(System.out::println);
    }

    public static void testGroupingByFunctionAndSupplierAndCollector() {
        Collector<Dish, ?, TreeMap<Dish.Type, Double>> dishTreeMapCollector = groupingBy(Dish::getType, TreeMap::new, averagingInt(Dish::getCalories));
        TreeMap<Dish.Type, Double> collect = menu.stream().collect(dishTreeMapCollector);
        Optional.ofNullable(collect)
                .ifPresent(System.out::println);
        Optional.of(collect.getClass()).ifPresent(System.out::println);
    }

    public static void testSummarizingInt() {
        IntSummaryStatistics intSummaryStatistics = menu.stream().collect(summarizingInt(Dish::getCalories));
        Optional.ofNullable(intSummaryStatistics)
                .ifPresent(System.out::println);
        //IntSummaryStatistics{count=9, sum=4200, min=120, average=466.666667, max=800}
    }

    public static void testSummarizingDouble() {
        DoubleSummaryStatistics doubleSummaryStatistics = menu.stream().collect(summarizingDouble(Dish::getCalories));
        Optional.ofNullable(doubleSummaryStatistics)
                .ifPresent(System.out::println);
        //DoubleSummaryStatistics{count=9, sum=4200.000000, min=120.000000, average=466.666667, max=800.000000}
    }

    public static void testSummarizingLong() {
        LongSummaryStatistics longSummaryStatistics = menu.stream().collect(summarizingLong(Dish::getCalories));
        Optional.ofNullable(longSummaryStatistics)
                .ifPresent(System.out::println);
        //LongSummaryStatistics{count=9, sum=4200, min=120, average=466.666667, max=800}
    }

    public static void testGroupingByConcurrentWithFunction() {
        // A {@link java.util.Map} providing thread safety and atomicity
        // * guarantees.
        ConcurrentMap<Dish.Type, List<Dish>> typeListConcurrentMap =
                menu.stream().collect(groupingByConcurrent(Dish::getType));
        Optional.of(typeListConcurrentMap)
                .ifPresent(System.out::println);
        Optional.of(typeListConcurrentMap.getClass())
                .ifPresent(System.out::println);
    }

    public static void testGroupingByConcurrentWithFunctionAndCollector() {

        ConcurrentMap<Dish.Type, Integer> collect = menu.stream()
                .collect(groupingByConcurrent(Dish::getType, summingInt(Dish::getCalories)));
        Optional.of(collect)
                .ifPresent(System.out::println);
    }

    public static void testGroupingByConcurrentWithFunctionAndSupplierAndCollector() {

        ConcurrentSkipListMap<Dish.Type, Integer> collect = menu.stream()
                .collect(groupingByConcurrent(Dish::getType, ConcurrentSkipListMap::new, summingInt(Dish::getCalories)));
        Optional.of(collect)
                .ifPresent(System.out::println);
    }

    public static void testJoining() {
        String string = menu.stream().map(Dish::getName).collect(joining());
        String stringDelimiter = menu.stream().map(Dish::getName).collect(joining(","));
        String stringDelimiterAndPrefixSuffix = menu.stream().map(Dish::getName).collect(joining(",", "Name={", "}"));
        Optional.ofNullable(string)
                .ifPresent(System.out::println);
        Optional.ofNullable(stringDelimiter)
                .ifPresent(System.out::println);
        Optional.ofNullable(stringDelimiterAndPrefixSuffix)
                .ifPresent(System.out::println);
    }

    public static void testMapping() {
        String mappingRes = menu.stream().collect(mapping(Dish::getName, joining(",")));
        Optional.of(mappingRes).ifPresent(System.out::println);
    }

    public static void testMaxBy() {
        Optional<Dish> maxBy = menu.stream().collect(maxBy(comparing(Dish::getCalories)));
        Optional.of(maxBy.get()).ifPresent(System.out::println);
    }

    public static void testMinBy() {
        Optional<Dish> minBy = menu.stream().collect(minBy(comparing(Dish::getCalories)));
        Optional.of(minBy.get()).ifPresent(System.out::println);
    }

    public static void testPartitioningByWithPredicate() {
        Map<Boolean, List<Dish>> partitionBy = menu.stream().collect(partitioningBy(Dish::isVegetarian));
        Optional.ofNullable(partitionBy).ifPresent(System.out::println);
    }

    public static void testPartitioningByWithPredicateAndCollector() {
        Map<Boolean, Integer> map = menu.stream()
                .collect(partitioningBy(Dish::isVegetarian, summingInt(Dish::getCalories)));
        Optional.ofNullable(map).ifPresent(System.out::println);
    }

    public static void testReducingBinaryOperator() {
        Optional.ofNullable(
                menu.stream().collect(
                        reducing(BinaryOperator.maxBy(comparing(Dish::getCalories)))
                )
        ).ifPresent(System.out::println);
    }

    public static void testReducingBinaryOperatorAndIdentity() {
        Optional.ofNullable(
                menu.stream().map(Dish::getCalories)
                        .collect(reducing(0, (d1, d2) -> d1 + d2))// 4200       d1-d2 ------  -4200
        ).ifPresent(System.out::println);
    }

    /*
    summarizing statistics:
            count
            sum
            min
            average
            max
     */
    public static void testSummarizing() {
        Optional.ofNullable(menu.stream().collect(Collectors.summarizingInt(Dish::getCalories)))
                .ifPresent(System.out::println);
        Optional.ofNullable(menu.stream().collect(Collectors.summarizingDouble(Dish::getCalories)))
                .ifPresent(System.out::println);
        Optional.ofNullable(menu.stream().collect(Collectors.summarizingLong(Dish::getCalories)))
                .ifPresent(System.out::println);
    }

    public static void testSumming() {
        Optional.ofNullable(menu.stream().collect(summingInt(Dish::getCalories))).ifPresent(System.out::println);
        Optional.ofNullable(menu.stream().collect(summingDouble(Dish::getCalories))).ifPresent(System.out::println);
        Optional.ofNullable(menu.stream().collect(summingLong(Dish::getCalories))).ifPresent(System.out::println);

        Optional.of(menu.stream().map(Dish::getCalories).mapToInt(Integer::intValue).sum())
                .ifPresent(System.out::println);
    }

    public static void testToCollection() {
        Optional<LinkedList<Dish>> dishLinkedList = Optional.of(menu.stream()
                .filter(d -> d.getCalories() > 600)
                .collect(toCollection(LinkedList::new)));
        dishLinkedList.ifPresent(System.out::println);
    }

    public static void testToConcurrentMap() {
        Optional<ConcurrentMap<String, Integer>> concurrentMap = Optional.ofNullable(menu.stream()
                .filter(dish -> dish.getType().equals(Dish.Type.MEAT))
                .collect(toConcurrentMap(Dish::getName, Dish::getCalories)));
//        ConcurrentMap<String, Integer> stringIntegerConcurrentMap = concurrentMap.orElse(new ConcurrentHashMap<>());
        concurrentMap.ifPresent(System.out::println);
    }

    public static void testToConcurrentMapWithBinaryOperator() {
        Optional<ConcurrentMap<String, Integer>> collect = Optional.ofNullable(menu.stream()
                .collect(toConcurrentMap(Dish::getName, Dish::getCalories, (d1, d2) -> d1 + d2))
        );
        collect.ifPresent(a -> {
            System.out.println(a);
            System.out.println(a.getClass());
        });
    }

    public static void testToConcurrentMapWithBinaryOperatorAndSupplier() {
        Optional<ConcurrentSkipListMap<String, Integer>> collect = Optional.ofNullable(menu.stream()
                .collect(toConcurrentMap(Dish::getName,
                        Dish::getCalories, (d1, d2) -> d1 + d2,
                        ConcurrentSkipListMap::new))
        );
        collect.ifPresent(a -> {
            System.out.println(a);
            System.out.println(a.getClass());
        });
    }

    public static void testToList() {
        Optional.of(menu.stream().filter(dish -> dish.getCalories() < 500).collect(toList())).ifPresent(
                a -> {
                    System.out.println(a);
                    System.out.println(a.getClass());
                }
        );
    }

    public static void testToSet() {
        Set<Dish.Type> dishSet = menu.stream().map(Dish::getType)
                .collect(toSet());
        Optional.of(dishSet).ifPresent(
                a -> {
                    System.out.println(a);
                    System.out.println(a.getClass());
                }
        );
    }

    public static void testToMap() {
        Optional.ofNullable(menu.stream()
                .collect(collectingAndThen(
                        toMap(Dish::getName, Dish::getCalories),
                        Collections::synchronizedMap
                )))
                .ifPresent(a -> {
                    System.out.println(a);
                    System.out.println(a.getClass());
                });
        Map<Thread, StackTraceElement[]> allStackTraces = Thread.getAllStackTraces();
        for (Map.Entry<Thread, StackTraceElement[]> entry : allStackTraces.entrySet()) {
            Thread key = entry.getKey();
            StackTraceElement[] value = entry.getValue();

            if (key.getId() != Thread.currentThread().getId())
                continue;
            System.out.println("============" + key.getName());
            for (StackTraceElement element : value) {
                if (element.isNativeMethod())
                    continue;
                System.out.println(element.getClassName());
                System.out.println("isNativeMethod " + element.isNativeMethod());
                System.out.println(element.getMethodName());
                System.out.println(element.getLineNumber());
                System.out.println(element.getFileName());
                System.out.println("-------------");
            }
        }
    }

    public static void testToMapWithBinaryOperator() {
        Optional.of(menu.stream().filter(dish -> dish.getCalories() > 400)
                .collect(toMap(Dish::getName, Dish::getCalories, (d1, d2) -> d1 + d2)))
                .ifPresent(a -> {
                    System.out.println(a);
                    System.out.println(a.getClass());
                });
        //{pizza=550, salmon=450, beef=700, pork=800, french fries=530}
    }

    public static void testToMapWithBinaryOperatorAndSupplier() {
        Optional.of(menu.stream().filter(dish -> dish.getCalories() > 400)
                .collect(toMap(Dish::getName, Dish::getCalories, (d1, d2) -> d1 + d2,Hashtable::new)))
                .ifPresent(a -> {
                    System.out.println(a);
                    System.out.println(a.getClass());
                });
        //{pizza=550, salmon=450, beef=700, pork=800, french fries=530}
    }

    public static void main(String[] args) {
        testToMapWithBinaryOperatorAndSupplier();
//        testToMapWithBinaryOperator();
//        testToMap();
//        testToSet();
//        testToList();
//        testToConcurrentMapWithBinaryOperatorAndSupplier();
//        testToConcurrentMapWithBinaryOperator();
//        testToConcurrentMap();
//        testToCollection();

//        System.out.println((byte) 4200);
//        System.out.println(4200 % 127);

//        testSumming();
//        testSummarizing();
//        testReducingBinaryOperatorAndIdentity();
//        testReducingBinaryOperator();
//        testPartitioningByWithPredicate();
//        testPartitioningByWithPredicateAndCollector();
//        testMaxBy();
//        testMinBy();
//        testMapping();
//        testJoining();
//        testGroupingByConcurrentWithFunctionAndSupplierAndCollector();
//        testGroupingByConcurrentWithFunctionAndCollector();
//        testGroupingByConcurrentWithFunction();
//        testSummarizingInt();
//        testSummarizingDouble();
//        testSummarizingLong();
//        testGroupingByFunctionAndSupplierAndCollector();


//        testAveragingXXX();
//        testCollectingAndThen();
//        testCounting();
//        testGroupingByFunction();
//        testGroupingByFunctionAndCollector();
    }

    public static List<Dish> menu = Arrays.asList(
            new Dish("pork", false, 800, Dish.Type.MEAT),
            new Dish("beef", false, 700, Dish.Type.MEAT),
            new Dish("chicken", false, 400, Dish.Type.MEAT),
            new Dish("french fries", true, 530, Dish.Type.OTHER),
            new Dish("rice", true, 350, Dish.Type.OTHER),
            new Dish("season fruit", true, 120, Dish.Type.OTHER),
            new Dish("pizza", true, 550, Dish.Type.OTHER),
            new Dish("prawns", false, 300, Dish.Type.FISH),
            new Dish("salmon", false, 450, Dish.Type.FISH));
}
