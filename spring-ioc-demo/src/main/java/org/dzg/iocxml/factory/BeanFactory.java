package org.dzg.iocxml.factory;

import org.dzg.iocxml.service.IAccountService;
import org.dzg.iocxml.utils.TransactionManagerUtil;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * 用于创建service的代理对象
 */
public class BeanFactory {

    private IAccountService accountService;
    private TransactionManagerUtil transactionManagerUtil;

    public void setAccountService(IAccountService accountService) {
        this.accountService = accountService;
    }

    public void setTransactionManagerUtil(TransactionManagerUtil transactionManagerUtil) {
        this.transactionManagerUtil = transactionManagerUtil;
    }

    public IAccountService getAccountService() {
        return (IAccountService) Proxy.newProxyInstance(accountService.getClass().getClassLoader(),
                accountService.getClass().getInterfaces(),
                new InvocationHandler() {
                    /**
                     * 添加事务支持
                     * @param proxy
                     * @param method
                     * @param args
                     * @return
                     * @throws Throwable
                     */
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        Object rtValue = null;
                        try {
                            //开启事务
                            transactionManagerUtil.beginTransaction();
                            //执行操作
                            rtValue = method.invoke(accountService,args);
                            //提交事务
                            transactionManagerUtil.commit();
                            //返回结果
                            return rtValue;
                        } catch (Exception e) {
                            //出现异常，进行回滚操作
                            transactionManagerUtil.rollback();
                            throw new RuntimeException(e);
                        } finally {
                            //释放资源
                            transactionManagerUtil.relase();
                        }

                    }
                });
    }
}
