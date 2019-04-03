package com.huihuang.test;


import org.junit.jupiter.api.Test;

public class TestApp {


    public static void main(String[] args){
        String url = "https://fanyi.baidu.com";
        System.err.println("fanyi.baidu.com".equals(getDomainName(url)));
        System.err.println(getDomainName(url));
    }

    private static String getDomainName(String referer){
        return referer.substring(referer.indexOf("//") + 2);
    }
}
