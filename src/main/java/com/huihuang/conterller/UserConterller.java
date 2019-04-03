package com.huihuang.conterller;

import com.huihuang.annotation.RateLimiterAnnotation;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

@RestController
public class UserConterller {

    @RateLimiterAnnotation(rate = 0.5,timeout = 500l,TIME_UNIT = TimeUnit.MILLISECONDS)
    @RequestMapping("/getUserName")
    public String getUserName(){
        return "zhangsan";
    }
}
