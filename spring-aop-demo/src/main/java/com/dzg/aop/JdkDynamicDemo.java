package com.dzg.aop;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @Author deng zhiguo
 * @Date 2021/9/12 13:42
 */
public class JdkDynamicDemo {

    public static void main(String[] args) {
        //目标对象
        TargetImpl target = new TargetImpl();

        //增强对象(切面)
        Advice advice = new Advice();

        MyTarget instance = (MyTarget) Proxy.newProxyInstance(
                //目标代理类加载器
                target.getClass().getClassLoader(),
                //目标对象接口字节码对象数组
                target.getClass().getInterfaces(),
                //调用代理对象的任何方法，实际执行的都是invoke方法
                new InvocationHandler() {
                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args)
                            throws Throwable {
                        advice.before();
                        //执行目标方法
                        method.invoke(target, args);
                        advice.afterReturning();
                        return null;
                    }
                }
        );
        System.out.println(instance);
        System.out.println("===============");
        instance.run();

    }
}
