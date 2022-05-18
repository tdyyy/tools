package com.example.demo.Aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
public class HelloAspect {
    @Around("execution(* com.example.demo.services.BaseService.innerHello(..))")
    public Object helloMappingAspect(ProceedingJoinPoint point) throws Throwable {
        Object[] args = point.getArgs();
        log.info("--------------");
        if("aspect".equals(args[0])){
            return "NO!!!";
        }else {
            return point.proceed();
        }
    }

    @Around("execution(* com.example.demo.services.BaseService.outerHello(..))")
    public Object helloMappingAspectx(ProceedingJoinPoint point) throws Throwable {
        Object[] args = point.getArgs();
        log.info("--------------");
        if("aspect".equals(args[0])){
            return "NO!!!";
        }else {
            return point.proceed();
        }
    }
}
