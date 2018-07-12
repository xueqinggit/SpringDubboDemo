package com.xueqing.demo.springdubbo.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
/**
 * 自定义Listener
 * XueQing Wang
 */
public class MyListener implements ServletContextListener {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        logger.info("--------------------ServletContext创建了-------------------");
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        logger.info("--------------------ServletContext销毁了-------------------");
    }
}
