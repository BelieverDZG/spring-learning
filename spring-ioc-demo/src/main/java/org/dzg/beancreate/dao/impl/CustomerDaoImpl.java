package org.dzg.beancreate.dao.impl;

import org.dzg.beancreate.dao.ICustomerDao;
import org.dzg.beancreate.pojo.Customer;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository("customerDao")
public class CustomerDaoImpl implements ICustomerDao {


    /**
     * 模拟数据库的查询操作，返回所有客户信息
     * @return
     */
    @Override
    public List<Customer> findAll() {

        List<Customer> customers = new ArrayList<Customer>();
        for (int i = 0; i < 7; i++) {

            Customer customer = new Customer();
            customer.setCid(i);
            customer.setCname("name"+i);
            customer.setSex("sex"+i);
            customers.add(customer);
        }
        return customers;
    }

}
