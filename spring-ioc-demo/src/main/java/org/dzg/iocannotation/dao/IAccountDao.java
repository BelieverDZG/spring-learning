package org.dzg.iocannotation.dao;

import org.dzg.iocxml.domain.Account;

import java.util.List;

/**
 * 账户的持久层接口
 */
public interface IAccountDao {

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
     * 根据id修改账户信息
     * @param account
     */
    void updateAccount(Account account);

    /**
     * 根据id删除账户信息
     * @param accountId
     */
    void deleteAccount(Integer accountId);
}