package com.dzg.test;

import com.dzg.service.IAccountService;
import org.aspectj.lang.ProceedingJoinPoint;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class AopUsageTest {

    public static void main(String[] args) {
        //获取容器和对象
        ApplicationContext ac = new ClassPathXmlApplicationContext("beans.xml");
        IAccountService service = (IAccountService) ac.getBean("accountService");
        service.saveAccount();
    }
}
