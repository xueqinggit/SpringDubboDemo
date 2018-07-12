package com.xueqing.demo.springdubbo.aop;


import com.xueqing.demo.springdubbo.annotation.SysLog;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

@Component
@Aspect
public class MyAspect {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Pointcut("@annotation(com.xueqing.demo.springdubbo.annotation.SysLog)")
    public void myAspect() {
    }

    @Before("myAspect()")
    public void before(JoinPoint joinPoint){
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();

        SysLog sysLog = method.getAnnotation(SysLog.class);

        String value = sysLog.value();

        //请求的方法名
        String className = joinPoint.getTarget().getClass().getName();
        String methodName = signature.getName();

        logger.info("------------------------"+value+"------------------------");
    }
}
