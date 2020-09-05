package com.dzg.order.feign.impl;

import com.dzg.order.entity.Product;
import com.dzg.order.feign.ProductFeignClient;
import org.springframework.stereotype.Component;

/**
 * 实现自定义的ProductFeignClient接口
 * 在接口实现类中编写熔断降级的方法
 */
@Component
public class ProductFeignClientCallBack implements ProductFeignClient {
    @Override
    public Product findById(Long id) {
        Product product = new Product();
        product.setProductName("在feign中使用hystrix熔断降级的方式");
        return product;
    }
}
