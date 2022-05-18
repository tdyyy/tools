package com.example.demo.Aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class FeignAdvice {
    private static final Logger logger = LoggerFactory.getLogger(FeignAdvice.class);

    @Around("execution(* com.example.demo.services.StanderInvoke.*(..))")
    public Object exceptionAdvice(ProceedingJoinPoint point){
        try {
            System.out.println("xxxxxxxxxxxx");
            Object proceed = point.proceed();
            return proceed;
        } catch (Throwable throwable) {
//            throwable.printStackTrace();
            logger.error("feign 调用异常，e",throwable);
        }
        return "";
    }

}
