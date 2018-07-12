package com.xueqing.demo.springdubbo.scheduler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;
@Component
public class MyScheduler {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Scheduled(cron = "0/3 * * * * ? ")
    public void cronTask(){
        logger.info("cronTask毫秒值:"+new SimpleDateFormat("HH:mm:ss").format(new Date()));
    }

    @Scheduled(fixedRate = 5000)
    public void fixedRateTask(){
        logger.info("fixedRateTask毫秒值:"+new SimpleDateFormat("HH:mm:ss").format(new Date()));
    }

    @Scheduled(fixedDelay = 5000)
    public void fixedDelayTask(){
        logger.info("fixedDelayTask毫秒值:"+new SimpleDateFormat("HH:mm:ss").format(new Date()));
    }
}
