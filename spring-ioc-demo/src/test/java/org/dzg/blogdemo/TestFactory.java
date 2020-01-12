package org.dzg.blogdemo;

import org.dzg.pojo.Customer;

public class TestFactory {

    public Object createMethod(){
        return new Customer();
    }

    public static Object staticCreateMethod(){
        return new Customer();
    }

}
