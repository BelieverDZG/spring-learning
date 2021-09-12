package com.dzg.proxy;

import com.dzg.proxy.impl.NbaStarImpl;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class Client4ProxyTest {

    public static void main(String[] args) {

        //要代理的真实对象
        INbaStar nbaStar = new NbaStarImpl();

       /* //将要代理的真实对象注入代理，通过真实对象来调用其方法
        InvocationHandler handler = new DynamicProxyHandler(nbaStar);

        *//**
         * 通过 Proxy.newProxyInstance 创建的代理对象是在jvm运行时动态生成的一个对象，
         * 它并不是我们的InvocationHandler类型，也不是我们定义的那组接口的类型，
         * 而是在运行时动态生成的一个对象，并且命名方式都是这样的形式，
         * 以$开头，proxy为中，最后一个数字表示对象的标号。
         *//*
        INbaStar instance = (INbaStar) Proxy.newProxyInstance(handler.getClass().getClassLoader(),
                nbaStar.getClass().getInterfaces(), handler);

        instance.rap("Believer");

        System.out.println("代理实例名称为-->" + instance.getClass().getName());
*/

        /**
         * newProxyInstance 方法中第三个参数为接口，可以使用匿名内部类的方式来实现动态代理
         */
        INbaStar iNbaStar = (INbaStar) Proxy.newProxyInstance(nbaStar.getClass().getClassLoader(),
                nbaStar.getClass().getInterfaces(),
                (proxy, method, args1) -> {

                    System.out.println(proxy.getClass().getName());
                    if (method.getName().equals("dance")) {
                        System.out.println("dance......");
                        method.invoke(nbaStar, args1);
                    } else if (method.getName().equals("basketball")) {
                        System.out.println("basketball......");
                        method.invoke(nbaStar, args1);
                    }

                    return null;
                });

        iNbaStar.basketball();
    }
}
