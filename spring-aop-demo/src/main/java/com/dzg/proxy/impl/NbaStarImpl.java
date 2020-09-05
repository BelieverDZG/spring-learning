package com.dzg.proxy.impl;

import com.dzg.proxy.INbaStar;

public class NbaStarImpl implements INbaStar {

    @Override
    public void basketball() {
        System.out.println("我想打篮球！！！");
    }

    @Override
    public void dance() {
        System.out.println("我不太会跳");
    }

    @Override
    public String rap(String song) {
        System.out.println("I can rap " + song);
        return "Sing " + song + " for me !";
    }
}
