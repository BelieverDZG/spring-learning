package org.dzg.blogdemo;

public interface BeanDefinitionRegistry {

    void register(BeanDefinition bd, String beanName);
    BeanDefinition getBeanDefinition(String beanName);
    boolean containsBeanDefinition(String beanName);
}
