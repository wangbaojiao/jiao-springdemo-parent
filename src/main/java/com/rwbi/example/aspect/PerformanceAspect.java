package com.rwbi.example.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
public class PerformanceAspect {
    @Around("execution(* com.rwbi..controller..*(..))")
    public Object logContollerPerformance(ProceedingJoinPoint pjp) throws Throwable {
        return logPerformance(pjp);
    }

    @Around("execution(* com.rwbi..service..*(..))")
    public Object logServicePerformance(ProceedingJoinPoint pjp) throws Throwable {
        return logPerformance(pjp);
    }

    @Around("execution(* com.rwbi..mapper..*(..))")
    public Object logMapperPerformance(ProceedingJoinPoint pjp) throws Throwable {
        return logPerformance(pjp);
    }

    private Object logPerformance(ProceedingJoinPoint pjp) throws Throwable {
        long startTime = System.currentTimeMillis();
        String name = "-";
        String result = "Y";
        try {
            name = pjp.getSignature().toShortString();
            return pjp.proceed();
        } catch (Throwable t) {
            result = "N";
            throw t;
        } finally {
            long endTime = System.currentTimeMillis();
            log.info("{};{};{}ms", name, result, endTime - startTime);
        }
    }
}
