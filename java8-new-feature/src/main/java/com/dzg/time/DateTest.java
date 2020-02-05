package com.dzg.time;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.temporal.ChronoField;
import java.util.Date;

public class DateTest {
    public static void main(String[] args) {
        /*Date date = new Date();
        System.out.println(date);
        //传统的SimpleDateFormat存在线程安全问题
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        for (int i = 0; i < 30; i++) {
            new Thread(()->{
                Date parseDate = null;
                for (int j = 0; j < 1000; j++) {
                    try {
                        parseDate = sdf.parse("2020-02-05");
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    System.out.println(parseDate);
                }
            }).start();
        }*/

//        testLocalDate();
        testLocalTime();
    }

    public static void testLocalDate(){
        LocalDate localDate = LocalDate.now();
        System.out.println(localDate.getDayOfYear());
        System.out.println(localDate.getDayOfMonth());
        System.out.println(localDate.getDayOfWeek());
        int i = localDate.get(ChronoField.ALIGNED_DAY_OF_WEEK_IN_MONTH);
        System.out.println(i);
    }

    public static void testLocalTime(){
        LocalTime localTime = LocalTime.now();
        System.out.println(localTime);
        System.out.println(localTime.getHour());
        System.out.println(localTime.getMinute());
        System.out.println(localTime.getSecond());
    }


}
