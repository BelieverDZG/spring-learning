package com.dzg.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * 方法调用处理器
 *          基于接口的动态代理
 */
public class DynamicProxyHandler implements InvocationHandler {

    //我们所要代理的真实对象
    private Object proxyObject;

    public DynamicProxyHandler(Object proxyObject) {
        this.proxyObject = proxyObject;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        /**
         * 调用对象前可以添加一些真实的操作
         */
        System.out.println("======调用对象前可以添加一些真实的操作====="+"\n");
        System.out.println("method name -->"+method.getName());
        // 当代理对象调用真实对象的方法时，其会自动的跳转到代理对象关联的handler对象的invoke方法来进行调用
        Object invoke = method.invoke(proxyObject, args);

        //代理真实对象后，可进行一些操作
        System.out.println("==========代理真实对象后，可进行一些操作========="+"\n");
        return null;
    }
}
