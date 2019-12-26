package com.dzg.domain;

public class Greeting {

    private final long id;//final值没有对应的set方法，直接通过构造函数初始化
    private final String content;

    public long getId() {
        return id;
    }

    public String getName() {
        return content;
    }

    public Greeting(long id, String content) {
        this.id = id;
        this.content = content;
    }
}
