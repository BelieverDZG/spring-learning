package com.dzg.stream;

import org.omg.CORBA.PUBLIC_MEMBER;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.function.Supplier;
import java.util.stream.Stream;

/**
 * 创建流的常用方式
 */
public class CreateStream {
    public static void main(String[] args) {

//        createStreamFromCollection().forEach(System.out::println);
//        createStreamFromValues().forEach(System.out::println);
//        createStreamFromArrays().forEach(System.out::println);
        //有bug？
        String path = "F:\\clonedir\\spring-learning\\java8-new-feature\\src\\main\\java\\com\\dzg\\stream\\Typee.java";
        Stream<String> streamFromFile = createStreamFromFile(path);
        System.out.println(streamFromFile);
//        streamFromFile.forEach(System.out::println);//stream has already been operated upon or closed
//        createStreamFromIterator().forEach(System.out::println);
//        createStreamFromGenerate().forEach(System.out::println);
//        createObjStreamFromGenerate().forEach(System.out::println);
    }

    public static Stream<String> createStreamFromCollection() {
        List<String> test = Arrays.asList("中国加油", "武汉加油", "我们必胜");
        return test.stream();
    }

    public static Stream<String> createStreamFromValues() {
        return Stream.of("中国加油", "武汉加油", "我们必胜");
    }

    public static Stream<String> createStreamFromArrays() {
        String[] array = {"中国加油", "武汉加油", "我们必胜", "中国加油", "武汉加油", "我们必胜"};
        return Arrays.stream(array);
    }

    public static Stream<String> createStreamFromFile(String path) {
        Path paths = Paths.get(path);
        try (Stream<String> streamFromFile = Files.lines(paths)) {
            streamFromFile.forEach(System.out::println);
            return streamFromFile;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public static Stream<Integer> createStreamFromIterator() {
//        Stream<Integer> integerStream = Stream.iterate(0, n -> n + 2);生成无限多的偶数
        Stream<Integer> integerStream = Stream.iterate(0, n -> n + 2).limit(10);
        return integerStream;
    }

    public static Stream<Double> createStreamFromGenerate() {
        return Stream.generate(Math::random).limit(10);
    }

    public static Stream<Obj> createObjStreamFromGenerate() {
        return Stream.generate(new ObjSupplier()).limit(10);
    }

    static class ObjSupplier implements Supplier<Obj> {

        private int index = 0;
        private Random random = new Random(System.currentTimeMillis());

        @Override
        public Obj get() {
            index = random.nextInt(10);
            return new Obj(index, "name ->" + index);
        }
    }

    static class Obj {
        private int id;
        private String name;

        public Obj(int id, String name) {
            this.id = id;
            this.name = name;
        }

        public int getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        @Override
        public String toString() {
            return "Obj{" +
                    "id=" + id +
                    ", name='" + name + '\'' +
                    '}';
        }
    }
}
