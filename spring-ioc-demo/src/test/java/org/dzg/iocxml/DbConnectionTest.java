package org.dzg.iocxml;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.apache.commons.dbutils.QueryRunner;
import org.dzg.iocxml.utils.ConnectionUtils;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.sql.Connection;

public class DbConnectionTest {

    private QueryRunner qr;
    private ConnectionUtils connectionUtils;

    public void setQr(QueryRunner qr) {
        this.qr = qr;
    }

    public void setConnectionUtils(ConnectionUtils connectionUtils) {
        this.connectionUtils = connectionUtils;
    }
    @Test
    public void testConnection(){
        /*ApplicationContext ac = new ClassPathXmlApplicationContext("beans-ioc-xml.xml");
        Object dataSource = ac.getBean("dataSource", ComboPooledDataSource.class);
        System.out.println(dataSource);*/
        Connection threadConnection = connectionUtils.getThreadConnection();
        System.out.println(threadConnection);
    }
}
