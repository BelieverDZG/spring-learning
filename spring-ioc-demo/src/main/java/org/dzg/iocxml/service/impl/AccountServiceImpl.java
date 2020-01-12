package org.dzg.iocxml.service.impl;

import org.dzg.iocxml.dao.IAccountDao;
import org.dzg.iocxml.domain.Account;
import org.dzg.iocxml.service.IAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 账户的业务层实现类
 *
 * 事物控制，应该都在业务层
 */

public class AccountServiceImpl implements IAccountService {

    private IAccountDao accountDao;
    /*
     不在需要该变量，因为已经实现由代理对象来控制
    private TransactionManager transactionManager;

    public void setTransactionManager(TransactionManager transactionManager) {
        this.transactionManager = transactionManager;
    }*/

    public void setAccountDao(IAccountDao accountDao) {
        this.accountDao = accountDao;
    }

    public List<Account> findAllAccount() {
        return accountDao.findAllAccount();
    }

    public Account findAccountById(Integer accountId) {
        return accountDao.findAccountById(accountId);
    }

    public void insertAccount(Account account) {
        accountDao.insertAccount(account);
    }

    public void updateAccount(Account account) {
        accountDao.updateAccount(account);
    }

    public void deleteAccount(Integer accountId) {
        accountDao.deleteAccount(accountId);
    }

    public void transaction(String sourceAccount, String targetAccount, float money) {
        /**
         * 1、根据用户名获取转出账户
         * 2、获取转入账户
         * 3、将转出账户的资金减去money
         * 4、将转入账户的资金加上money
         * 5、更新转出账户的资金
         * 6、更新转入账户的资金
         */
        System.out.println("transfer.........");
        Account accountA = accountDao.findByAccountName(sourceAccount);
        Account accountB = accountDao.findByAccountName(targetAccount);
        accountA.setMoney(accountA.getMoney() - money);
        accountB.setMoney(accountB.getMoney() + money);
        //更新
        accountDao.updateAccount(accountA);

        /*
        添加一个异常，致使事物不能够执行完整，使得数据库的数据更新出现异常：
            即转出账户资金减少了，但是转入账户的资金并没有增加
         */
//        int i = 1/0;

        accountDao.updateAccount(accountB);
    }
}

