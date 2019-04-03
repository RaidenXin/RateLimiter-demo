package com.huihuang.filter;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.StringUtils;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * 防盗过滤器
 */
@WebFilter(filterName = "guardAgainstTheftFilter", urlPatterns = "/imgs/*")
public class GuardAgainstTheftFilter implements Filter {

    private static final String Referer = "Referer";

    @Value("${domain.name}")
    private String domainName;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        //获取访问来源
        String referer = req.getHeader(Referer);
        if (StringUtils.isEmpty(referer)){
            //如果来源为空 返回友好提示图片
            req.getRequestDispatcher("/imgs/error.png").forward(req, servletResponse);
            return;
        }
        if (!referer.equals(domainName)){
            //如果来源不是本服务器域名 则返回友好提示图片
            req.getRequestDispatcher("/imgs/error.png").forward(req, servletResponse);
            return;
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }

    private String getDomainName(String referer){
        return referer.substring(referer.indexOf("//") + 2);
    }

    @Override
    public void destroy() {

    }
}
