package org.dzg.beancreate.service.impl;

import org.dzg.beancreate.dao.ICustomerDao;
import org.dzg.beancreate.pojo.Customer;
import org.dzg.beancreate.service.ICustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service("customerService")
public class CustomerServiceImpl implements ICustomerService {

    @Autowired(required = false)
    ICustomerDao customerDao;

    public List<Customer> findAll() {
        if (customerDao != null){
            return customerDao.findAll();
        }
        return null;
    }

}
