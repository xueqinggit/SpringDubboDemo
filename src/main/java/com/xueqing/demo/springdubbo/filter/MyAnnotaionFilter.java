package com.xueqing.demo.springdubbo.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * 使用注解自定义Filter  需要在启动类上加入@ServletComponentScan注解
 * XueQing Wang
 */
@Order(2)
@WebFilter(filterName = "",urlPatterns = "/*")
public class MyAnnotaionFilter implements Filter {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        String requestURI = request.getRequestURI();
        logger.info("MyAnnotaionFilter"+requestURI);
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {

    }
}
