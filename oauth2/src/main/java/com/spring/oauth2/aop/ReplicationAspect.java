package com.spring.oauth2.aop;

import com.spring.oauth2.config.datasource.ReplicationDataBaseContextHolder;
import com.spring.oauth2.config.datasource.ReplicationDataSourceRouter;
import com.spring.oauth2.config.datasource.ReplicationType;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Aspect
@Component
@Order(0)
public class ReplicationAspect {

//    @Around("@annotation(transactional)")
//    public Object proceed(ProceedingJoinPoint proceedingJoinPoint, Transactional transactional) throws Throwable {
//        System.out.println("Aspect executed");
//        try {
//            if (transactional.readOnly()) {
//                ReplicationDataBaseContextHolder.set(ReplicationType.READ);
//            }
//            return proceedingJoinPoint.proceed();
//        } finally {
//            ReplicationDataBaseContextHolder.reset();
//        }
//    }
}
