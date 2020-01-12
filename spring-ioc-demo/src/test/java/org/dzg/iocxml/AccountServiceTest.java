package org.dzg.iocxml;

import org.dzg.iocxml.service.IAccountService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
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
 *    整合后，对于测试人员来说不需要关注需要创建哪些对象或者获取哪些资源，只需要关注方法功能本身
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "/beans.xml")
public class AccountServiceTest {

    @Autowired
    @Qualifier("proxyAccountService")
    IAccountService accountService = null;

    @Test
    public void transactionTest(){
        String sourceAccount = "aaa";
        String targetAccount = "bbb";
        float money = 100;
        accountService.transaction(sourceAccount,targetAccount,money);
    }


}
