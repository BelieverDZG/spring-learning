package org.dzg.iocannotation.service.impl;

import org.dzg.iocannotation.dao.IAccountDao;
import org.dzg.iocannotation.service.IAccountService;
import org.dzg.iocxml.domain.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 账户的业务层实现类
 *
 * 事物控制，应该都在业务层
 */

@Service("accountService")
public class AccountServiceImpl implements IAccountService {


    @Autowired
    private IAccountDao accountDao;

    @Override
    public List<Account> findAllAccount() {
        return accountDao.findAllAccount();
    }

    @Override
    public Account findAccountById(Integer accountId) {
        return accountDao.findAccountById(accountId);
    }

    @Override
    public void insertAccount(Account account) {
        accountDao.insertAccount(account);
    }

    @Override
    public void updateAccount(Account account) {
        accountDao.updateAccount(account);
    }

    @Override
    public void deleteAccount(Integer accountId) {
        accountDao.deleteAccount(accountId);
    }

    @Override
    public void transaction(String sourceAccount, String targetAccount, float money) {

    }
}

