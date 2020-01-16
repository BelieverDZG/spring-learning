package org.dzg.beancreate.dao;

import org.dzg.beancreate.pojo.Customer;

import java.util.List;

public interface ICustomerDao {

    List<Customer> findAll();
}
