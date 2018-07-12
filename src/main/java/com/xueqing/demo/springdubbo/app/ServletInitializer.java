package com.xueqing.demo.springdubbo.app;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;

/**
 * 若要部署到外部servlet容器,需要继承SpringBootServletInitializer并重写configure()
 */
public class ServletInitializer extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(SpringdubboApplication.class);
    }

}
