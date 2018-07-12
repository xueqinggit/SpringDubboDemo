package com.xueqing.demo.springdubbo.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 * 使用注解自定义Listener
 * XueQing Wang
 */
@WebListener
public class MyAnnotaionListener implements ServletContextListener {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        logger.info("--------------------MyAnnotaionListener ServletContext创建了-------------------");
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        logger.info("--------------------MyAnnotaionListener ServletContext销毁了-------------------");
    }
}
