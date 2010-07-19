package com.jquery.core.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.util.StringUtils;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.jquery.core.constant.SysWebConstant;

public class ControllerInterceptor extends HandlerInterceptorAdapter {
    /*
     * (non-Javadoc)
     * 
     * @see
     * org.springframework.web.servlet.handler.HandlerInterceptorAdapter#preHandle
     * (javax.servlet.http.HttpServletRequest,
     * javax.servlet.http.HttpServletResponse, java.lang.Object)
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 第一次请求时设置web程序名称
        System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
        if (StringUtils.hasText(SysWebConstant.WEB_PROJECT_NAME)) {
            SysWebConstant.WEB_PROJECT_NAME = request.getContextPath();
        }
        return super.preHandle(request, response, handler);
    }
}
