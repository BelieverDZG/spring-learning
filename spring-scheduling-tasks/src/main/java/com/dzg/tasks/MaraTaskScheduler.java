package com.dzg.tasks;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.stereotype.Component;

@Component
public class MaraTaskScheduler {

    @Autowired
    private ThreadPoolTaskScheduler threadPoolTaskScheduler;

    @Bean
    public ThreadPoolTaskScheduler threadPoolTaskScheduler(){
        return new ThreadPoolTaskScheduler();
    }

    /**
     * 设置一个具体时间的任务调度构造方法
     * cron举例：
     <li>"0 0 * * * *" = the top of every hour of every day.</li>
     <li>"0 0 8-10 * * *" = 8, 9 and 10 o'clock of every day.</li>
     <li>"0 0/30 8-10 * * *" = 8:00, 8:30, 9:00, 9:30 and 10 o'clock every day.</li>
     <li>"0 0 9-17 * * MON-FRI" = on the hour nine-to-five weekdays</li>
     <li>"0 0 0 25 12 ?" = every Christmas Day at midnight</li>
     * @param task
     * @param cron
     */
    public void schedule(Runnable task,String cron){

        if (cron == null || "".equals(cron)){
            cron = "0 * * * * *";
        }
        threadPoolTaskScheduler.schedule(task,new CronTrigger(cron));
    }

    /**
     * 重设：先关闭，再初始化
     */
    public void reset(){
        threadPoolTaskScheduler.shutdown();
        threadPoolTaskScheduler.initialize();
    }

    /**
     * 重新设置调度
     * @param task
     * @param cron
     */
    public void resetSchedule(Runnable task,String cron){
        shutdown();
        threadPoolTaskScheduler.initialize();
        schedule(task,cron);
    }

    public void shutdown(){
        threadPoolTaskScheduler.shutdown();
    }
}
