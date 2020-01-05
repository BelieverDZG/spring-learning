package com.dzg;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication //该注释已经将 @EnableWebMvc添加到程序类中
//@EnableWebMvc
public class SpringValidatingFormApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringValidatingFormApplication.class, args);
    }

}
