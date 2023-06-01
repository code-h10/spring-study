package com.spring.aspect.config.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
public class LoggingAspect {

    @Pointcut("@annotation(org.springframework.web.bind.annotation.GetMapping)")
    public void getMappingMethods() {}

    @Pointcut("@annotation(org.springframework.web.bind.annotation.PostMapping)")
    public void postMappingMethods() {}

    @Pointcut("@annotation(org.springframework.web.bind.annotation.PutMapping)")
    public void putMappingMethods() {}


    @AfterReturning("getMappingMethods() || postMappingMethods() || putMappingMethods()")
    public void afterMappingMethods() {
        log.info("AfterReturning...");
    }

    @AfterThrowing(pointcut = "getMappingMethods() || postMappingMethods() || putMappingMethods()", throwing = "ex")
    public void afterThrowingMappingMethods(Exception ex) {
        log.info("AfterThrowing...");
    }


}
