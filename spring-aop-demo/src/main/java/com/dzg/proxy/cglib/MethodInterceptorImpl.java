package com.dzg.proxy.cglib;

import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

public class MethodInterceptorImpl implements MethodInterceptor {

    /**
     *
     * @param object
     * @param method
     * @param args
     * @param methodProxy
     * @return
     * @throws Throwable
     */
    @Override
    public Object intercept(Object object, Method method, Object[] args, MethodProxy methodProxy)
            throws Throwable {

        System.out.println("开始Cglib动态代理");
        Object invokeObj = methodProxy.invokeSuper(object, args);
        System.out.println("Cglib动态代理结束");

        return invokeObj;
    }
}
