package org.dzg.beancreate.controller;

import org.dzg.beancreate.pojo.Customer;
import org.dzg.beancreate.service.ICustomerService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class MyController {

    @Autowired(required = false)
    ICustomerService customerService;

    public List<Customer> test(){
        if (customerService != null){
            return customerService.findAll();
        }
        return null;
    }
}
