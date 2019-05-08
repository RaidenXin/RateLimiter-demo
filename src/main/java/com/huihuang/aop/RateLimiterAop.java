package com.huihuang.aop;

import com.google.common.util.concurrent.RateLimiter;
import com.huihuang.annotation.RateLimiterAnnotation;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

@Aspect
@Component
public class RateLimiterAop {

    //实际项目中可以使用Redis
    private static final Map<String, RateLimiter> RATE_LIMITER_MAP = new ConcurrentHashMap<>();

    /**
     * 环绕方法 直接扫描注解
     * @param proceedingJoinPoint
     * @return
     * @throws Throwable
     */
    @Around("@annotation(com.huihuang.annotation.RateLimiterAnnotation)")
    public Object currentLimiting(ProceedingJoinPoint proceedingJoinPoint) throws Throwable{
        MethodSignature methodSignature = (MethodSignature) proceedingJoinPoint.getSignature();
        Method method = methodSignature.getMethod();
        RateLimiterAnnotation rateLimiterAnnotation = method.getAnnotation(RateLimiterAnnotation.class);
        //获取速率
        double rate = rateLimiterAnnotation.rate();
        //获取超时时间
        long timeout = rateLimiterAnnotation.timeout();
        //获取超时时间规格
        TimeUnit timeUnit = rateLimiterAnnotation.TIME_UNIT();
        RateLimiter rateLimiter = getRateLimiter(rate);
        if (!rateLimiter.tryAcquire(timeout , timeUnit)){
            return serviceDowng();
        }
        return proceedingJoinPoint.proceed();
    }

    /**
     * 获取令牌桶
     * @param rate
     * @return
     */
    private RateLimiter getRateLimiter(Double rate){
        // 获取当前URL
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        String requestURI = attributes.getRequest().getRequestURI();
        if (RATE_LIMITER_MAP.containsKey(requestURI)){
            return RATE_LIMITER_MAP.get(requestURI);
        }else {
            RateLimiter rateLimiter = RateLimiter.create(rate);
            RATE_LIMITER_MAP.put(requestURI, rateLimiter);
            return rateLimiter;
        }
    }

    /**
     * 服务降级
     * @return
     * @throws IOException
     */
    private Object serviceDowng() throws IOException {
        // 执行服务降级处理
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletResponse response = attributes.getResponse();
        response.setHeader("Content-type", "text/html;charset=UTF-8");
        try (PrintWriter writer = response.getWriter()) {
            writer.println("执行降级方法,亲,服务器忙！请稍后重试!");
        } catch (Exception e) {
        }
        return null;
    }
}
