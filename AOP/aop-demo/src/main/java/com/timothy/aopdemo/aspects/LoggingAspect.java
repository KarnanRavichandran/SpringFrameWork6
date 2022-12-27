package com.timothy.aopdemo.aspects;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;

@Configuration
@Aspect
public class LoggingAspect {
    Logger logger = LoggerFactory.getLogger(getClass());

    @Before("execution(* com.timothy.aopdemo.*.*.*(..))")
    public void beforeMethodCall(JoinPoint joinPoint){
        logger.info("before method is called -{}",joinPoint);
    }
//    @After("execution(* com.timothy.aopdemo.*.*.*(..))")
//    public void afterMethodCall(JoinPoint joinPoint){
//        logger.info("after method is called -{}",joinPoint);
//    }
    @AfterThrowing(
            pointcut = "execution(* com.timothy.aopdemo.*.*.*(..))",
            throwing = "exception"
            )
    public void afterThrowingMethodCall(JoinPoint joinPoint, Exception exception){
        logger.info("afterThrowing method is called - {} has thrown exception - {}",joinPoint,exception);
    }

    @AfterReturning(
            pointcut = "execution(* com.timothy.aopdemo.*.*.*(..))",
            returning = "result")
    public void afterReturningMethodCall(JoinPoint joinPoint, Object result){
        logger.info("afterReturning method is called -{} return the result - {}",joinPoint,result);
    }
}
