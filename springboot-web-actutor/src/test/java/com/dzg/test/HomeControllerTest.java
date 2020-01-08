package com.dzg.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.BDDAssertions.then;

/*
    Spring Boot Actuator includes a number of additional features to help you
    monitor and manage your application when it’s pushed to production.
    You can choose to manage and monitor your application using HTTP or JMX endpoints.
    Auditing, health and metrics gathering can be automatically
    applied to your application.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource(properties = {"management.port=0"})
public class HomeControllerTest {

    @LocalServerPort
    private int port;

    @Value("${local.management.port}")
    private int mgt;

    @Autowired
    private TestRestTemplate testRestTemplate;

    /**
     * 向控制器发送请求时，应该返回200
     */
    @Test
    public void testOne(){

        List<String> lists = new ArrayList<String>();
        @SuppressWarnings("rawtypes")
        ResponseEntity<Map> entity = this.testRestTemplate.getForEntity(
                "http://localhost:"+this.port+"/hello",Map.class
        );
        int res = entity.getStatusCode().compareTo(HttpStatus.OK);
        then(res);
        System.out.println("执行结果："+res);
    }

    /**
     * 向管理终端发送请求，应该返回200
     */
    @Test
    public void testTwo(){

        @SuppressWarnings("rawtypes")
        ResponseEntity<Map> entity = this.testRestTemplate.getForEntity(
                "http://localhost:"+this.mgt+"/actuator/info",Map.class
        );
        int res = entity.getStatusCode().compareTo(HttpStatus.OK);
        then(res);
        System.out.println("执行结果："+res);
    }

}
