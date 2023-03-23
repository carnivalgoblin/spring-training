package org.rcprdn.springtraining.aspect;

import lombok.extern.java.Log;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.logging.Level;

@Aspect
@Log
@Component
public class TimeLogger {

  private static final Logger LOG = LoggerFactory.getLogger(TimeLogger.class);
  private static final String LOGGING_MESSAGE = "method {} in class {} took {} milliseconds to execute";

  @Around("bean(toDoController)")
  public Object logControllerExecutiontime(ProceedingJoinPoint joinPoint) throws Throwable {
    Long start = System.currentTimeMillis();
    Object proceed = joinPoint.proceed();
    Long end = System.currentTimeMillis();

    Long difference = end -start;

    String methodName = joinPoint.getSignature().getName();
    String className = joinPoint.getTarget().getClass().getName();

    LOG.info(LOGGING_MESSAGE, methodName, className, difference);
    return proceed;
  }
}
