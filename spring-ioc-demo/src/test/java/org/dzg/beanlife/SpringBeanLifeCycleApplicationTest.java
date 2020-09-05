package org.dzg.beanlife;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringBeanLifeCycleApplicationTest {

    @Test
    public void beanLifeCycleTest(){
        //面试回答下述问题即可
        ApplicationContext ac = new ClassPathXmlApplicationContext("bean-lifecycle.xml");

        Book book = (Book) ac.getBean("book");
        book.setBookName("Java8 In Action");
        System.out.println("Book name "+book.getBookName());
        ((ClassPathXmlApplicationContext)ac).destroy();
    }


    @Test
    public void beanFullLifeCycleTest(){
        // 完整的加载过程，当然了解的越多越好
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("SubBean-Lifecycle.xml");
        SubBookClass subBookClass = (SubBookClass) applicationContext.getBean("bookClass");
        System.out.println("BookSystemName = " + subBookClass.getBookSystem());
        ((ClassPathXmlApplicationContext) applicationContext).registerShutdownHook();
    }

}
