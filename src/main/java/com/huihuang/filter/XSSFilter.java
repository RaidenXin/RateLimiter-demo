package com.huihuang.filter;

import com.huihuang.httprequest.XssHttpServletRequestWrapper;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * XSS攻击过滤器
 */
@WebFilter(filterName = "xssFilter", urlPatterns = "/*")
public class XSSFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        // 程序防止XSS攻击原理
        // 1. 使用过滤器拦截所有参数
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        // 2.重新getParameter方法
        XssHttpServletRequestWrapper xssHttpServletRequestWrapper = new XssHttpServletRequestWrapper(req);
        // 放行程序，继续往下执行
        filterChain.doFilter(xssHttpServletRequestWrapper, servletResponse);
    }

    @Override
    public void destroy() {

    }
}
