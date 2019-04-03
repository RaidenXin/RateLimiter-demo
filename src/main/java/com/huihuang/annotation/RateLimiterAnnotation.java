package com.huihuang.annotation;

import java.lang.annotation.*;
import java.util.concurrent.TimeUnit;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface RateLimiterAnnotation {

    //生成令牌的速率
    double rate();
    //超时时间
    long timeout();
    //超时时间类型
    TimeUnit TIME_UNIT();
}
