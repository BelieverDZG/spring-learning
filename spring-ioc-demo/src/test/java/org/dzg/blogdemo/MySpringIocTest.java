package org.dzg.blogdemo;

import org.dzg.blogdemo.impl.DefaultBeanDefinition;
import org.dzg.blogdemo.impl.DefaultBeanFactory;
import org.dzg.pojo.Customer;
import org.junit.Test;

public class MySpringIocTest {
    static DefaultBeanFactory factory = new DefaultBeanFactory();

    @Test
    public void test(){
        DefaultBeanDefinition dbd = new DefaultBeanDefinition();
        dbd.setClazz(Customer.class);
        dbd.setSingleton(true);
        dbd.setBeanFactoryName("TestFactory");
        dbd.setCreateBeanMethodName("createMethod");
        dbd.setStaticCreateBeanMethodName("staticCreateMethod");
        dbd.setBeanInitMethodName("init");

        factory.register(dbd,"customer");

        System.out.println(factory.doGetBean("customer"));
    }
}
