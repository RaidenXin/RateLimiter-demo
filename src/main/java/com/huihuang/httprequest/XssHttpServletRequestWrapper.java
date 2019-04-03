package com.huihuang.httprequest;

import org.apache.commons.lang.StringEscapeUtils;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

public class XssHttpServletRequestWrapper extends HttpServletRequestWrapper {


    private HttpServletRequest request;

    public XssHttpServletRequestWrapper(HttpServletRequest request) {
        super(request);
        this.request = request;
    }

    @Override
    public String getParameter(String name) {
        // 获取之前的参数
        String olValue = super.getParameter(name);
        if (!StringUtils.isEmpty(olValue)) {
            // 将特殊字符转换成html展示 使用(StringEscapeUtils.escapeHtml(name)转换特殊参数
            olValue = StringEscapeUtils.escapeHtml(olValue);
        }
        return olValue;
    }
}
