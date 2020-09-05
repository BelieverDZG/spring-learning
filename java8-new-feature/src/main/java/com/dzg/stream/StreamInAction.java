package com.dzg.stream;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class StreamInAction {
    public static void main(String[] args) {

        Trader raoul = new Trader("Raoul", "Cambridge");
        Trader mario = new Trader("Mario", "Milan");
        Trader alan = new Trader("Alan", "Cambridge");
        Trader brian = new Trader("Brian", "Cambridge");

        List<Transaction> transactions = Arrays.asList(
                new Transaction(brian, 2011, 300),
                new Transaction(raoul, 2012, 1000),
                new Transaction(raoul, 2011, 400),
                new Transaction(mario, 2012, 710),
                new Transaction(mario, 2012, 700),
                new Transaction(alan, 2012, 950)
        );

        //1. Find all transactions in the year 2011 and sort them by value (small to high).
        transactions.stream().filter(transaction -> transaction.getYear() == 2011)
                .sorted(Comparator.comparingInt(Transaction::getYear))
                .collect(Collectors.toList())
                .forEach(System.out::println);

        //2.What are all the unique cities where the traders work?
        transactions.stream().map(transaction -> transaction.getTrader().getCity())
                .distinct()
                .collect(Collectors.toList())
                .forEach(System.out::println);

        System.out.println("==========================");
        //3.Find all traders from Cambridge and sort them by name.
        //method one
        transactions.stream()
                .map(Transaction::getTrader)
                .filter(transaction -> transaction.getCity().equals("Cambridge"))
                .sorted(Comparator.comparing(Trader::getName))
                .distinct()
                .forEach(System.out::println);
        System.out.println("================================");

        //4.Return a string of all traders’ names sorted alphabetically
        //method one
        String traderList = transactions.stream().map(Transaction::getTrader)
                .map(Trader::getName)
                .sorted()
                .distinct()
                .collect(Collectors.toList())
                .toString();
//                .forEach(System.out::println);
        System.out.println(traderList);

        //method two
        String value = transactions.stream().map(t -> t.getTrader().getName())
                .distinct()
                .sorted()
                .reduce("", (name1, name2) -> name1 + " " + name2);
        System.out.println(value);
        System.out.println("========================");
        //5. Are any traders based in Milan?
        boolean res1 = transactions.stream().map(Transaction::getTrader).anyMatch(trader -> trader.getCity().equals("Milan"));
        System.out.println(res1);//true
        boolean res2 = transactions.stream().anyMatch(t -> t.getTrader().getCity().equals("Milan"));
        System.out.println(res2);//true
        System.out.println("==============================");
        //6.Print all transactions’ values from the traders living in Cambridge.
        transactions.stream().filter(transaction -> transaction.getTrader().getCity().equals("Cambridge"))
                .map(Transaction::getValue)
                .sorted()
                .forEach(System.out::println);

        System.out.println("===========================");
        //7.What’s the highest value of all the transactions?
        int max = transactions.stream().map(Transaction::getValue)
//                .max(Comparator.comparing(t->t.intValue()))
//                .ifPresent(a -> System.out.println(a));//1000
                .reduce((i, j) -> i > j ? i : j)
                .get();
        System.out.println(max);

        System.out.println("==================");
        //8.Find the transaction with the smallest value.
        int min = transactions.stream().map(Transaction::getValue)
//                .min(Comparator.comparing(a->a.intValue()))
//                .ifPresent(a-> System.out.println(a)); 300
                .reduce((i, j) -> i < j ? i : j)
                .get();
        System.out.println(min);//300
    }

}
