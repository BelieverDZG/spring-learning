package com.dzg.defaults;

public class DefaultInAction {

    public static void main(String[] args) {
        D d = () -> 10;
//        D d = i-> i;
//        System.out.println(d.reference(1000));
        System.out.println(d.size());
        System.out.println(d.isEmpty());
    }

    interface D {
        int size();

        default boolean isEmpty() {
            return size() == 0;
        }

//        int reference(int ref);
    }
}
