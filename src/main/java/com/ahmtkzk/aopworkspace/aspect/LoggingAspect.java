package com.ahmtkzk.aopworkspace.aspect;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {

    private final Logger LOGGER = LogManager.getLogger(LoggingAspect.class);

    @AfterReturning(pointcut = "@annotation(com.ahmtkzk.aopworkspace.aspect.annotation.MyLogger)", returning = "result")
    public void endpointLogSuccess(ResponseEntity<?> result) {
        LOGGER.info("The method worked successfully and was completed. Status Code: {}", result.getStatusCode());
    }

}
