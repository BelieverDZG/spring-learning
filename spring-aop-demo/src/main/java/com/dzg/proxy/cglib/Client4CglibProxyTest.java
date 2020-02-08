package com.dzg.proxy.cglib;

import com.dzg.proxy.INbaStar;
import com.dzg.proxy.impl.NbaStarImpl;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * 基于子类的动态代理
 */
public class Client4CglibProxyTest {

    private static final INbaStar nbaStar = new NbaStarImpl();

    public static void main(String[] args) {

        /*
        //可以看出使用Enhancer生成代理类，需要设置被代理类，
        //也就是父类（从这里可以看出是使用继承，生成的子类），设置回调方法
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(NbaStarImpl.class);//体现继承
        enhancer.setCallback(new MethodInterceptorImpl());

        //生成代理类
        NbaStarImpl star = (NbaStarImpl) enhancer.create();
        star.rap("HHH");
        System.out.println("==========");
        star.dance();*/

        MethodInterceptor methodInterceptor = new MethodInterceptorImpl();
        NbaStarImpl star = (NbaStarImpl) Enhancer.create(nbaStar.getClass(), methodInterceptor);
        star.basketball();

        /**
         * 使用匿名内部类的方式
         */
//        Class<? extends INbaStar> aClass = nbaStar.getClass();
        NbaStarImpl cglibStar = (NbaStarImpl) Enhancer.create(nbaStar.getClass(), new MethodInterceptor() {
            /**
             *执行代理对象的任何方法都会经过该方法
             * @param o
             * @param method
             * @param objects
            以上三更参数和基于接口的动态代理中invoke方法参数一样
             * @param methodProxy 当前执行方法的代理对象
             * @return
             * @throws Throwable
             */
            @Override
            public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {

                System.out.println("匿名内部类Cglib动态代理开始");
                methodProxy.invoke(nbaStar, objects);
                System.out.println("匿名内部类Cglib动态代理结束");
                return null;
            }
        });

        cglibStar.rap("中国加油");
    }
}
