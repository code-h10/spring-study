package com.spring.aspect.config.aop;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

@Aspect
@Component
@Slf4j
@RequiredArgsConstructor
public class LoggingAspect {

    private final RequestMappingHandlerMapping handlerMapping;

    @Pointcut("@annotation(org.springframework.web.bind.annotation.GetMapping)")
    public void getMappingMethods() {}

    @Pointcut("@annotation(org.springframework.web.bind.annotation.PostMapping)")
    public void postMappingMethods() {}

    @Pointcut("@annotation(org.springframework.web.bind.annotation.PutMapping)")
    public void putMappingMethods() {}


    @AfterReturning("getMappingMethods() || postMappingMethods() || putMappingMethods()")
    public void afterMappingMethods(ProceedingJoinPoint joinPoint) {
        log.info("AfterReturning...");
    }

    @AfterThrowing(pointcut = "getMappingMethods() || postMappingMethods() || putMappingMethods()", throwing = "ex")
    public void afterThrowingMappingMethods(ProceedingJoinPoint joinPoint, Exception ex) {
        log.info("AfterThrowing...");
    }


}
