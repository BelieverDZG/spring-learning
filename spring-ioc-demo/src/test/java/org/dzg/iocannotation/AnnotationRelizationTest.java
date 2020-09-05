package org.dzg.iocannotation;

import org.dzg.iocannotation.service.IAccountService;
import org.dzg.iocxml.domain.Account;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

public class AnnotationRelizationTest {

    @Test
    public void testFindAll(){
        ApplicationContext ac = new ClassPathXmlApplicationContext("/beans-ioc-annotation.xml");
        IAccountService service =  ac.getBean("accountService",IAccountService.class);
        List<Account> accounts = service.findAllAccount();
        for (Account account:
             accounts) {
            System.out.println(account);
        }
    }

    @Test
    public void testFindOne(){
        //1.获取数据信息
        ApplicationContext ac = new ClassPathXmlApplicationContext("/beans-ioc-annotation.xml");
        //2.得到业务层对象
        IAccountService as = ac.getBean("accountService",IAccountService.class);
        Account account = as.findAccountById(3);
        System.out.println(account);
    }


    @Test
    public void testAdd(){
        Account account = new Account();
        account.setAname("ddd");
        account.setMoney(4444f);
        //1.获取数据信息
        ApplicationContext ac = new ClassPathXmlApplicationContext("/beans-ioc-annotation.xml");
        //2.得到业务层对象
        IAccountService as = ac.getBean("accountService",IAccountService.class);

        as.insertAccount(account);
    }

    @Test
    public void testUpdate(){

        Account account = new Account();
        account.setId(6);
        account.setAname("fffupdate");
        account.setMoney(4434f);
        //1.获取数据信息
        ApplicationContext ac = new ClassPathXmlApplicationContext("/beans-ioc-annotation.xml");
        //2.得到业务层对象
        IAccountService as = ac.getBean("accountService",IAccountService.class);
        //3.执行方法
        as.updateAccount(account);
    }

    @Test
    public void testDelete(){
        //1.获取数据信息
        ApplicationContext ac = new ClassPathXmlApplicationContext("/beans-ioc-annotation.xml");
        //2.得到业务层对象
        IAccountService as = ac.getBean("accountService",IAccountService.class);
        //3.执行方法
        as.deleteAccount(6);
    }
}
