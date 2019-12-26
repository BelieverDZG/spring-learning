package com.dzg.controller;

import com.dzg.tasks.MaraTaskScheduler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.Date;

//使用rest风格的接口，对任务进行调度
@RestController
public class TaskScheduleController {

    @Autowired
    MaraTaskScheduler maraTaskScheduler;

    @RequestMapping("/schedule")
    public String schedule(@RequestParam String cron){
        if(cron == null){
            cron = "0/5 * * * * *";
        }
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                String time = new SimpleDateFormat("yy-MM-DD HH:mm:ss ").format(new Date());
                System.out.println("test success at :"+time);
            }
        };
        maraTaskScheduler.schedule(runnable,cron);
        return "test schedule tasks interface";
    }
}
