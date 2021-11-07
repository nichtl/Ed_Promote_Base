package com.nicht.spring_base.Aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;

/**
 * @Author Nicht
 * @Description
 * @Time 2021/10/11
 * @Link
 */
@Aspect
public class LogAspects {
    public LogAspects() {
        System.out.println("======LogAspects========");
    }

    @Pointcut("execution(* com.nicht.spring_base.Aop.Service.*(..))")
    public void pointCut() {
    }

    /**
     * 在目标方法执行前
     */
    @Before("pointCut()")
    public void logStart(JoinPoint joinPoint) {
        System.out.println("logStart  开始运行:" + joinPoint.getSignature().getName());
    }

    /**
     * 在目标方法执行后
     */
    @After("pointCut()")
    public void logAfter() {
        System.out.println("logAfter  结束运行");
    }

    /**
     * 方法返回后
     */
    @AfterReturning(value = "pointCut()", returning = "result")
    public void logReturning(Object result) {
        System.out.println("logReturning  正常返回，结果:" + result);
    }

    /**
     * 出现异常后
     */
    @AfterThrowing(value = "pointCut()", throwing = "exception")
    public void logException(Exception exception) {
        System.out.println("logStarException  运行异常:" + exception);
    }
}
