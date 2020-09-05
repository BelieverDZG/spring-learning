package org.dzg.iocannotation.dao.impl;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.dzg.iocannotation.dao.IAccountDao;
import org.dzg.iocxml.domain.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.List;

/**
 * 使用JdbcTemplate来替换QueryRunner进行数据操作
 *
 *  JdbcTemplate主要提供以下五类方法：
 *
 *     execute方法：可以用于执行任何SQL语句，一般用于执行DDL语句；
 *
 *     update方法及batchUpdate方法：update方法用于执行新增、修改、删除等语句；batchUpdate方法用于执行批处理相关语句；
 *
 *     query方法及queryForXXX方法：用于执行查询相关语句；
 *
 *     call方法：用于执行存储过程、函数相关语句。
 */
@Repository("accountDao")
public class AccountDaoImpl implements IAccountDao {

   /* @Autowired
    private QueryRunner qr;*/

    @Autowired
    JdbcTemplate jdbcTemplate;

    RowMapper<Account> rowMapper = new BeanPropertyRowMapper<>(Account.class);

    @Override
    public List<Account> findAllAccount() {

        return jdbcTemplate.query("select * from t_account",rowMapper);
//            return qr.query("select * from t_account",new BeanListHandler<Account>(Account.class));
    }

    @Override
    public Account findAccountById(Integer accountId) {
        return jdbcTemplate.queryForObject("select * from t_account where id=?",rowMapper,accountId);
        /*try {
            return qr.query("select * from t_account where id=?",new BeanHandler<Account>(Account.class),accountId);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }*/
    }

    @Override
    public void insertAccount(Account account) {
        jdbcTemplate.update("insert into t_account(aname,money) values(?,?)",account.getAname(),account.getMoney());
        /*try {
            qr.update("insert into t_account(aname,money) values(?,?)",account.getAname(),account.getMoney());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }*/
    }

    @Override
    public void updateAccount(Account account) {

        jdbcTemplate.update("update t_account set aname = ?,money=? where id = ?",account.getAname(),account.getMoney(),account.getId());
        /*try {
            qr.update("update t_account set aname = ?,money=? where id = ?",account.getAname(),account.getMoney(),account.getId());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }*/
    }

    @Override
    public void deleteAccount(Integer accountId) {
        jdbcTemplate.update("delete from t_account where id = ?",accountId);
        /*try {
            qr.update("delete from t_account where id = ?",accountId);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }*/
    }
}
