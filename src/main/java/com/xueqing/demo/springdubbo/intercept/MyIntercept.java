package com.xueqing.demo.springdubbo.intercept;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MyIntercept extends HandlerInterceptorAdapter {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        logger.info("MyIntercept开始拦截");
        return super.preHandle(request, response, handler);
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        super.postHandle(request, response, handler, modelAndView);
        int status = response.getStatus();
        if (status == 404) {
            modelAndView.setViewName("errorpage/404");
        } else if (status == 400) {
            modelAndView.setViewName("errorpage/400");
        }else if (status == 500) {
            modelAndView.setViewName("errorpage/500");
        }
        logger.info("MyIntercept正在拦截");
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        super.afterCompletion(request, response, handler, ex);
        logger.info("MyIntercept拦截完成");
    }
}
