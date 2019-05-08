package com.huihuang.aop;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletResponse;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@Aspect
@Component
public class JavascrIptAop {

    //实际项目中可以使用Redis
    private static final Map<String, String> TOKEN_MAP = new ConcurrentHashMap<>();

    @Before("@annotation(com.huihuang.annotation.CreateToken)")
    public void createToken(){
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletResponse response = attributes.getResponse();
        String token = String.valueOf(UUID.randomUUID());
        response.setHeader(token, token);
        TOKEN_MAP.put(token,token);
    }
}
