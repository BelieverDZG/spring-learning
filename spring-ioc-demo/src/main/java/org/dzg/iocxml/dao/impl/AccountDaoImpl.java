package org.dzg.iocxml.dao.impl;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.dzg.iocxml.dao.IAccountDao;
import org.dzg.iocxml.domain.Account;
import org.dzg.iocxml.utils.ConnectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.SQLException;
import java.util.List;
/**
 * 账户持久层实现类
 */

public class AccountDaoImpl implements IAccountDao {


    private QueryRunner qr;

    private ConnectionUtils connectionUtils;

    public void setQr(QueryRunner qr) {
        this.qr = qr;
    }

    public void setConnectionUtils(ConnectionUtils connectionUtils) {
        this.connectionUtils = connectionUtils;
    }

    public List<Account> findAllAccount() {
        try {
            return qr.query(connectionUtils.getThreadConnection(),"select * from t_account",new BeanListHandler<>(Account.class));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public Account findAccountById(Integer accountId) {
        try {
            return qr.query(connectionUtils.getThreadConnection(),"select * from t_account where id=?",new BeanHandler<Account>(Account.class),accountId);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void insertAccount(Account account) {

        try {
            qr.update(connectionUtils.getThreadConnection(),"insert into t_account(aname,money) values(?,?)",account.getAname(),account.getMoney());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public void updateAccount(Account account) {
        try {
            qr.update(connectionUtils.getThreadConnection(),"update t_account set aname = ?,money=? where id = ?",account.getAname(),account.getMoney(),account.getId());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public void deleteAccount(Integer accountId) {
        try {
            qr.update(connectionUtils.getThreadConnection(),"delete from t_account where id = ?",accountId);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Account findByAccountName(String accountName) {
        try {
            List<Account> accounts = qr.query(connectionUtils.getThreadConnection(),"select * from t_account where aname = ?",new BeanListHandler<Account>(Account.class),accountName);
            if(accounts.size() == 0 ){
                return null;
            }
            return  accounts.get(0);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


}
