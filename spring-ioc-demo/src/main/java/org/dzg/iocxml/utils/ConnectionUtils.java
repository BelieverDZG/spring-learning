package org.dzg.iocxml.utils;


import javax.sql.DataSource;
import java.sql.Connection;

/**
 * 连接的工具类，用于从数据源中获取一个连接
 * 并且实现和线程的绑定
 */
public class ConnectionUtils {

     ThreadLocal<Connection> threadConn = new ThreadLocal<>();
     DataSource dataSource =null;

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    /**
     * 获取当前线程上的连接
     * @return
     */
    public  Connection getThreadConnection(){
        /**
         * 1.先从ThreadLocal上获取
         * 2.判断当前线程是否有链接
         * 3.从数据源获取连接，并且存入ThreadLocal
         * 4返回当前的线程连接
         */
        try {
            Connection connection = threadConn.get();
            if (connection == null){
                connection = dataSource.getConnection();
                threadConn.set(connection);
            }
            return connection;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 把连接和线程解绑
     */
    public void removeConnection(){
        threadConn.remove();
    }
}
