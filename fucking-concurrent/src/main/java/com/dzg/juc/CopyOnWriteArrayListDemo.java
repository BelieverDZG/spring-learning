package com.dzg.juc;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class CopyOnWriteArrayListDemo {

    public static void main(String[] args) {

        List<String> slists = new CopyOnWriteArrayList<>();
        slists.add("1");
        slists.add("11");
        slists.add("111");
        System.out.println(slists);
        for (String slist : slists) {
            if (slist.equals("111")){
                slists.remove(slist);
            }
        }
        System.out.println(slists);

        List<String> lists = new ArrayList<>();
        lists.add("1");
        lists.add("11");
        lists.add("111");
//        for (String str : lists){
//            if (str.equals("111")){
//                lists.remove("111");
////                break;
//            }
//        }
        System.out.println(lists);
        lists.removeIf(next -> next.equals("111"));
        System.out.println(lists);

        int cnt = 0;
        for (int i = 1; i <= 1000 ; i++) {
            if (i % 5 == 0)cnt += i / 5;
        }
        System.out.println(cnt);
        BigDecimal n = new BigDecimal(1000);
        for (int i = 999; i > 0 ; i--) {
            n = n.multiply(new BigDecimal(i));
        }
        int count = 0;
        char[] chs = n.toString().toCharArray();
        for (int i = chs.length - 1; i >= 0; i--){
            if (chs[i] == '0') {
                count++;
            }else break;
        }
        System.out.println(n);
        System.out.println(count);
        System.out.println(chs.length);
        int m = 1000;
        int c = 0;
        while (m / 5 != 0){
            c += m / 5;
            m /= 5;
        }
        System.out.println(c);
    }
}
