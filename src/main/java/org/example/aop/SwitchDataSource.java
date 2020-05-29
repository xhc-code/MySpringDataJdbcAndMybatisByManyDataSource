package org.example.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.example.annon.TargetDataSource;
import org.example.utils.ContextSwitchDataSourceUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * 解决从Service接口进行调用方法进行切入，Service之间的方法调用不会使用AOP进行切入
 */
@Component
@Aspect
@Order(Ordered.LOWEST_PRECEDENCE-1)
public class SwitchDataSource {

    private Logger logger = LoggerFactory.getLogger(SwitchDataSource.class);

    @Pointcut("execution(* org.example.service.*Service.*(..)) && @annotation(org.example.annon.TargetDataSource)")
    private void dataSourcePointcut(){}

    @Before("dataSourcePointcut()")
    public void switchData(JoinPoint joinPoint){
        ContextSwitchDataSourceUtils.DataSourceEnum currentLocalDataSourceKey = ContextSwitchDataSourceUtils.getCurrentLocalDataSourceKey();
        Signature signature = joinPoint.getSignature();
        MethodSignature methodSignature = (MethodSignature) signature;
        Method method = methodSignature.getMethod();
        TargetDataSource annotation = method.getAnnotation(TargetDataSource.class);
        if(annotation != null){
            //判断是否需要改变
            if(currentLocalDataSourceKey != annotation.value()){
                ContextSwitchDataSourceUtils.DataSourceEnum value = annotation.value();
                logger.debug("设置数据源上下文:" + ContextSwitchDataSourceUtils.getCurrentLocalDataSourceKey() + "=====>" + value);
                ContextSwitchDataSourceUtils.setDataSource(value);
            }
        }else{
            logger.debug("恢复默认数据源上下文");
            ContextSwitchDataSourceUtils.restoreDefault();
        }
    }

    @After("dataSourcePointcut()")
    public void restoreDefault(){
        ContextSwitchDataSourceUtils.DataSourceEnum dataSourceKeys = ContextSwitchDataSourceUtils.DataSourceEnum.DEFAULT;
        logger.debug("恢复数据源上下文:" + ContextSwitchDataSourceUtils.getCurrentLocalDataSourceKey()+ "=====>" + dataSourceKeys);
        ContextSwitchDataSourceUtils.restoreDefault();
    }

}
