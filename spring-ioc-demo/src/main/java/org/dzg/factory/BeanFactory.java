package org.dzg.factory;

import org.springframework.beans.factory.config.BeanPostProcessor;

public interface BeanFactory {

    Object getBean(String beanName);
    boolean registerBeanPostProcessor(BeanPostProcessor processor);

}
