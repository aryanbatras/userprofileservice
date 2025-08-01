package com.sashel.user_profile_service.utility.logger;

import org.slf4j.*;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.ProceedingJoinPoint;
import org.springframework.stereotype.Component;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

@Aspect
@Component
public class LoggerAspect {
    private static final Logger log = LoggerFactory.getLogger(LoggerAspect.class);
    private static final ObjectMapper objectMapper = new ObjectMapper();

    @Pointcut(
            "execution(public * com.sashel.user_profile_service.user.controller..*(..)) || " +
                    "execution(public * com.sashel.user_profile_service.seller.controller..*(..)) || " +
                    "execution(public * com.sashel.user_profile_service.buyer.controller..*(..)) || " +
                    "execution(public * com.sashel.user_profile_service.admin.controller..*(..))"
    )
    public void loggableEndpoints() {}

    @Around("loggableEndpoints()")
    public Object logAround(ProceedingJoinPoint joinPoint) throws Throwable {
        var className = joinPoint.getSignature().getDeclaringType().getSimpleName();
        var method = joinPoint.getSignature().getName();
        var args = joinPoint.getArgs();
        objectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
        log.info("\uD83D\uDD39 Entering {}.{} with args {}", className, method, objectMapper.writeValueAsString(args));
        var result = joinPoint.proceed();
        return logExecution(result, className, method);
    }

    public Object logExecution(Object result, String className, String method) throws Throwable {
        try {
            log.info(" \uD83D\uDC9B Exiting {}.{} with result {}", className, method, objectMapper.writeValueAsString(result));
            return result;
        } catch (Throwable ex) {
            log.error(" ❌ Exception in {}.{}: {}", className, method, ex.getMessage(), ex);
            throw ex;
        }
    }
}
