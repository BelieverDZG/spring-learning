package org.dzg.blogdemo.impl;


import org.dzg.blogdemo.BeanDefinition;

public class DefaultBeanDefinition implements BeanDefinition {

    private Class<?> clazz;
    private String beanFactoryName;
    private String createBeanMethodName;
    private String staticCreateBeanMethodName;
    private String beanInitMethodName;
    private String beanDestroyMethodName;
    private Boolean isSingleton;

    public Class<?> getClazz() {
        return clazz;
    }

    public void setClazz(Class<?> clazz) {
        this.clazz = clazz;
    }

    public String getBeanFactoryName() {
        return beanFactoryName;
    }

    public void setBeanFactoryName(String beanFactoryName) {
        this.beanFactoryName = beanFactoryName;
    }

    public String getCreateBeanMethodName() {
        return createBeanMethodName;
    }

    public void setCreateBeanMethodName(String createBeanMethodName) {
        this.createBeanMethodName = createBeanMethodName;
    }

    public String getStaticCreateBeanMethodName() {
        return staticCreateBeanMethodName;
    }

    public void setStaticCreateBeanMethodName(String staticCreateBeanMethodName) {
        this.staticCreateBeanMethodName = staticCreateBeanMethodName;
    }

    public String getBeanInitMethodName() {
        return beanInitMethodName;
    }

    public void setBeanInitMethodName(String beanInitMethodName) {
        this.beanInitMethodName = beanInitMethodName;
    }

    public String getBeanDestroyMethodName() {
        return beanDestroyMethodName;
    }

    public void setBeanDestroyMethodName(String beanDestroyMethodName) {
        this.beanDestroyMethodName = beanDestroyMethodName;
    }

    public Boolean getSingleton() {
        return isSingleton;
    }

    public void setSingleton(Boolean singleton) {
        isSingleton = singleton;
    }

    @Override
    public Class getBeanClass() {
        return this.clazz;
    }

    @Override
    public String getBeanFactory() {
        return this.beanFactoryName;
    }

    @Override
    public String getBeanCreateMethod() {
        return this.createBeanMethodName;
    }

    @Override
    public String getStaticCreateBeanMethod() {
        return this.staticCreateBeanMethodName;
    }

    @Override
    public String getBeanInitMethod() {
        return this.beanInitMethodName;
    }

    @Override
    public String getBeanDestroyMethod() {
        return this.beanDestroyMethodName;
    }

    @Override
    public String getScope() {
        return this.isSingleton?BeanDefinition.SINGLETON:BeanDefinition.PROTOTYPE;
    }

    @Override
    public boolean isSingleton() {
        return this.isSingleton;
    }

    @Override
    public boolean isPrototype() {
        return !this.isSingleton; //不是singleton模式，即为prototype类型
    }

    @Override
    public boolean validate() {
        return false;
    }

}
