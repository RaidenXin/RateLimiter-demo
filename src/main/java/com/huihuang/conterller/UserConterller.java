package com.huihuang.conterller;

import com.huihuang.annotation.CheckVersion;
import com.huihuang.annotation.RateLimiterAnnotation;
import com.huihuang.checkhandler.impl.CheckVersionHandlerImpl;
import com.huihuang.model.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

@RestController
public class UserConterller {

    @RateLimiterAnnotation(rate = 0.5,timeout = 500l,TIME_UNIT = TimeUnit.MILLISECONDS)
    @RequestMapping("/getUserName")
    public String getUserName(){
        return "zhangsan";
    }

    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public String login(User user){
        return "";
    }

    @GetMapping("/getUser")
    @CheckVersion(version = "1.0.1", clazz = CheckVersionHandlerImpl.class)
    public String getUser(String id,String version){
        return "zhangsan";
    }
}
