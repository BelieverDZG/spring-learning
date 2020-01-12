package org.dzg.blogdemo;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;

public interface BeanDefinition {
    String SINGLETON = ConfigurableBeanFactory.SCOPE_SINGLETON;
    String PROTOTYPE = ConfigurableBeanFactory.SCOPE_PROTOTYPE;

    Class<?> getBeanClass();
    String getBeanFactory();
    String getBeanCreateMethod();
    String getStaticCreateBeanMethod();
    String getBeanInitMethod();
    String getBeanDestroyMethod();
    String getScope();
    boolean isSingleton();
    boolean isPrototype();
    boolean validate(); // ???怎么验证

}
