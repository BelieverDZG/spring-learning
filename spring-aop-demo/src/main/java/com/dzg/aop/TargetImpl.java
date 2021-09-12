package com.dzg.aop;

/**
 * @Author deng zhiguo
 * @Date 2021/9/12 13:41
 */
public class TargetImpl implements MyTarget{
    @Override
    public void run() {
        System.out.println("target running ......");
    }
}
