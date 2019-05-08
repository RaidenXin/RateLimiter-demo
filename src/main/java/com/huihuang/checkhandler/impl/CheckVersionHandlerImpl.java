package com.huihuang.checkhandler.impl;

import com.huihuang.checkhandler.CheckVersionHandler;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.Map;

public class CheckVersionHandlerImpl implements CheckVersionHandler {

    private static final String STATUS = "STATUS";
    private static final String RESULT = "RESULT";
    @Override
    public Map<String, Object> chack(Object[] param,String version) {
        String id = (String) param[0];
        String newVersion = (String) param[1];
        Map<String, Object> result = new HashMap<>();
        if (StringUtils.isEmpty(newVersion) || convertVersion(newVersion).compareTo(convertVersion(version)) < 0){
            result.put(STATUS, true);
            result.put(RESULT, "lisi");
        }else {
            result.put(STATUS, false);
        }
        return result;
    }

    private Integer convertVersion(String version){
        return Integer.valueOf(version.replaceAll("\\.",""));
    }
}
