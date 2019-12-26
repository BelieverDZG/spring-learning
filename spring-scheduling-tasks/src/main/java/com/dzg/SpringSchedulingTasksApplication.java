package com.dzg;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling //创建一个任务执行器，进行任务调度
public class SpringSchedulingTasksApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringSchedulingTasksApplication.class, args);
    }

}
