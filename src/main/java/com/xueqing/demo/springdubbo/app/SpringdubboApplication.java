package com.xueqing.demo.springdubbo.app;

import com.xueqing.demo.springdubbo.utils.SpringUtil;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication
@ComponentScan("com.xueqing.demo.springdubbo")  //开启扫描注解
@EnableScheduling  //任务调度
@ServletComponentScan("com.xueqing.demo.springdubbo") //自定义filter等
@MapperScan("com.xueqing.demo.springdubbo.dao") //mybatis需要指定到dao文件夹
@EnableAspectJAutoProxy  //开启aop注解
public class SpringdubboApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(SpringdubboApplication.class, args);
        SpringUtil.setApplicationContext(context);
    }
}
