package com.xueqing.demo.springdubbo.config;

import com.xueqing.demo.springdubbo.filter.MyFilter;
import com.xueqing.demo.springdubbo.listener.MyListener;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.EventListener;
import java.util.List;

/**
 *
 * @ClassName: RedisConfig
 * @Description: redis配置
 * @author XueQing Wang
 * @date 2018年7月8日 上午09:15:19
 */
@Configuration
public class WebConfig {

    @Bean
    public FilterRegistrationBean getDemoFilter() {
        FilterRegistrationBean registrationBean = new FilterRegistrationBean();
        registrationBean.setFilter(new MyFilter());
        List<String> urlPatterns = new ArrayList<String>();
        urlPatterns.add("/*");                            //拦截路径，可以添加多个
        registrationBean.setUrlPatterns(urlPatterns);
        registrationBean.setOrder(1);
        return registrationBean;
    }


    @Bean
    public ServletListenerRegistrationBean<EventListener> getDemoListener() {
        ServletListenerRegistrationBean<EventListener> registrationBean = new ServletListenerRegistrationBean<>();
        registrationBean.setListener(new MyListener());
        registrationBean.setOrder(1);
        return registrationBean;

    }

}
