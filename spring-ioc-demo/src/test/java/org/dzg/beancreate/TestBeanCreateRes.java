package org.dzg.beancreate;

import org.dzg.beancreate.config.SpringBeansConfig;
import org.dzg.beancreate.controller.MyController;
import org.dzg.beancreate.pojo.Customer;
import org.dzg.beancreate.service.impl.CustomerServiceImpl;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;

public class TestBeanCreateRes {

    @Test
    public void test(){

        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SpringBeansConfig.class);
        CustomerServiceImpl service = context.getBean("customerService",CustomerServiceImpl.class);
        List<Customer> customers = service.findAll();
        for (Customer c:
             customers) {
            System.out.println(c);
        }
    }
}
