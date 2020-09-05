package com.dzg;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @SpringBootConfiguration 加载配置文件
 * @EnableAutoConfiguration
 * @ComponentScan 组件扫描和自动装配
 */
@SpringBootApplication()
public class SpringAuthenticatingUserLdapApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringAuthenticatingUserLdapApplication.class, args);
	}

}
