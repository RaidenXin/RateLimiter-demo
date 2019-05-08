package com.huihuang.annotation;

import java.lang.annotation.*;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface CheckVersion {

    String version();
    Class<?> clazz();
}
