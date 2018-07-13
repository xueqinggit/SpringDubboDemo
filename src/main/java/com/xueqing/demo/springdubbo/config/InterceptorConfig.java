package com.xueqing.demo.springdubbo.config;

import com.xueqing.demo.springdubbo.intercept.MyIntercept;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class InterceptorConfig extends WebMvcConfigurerAdapter {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        super.addInterceptors(registry);
        registry.addInterceptor(new MyIntercept()).addPathPatterns("/*");
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        super.addResourceHandlers(registry);
        registry.addResourceHandler("/images").addResourceLocations("/static/images");
        registry.addResourceHandler("/js").addResourceLocations("/static/js");
        registry.addResourceHandler("/css").addResourceLocations("/static/css");
    }
}
