package com.dzg.juc;

import java.io.*;
import java.util.*;
import java.util.concurrent.atomic.LongAdder;
import java.util.function.BiConsumer;
import java.util.function.Supplier;
import java.util.stream.Collectors;

/**
 * 单词统计
 */
public class WordCountDemo {

    static final String BASIC = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

    public static void main(String[] args) throws IOException {


        demo(
                () -> new HashMap<String, LongAdder>(),
                (map,words) -> {
                    for (String word : words){
                        //如果缺少一个key，则计算生成一个value，然后将key value放入map
                        LongAdder val = map.computeIfAbsent(word, key -> new LongAdder());
                        //执行累加
                        val.increment();
//                        map.put(word,map.getOrDefault(word,0) + 1);
//                        Integer counter = map.get(word);
//                        int newValue = counter == null ? 1 : counter + 1;
//                        map.put(word,newValue);
                    }
                }
        );
    }


    /**
     * //模板代码
     *
     * @param supplier 提供输入数据
     * @param consumer 根据输入数据进行相应的操作
     * @param <V>
     */
    private static <V> void demo(Supplier<Map<String, V>> supplier,
                                 BiConsumer<Map<String, V>, List<String>> consumer) {
        List<Thread> ts = new ArrayList<>();
        Map<String, V> counterMap = supplier.get();
        for (int i = 0; i < 26; i++) {
            int idx = i;
            Thread thread = new Thread(() -> {
                List<String> words = readFromFile(idx);
                consumer.accept(counterMap, words);
            });
            ts.add(thread);
        }
        ts.forEach(t -> t.start());
        ts.forEach(t -> {
            try {
                t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        System.out.println(counterMap);
    }

    private static List<String> readFromFile(int idx) {
        ArrayList<String> words = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(new FileInputStream("tmp/" + (idx + 1) + ".txt")))) {
//            下述单词数据的方式不能够正确读取 ！
//            char[] buffer = new char[1024];
//            while (reader.read(buffer) != -1) {
//                words.add(reader.readLine());
//            }
            while (true){
                String word = reader.readLine();
                if (word == null){
                    break;
                }
                words.add(word);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return words;
    }

    private static void createDataStatitics() {
        int len = BASIC.length();
        int count = 200;
        List<String> list = new ArrayList<>(len * count);
        for (int i = 0; i < len; i++) {
            char ch = BASIC.charAt(i);
            for (int j = 0; j < count; j++) {
                list.add(String.valueOf(ch));
            }
        }
        Collections.shuffle(list);
        //首先创建目录，mkdir，然后创建文件 createNewFile

        for (int i = 0; i < 26; i++) {
            try (PrintWriter pw = new PrintWriter(new OutputStreamWriter((
                    new FileOutputStream("tmp/" + (i + 1) + ".txt")))) {
            }) {
                String collect = list.subList(i * count, (i + 1) * count).stream()
                        .collect(Collectors.joining("\n"));
                pw.write(collect);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    private static void createFileData() throws IOException {
        for (int i = 0; i < 26; i++) {
            File file = new File("tmp/" + (i + 1) + ".txt");
            if (!file.exists()) {
                file.createNewFile();
//                file.delete();
            }

        }
    }

}
