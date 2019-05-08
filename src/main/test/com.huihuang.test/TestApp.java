package com.huihuang.test;


import org.junit.jupiter.api.Test;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class TestApp {


    public static void main(String[] args){
        String url = "https://fanyi.baidu.com";
        System.err.println("fanyi.baidu.com".equals(getDomainName(url)));
        System.err.println(getDomainName(url));
        Map<String, String> map = new ConcurrentHashMap<>();
        System.err.println(map.remove("111"));
    }

    private static String getDomainName(String referer){
        return referer.substring(referer.indexOf("//") + 2);
    }
}
