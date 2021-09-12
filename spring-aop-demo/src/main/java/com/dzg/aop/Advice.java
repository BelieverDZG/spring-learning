package com.dzg.aop;

/**
 * @Author deng zhiguo
 * @Date 2021/9/12 13:51
 */
public class Advice {

    public void before() {
        System.out.println("前置增强....");
    }

    public void afterReturning() {
        System.out.println("后置增强....");
    }
}
