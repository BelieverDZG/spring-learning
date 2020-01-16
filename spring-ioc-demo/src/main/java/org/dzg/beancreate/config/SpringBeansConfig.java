package org.dzg.beancreate.config;

import org.dzg.beancreate.dao.ICustomerDao;
import org.dzg.beancreate.dao.impl.CustomerDaoImpl;
import org.dzg.beancreate.pojo.Customer;
import org.dzg.beancreate.service.ICustomerService;
import org.dzg.beancreate.service.impl.CustomerServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = "org.dzg.beancreate")
public class SpringBeansConfig {

    @Bean(name = "customer")
    public Customer customer(){
        return new Customer();
    }

    @Bean(name = "customerService")
    public ICustomerService customerService(){
        return new CustomerServiceImpl();
    }

    @Bean(name = "customerDao")
    public ICustomerDao customerDao(){
        return new CustomerDaoImpl();
    }

}
