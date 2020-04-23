package com.dzg.order.feign;

import com.dzg.order.entity.Product;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * 1、声明需要调用的微服务名称
 *
 * @FeignClient name：服务提供者的名称
 */

@FeignClient(name="service-product")
public interface ProductFeignClient {

    /**
     * 配置需要调用的微服务接口
     */
    @GetMapping(value = "/product/{id}")
    Product findById(@PathVariable("id") Long id);
}
