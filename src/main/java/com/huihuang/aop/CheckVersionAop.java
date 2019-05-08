package com.huihuang.aop;

import com.huihuang.annotation.CheckVersion;
import com.huihuang.checkhandler.CheckVersionHandler;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.Map;

@Aspect
@Component
public class CheckVersionAop {

    private static final String STATUS = "STATUS";
    private static final String RESULT = "RESULT";
    /**
     * 环绕方法 直接扫描注解
     * @param proceedingJoinPoint
     * @return
     * @throws Throwable
     */
    @Around("@annotation(com.huihuang.annotation.CheckVersion)")
    public Object currentLimiting(ProceedingJoinPoint proceedingJoinPoint) throws Throwable{
        MethodSignature methodSignature = (MethodSignature) proceedingJoinPoint.getSignature();
        Method method = methodSignature.getMethod();
        Object[] param = proceedingJoinPoint.getArgs();
        CheckVersion checkVersion = method.getAnnotation(CheckVersion.class);
        String version = checkVersion.version();
        Class<?> clazz = checkVersion.clazz();
        CheckVersionHandler handler = (CheckVersionHandler) clazz.newInstance();
        Map<String, Object> reslutMap = handler.chack(param, version);
        if ((boolean) reslutMap.get(STATUS)){
            return reslutMap.get(RESULT);
        }
        return proceedingJoinPoint.proceed();
    }
}
