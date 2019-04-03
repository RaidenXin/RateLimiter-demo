package com.huihuang;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication
@ServletComponentScan
public class RateLimiterApp {

    public static void main(String[] arge){
        SpringApplication.run(RateLimiterApp.class, arge);
    }
}
