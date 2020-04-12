package com.dzg.order.controller;

import com.dzg.order.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;


@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private RestTemplate restTemplate;


    /**
     * 基于ribbon形式调用微服务
     *  1、使用LoadBalance声明restTempLate
     *  2、使用微服务名称替换IP地址
     * @param id
     * @return
     */
    @GetMapping("/buy/{id}")
    public Product buyById(@PathVariable Long id) {
        return restTemplate.getForObject("http://service-product/product/1", Product.class);
    }

    //使用Ribbon之前的服务调用
    //服务发现
   /* @Autowired
    private DiscoveryClient discoveryClient;

    @RequestMapping(value = "/buy/{id}", method = RequestMethod.GET)
    public Product findById(@PathVariable Long id) {
        //调用discoverClient方法
        //已调用服务名称获取所有的元数据
        List<ServiceInstance> instances = discoveryClient.getInstances("service-product");
        //获取唯一的元数据
        ServiceInstance serviceInstance = instances.get(0);
        //根据元数据的主机地址和端口号拼接请求微服务的URL
        Product product = null;
        //如何调用商品服务？
        product = restTemplate.getForObject("http://" + serviceInstance.getHost() + ":" + serviceInstance.getPort() + "/product/1", Product.class);

        return product;
    }*/
}
