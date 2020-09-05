package org.dzg.iocannotation.service;

import org.dzg.iocxml.domain.Account;

import java.util.List;

/**
 * 账户的业务接口层
 */
public interface IAccountService {

    /**
     * 查询所有账户信息
     * @return
     */
    List<Account> findAllAccount();

    /**
     * 根据id查询一个账户
     * @param accountId
     * @return
     */
    Account findAccountById(Integer accountId);

    /**
     * 增加账户信息
     * @param account
     */
    void insertAccount(Account account);

    /**
     * 修改账户信息
     * @param account
     */
    void updateAccount(Account account);

    /**
     * 根据id删除账户信息
     * @param accountId
     */
    void deleteAccount(Integer accountId);

    /**
     * 账户之间进行转账操作
     * @param sourceAccount
     * @param targetAccount
     * @param money
     */
    void transaction(String sourceAccount, String targetAccount, float money);
}
