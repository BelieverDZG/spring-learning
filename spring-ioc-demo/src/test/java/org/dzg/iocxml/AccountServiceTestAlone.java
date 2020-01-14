package org.dzg.iocxml;

import org.dzg.iocxml.domain.Account;
import org.dzg.iocxml.service.IAccountService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

/**
 * 使用juint单元测试，测试配置
 *
 *   spring综合junit的配置
 *    1、导入jar包
 *    2、使用Junit提供的一个注解把原有的main方法替换，替换成spring提供的
 *           @Runwith
 *    3.告知spring的运行器，spring和ioc创建的是基于xml还是注解的，并说明位置
 *           @ContextConfiguration
 *                     locations:指定xml文件的位置，加上classpath关键字，表示在类路径下
 *                     classes：指定注解类所在的位置
 */

public class AccountServiceTestAlone {

    @Autowired
    private IAccountService as = null;

    @Test
    public void testFindAll(){
        //1.获取数据信息
        ApplicationContext ac = new ClassPathXmlApplicationContext("beans-ioc-xml.xml");
        //2.得到业务层对象
         as = ac.getBean("accountService",IAccountService.class);
        /* ApplicationContext ac = new AnnotationConfigApplicationContext(DemoConfig.class);
         as = ac.getBean("accountService",IAccountService.class);*/
        //3.执行方法
        List<Account> accountList = as.findAllAccount();
        for (Account account:
             accountList) {
            System.out.println(account);
        }
    }

    @Test
    public void testFindOne(){
        //1.获取数据信息
        ApplicationContext ac = new ClassPathXmlApplicationContext("beans-ioc-xml.xml");
        //2.得到业务层对象
        IAccountService as = ac.getBean("accountService",IAccountService.class);
        Account account = as.findAccountById(2);
        System.out.println(account);
    }

    @Test
    public void testAdd(){
        Account account = new Account();
        account.setAname("ddd");
        account.setMoney(1234f);
        //1.获取数据信息
        ApplicationContext ac = new ClassPathXmlApplicationContext("beans-ioc-xml.xml");
        //2.得到业务层对象
        IAccountService as = ac.getBean("accountService",IAccountService.class);

        as.insertAccount(account);
    }

    @Test
    public void testUpdate(){

        Account account = new Account();
        account.setId(4);
        account.setAname("dddupdate");
        account.setMoney(4434f);
        //1.获取数据信息
        ApplicationContext ac = new ClassPathXmlApplicationContext("beans-ioc-xml.xml");
        //2.得到业务层对象
        IAccountService as = ac.getBean("accountService",IAccountService.class);
        //3.执行方法
        as.updateAccount(account);
    }

    @Test
    public void testDelete(){
        //1.获取数据信息
        ApplicationContext ac = new ClassPathXmlApplicationContext("beans-ioc-xml.xml");
        //2.得到业务层对象
        IAccountService as = ac.getBean("accountService",IAccountService.class);
        //3.执行方法
        as.deleteAccount(4);
    }

}
