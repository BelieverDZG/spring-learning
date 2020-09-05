package com.dzg.order;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients//激活feign
@EnableCircuitBreaker //激活hystrix
//激活hystrix的web监控台
@EnableHystrixDashboard
public class OrderServiceFeignApplication {

    public static void main(String[] args) {
        SpringApplication.run(OrderServiceFeignApplication.class, args);
    }

    /**
     * 使用spring提供的RestTemplate发送http请求到商品服务
     * 1.创建RestTemplate对象交给容器管理
     * 2.在使用的时候,调用其方法完成操作 (getXX,postxxx)
     * ** @LoadBalanced : 是ribbon提供的负载均衡的注解
     */

   /* @LoadBalanced //ribbon负载均衡
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }*/
}
