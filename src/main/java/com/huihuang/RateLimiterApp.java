package com.huihuang;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
@MapperScan(basePackages = { "com.huihuang.mapper"})
@SpringBootApplication
@ServletComponentScan
public class RateLimiterApp {

    public static void main(String[] arge){
        SpringApplication.run(RateLimiterApp.class, arge);
    }
}
