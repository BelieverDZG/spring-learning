package org.dzg.beanlife;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

public class Book implements BeanNameAware,
        BeanFactoryAware, ApplicationContextAware,
        InitializingBean, DisposableBean {

    private String bookName;

    public Book() {
        System.out.println("Book initializing....");
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
        System.out.println("bookName has been set");
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        System.out.println("invoke Book.setBeanFactory");
    }

    @Override
    public void setBeanName(String name) {
        System.out.println("invoke Book.setBeanName");
    }

    @Override
    public void destroy() throws Exception {
        System.out.println("invoke Book.destroy");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("invoke Book.afterPropertiesSet");
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        System.out.println("invoke Book.setApplicationContext");
    }

    public void myPostConstruct(){
        System.out.println("invoke Book.myPostConstruct");
    }

    //自定义初始化方法
    @PostConstruct
    public void springPostConstruct(){
        System.out.println("@Construct");
    }

    public void myPreDestroy(){
        System.out.println("Book.myPreDestroy");
        System.out.println("============destroy=============");
    }

    //自定义销毁方法
    @PreDestroy
    public void springPreDestroy(){
        System.out.println("@PreDestroy");
    }

    @Override
    protected void finalize() throws Throwable {
        System.out.println("================inside finalize====================");
    }
}
