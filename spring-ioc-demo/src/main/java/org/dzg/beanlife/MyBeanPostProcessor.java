package org.dzg.beanlife;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

public class MyBeanPostProcessor implements BeanPostProcessor {
    /*
    容器加载的时候会加载一些其他的bean，会调用初始化前和初始化后的方法
    这次只关注book（bean）的声明周期
     */

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        if (bean instanceof Book){
            System.out.println("MyBeanPostProcessor.postProcessBeforeInitialization invoke");
        }
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if (bean instanceof Book){
            System.out.println("MyBeanPostProcessor.postProcessAfterInitialization invoke");
        }
        return bean;
    }
}
