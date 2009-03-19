/*
 * Copyright 2008 by hebei, All rights reserved.
 */
package org.freedom.sys.interceptor;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.freedom.sys.SysConstant;
import org.freedom.sys.view.JosnViewObject;
import org.freedom.view.action.AbstractViewAction;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

/**
 * 请求访问过滤
 * 
 * @author 何贝
 * @since JDK1.5
 */
public class LoginUserAccessInterceptor extends HandlerInterceptorAdapter {

    /**
     * 未登录用户允许访问的Action地址列表
     */
    private List<String> notLoginAllowAccessClassNameList;
    /**
     * SessionTimeOut Action地址列表
     */
    private String sessionTimeOut;

    public void setNotLoginAllowAccessClassNameList(List<String> notLoginAllowAccessClassNameList) {
        this.notLoginAllowAccessClassNameList = notLoginAllowAccessClassNameList;
    }

    public void setSessionTimeOut(String sessionTimeOut) {
        this.sessionTimeOut = sessionTimeOut;
    }

    private final static String AJAX_VIEW_ACTION = "AjaxViewAction";

    /*
     * (non-Javadoc)
     * 
     * @seeorg.springframework.web.servlet.handler.HandlerInterceptorAdapter#preHandle(javax.servlet.http.
     * HttpServletRequest, javax.servlet.http.HttpServletResponse,
     * java.lang.Object)
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 第一次请求时设置web程序名称
        if (StringUtils.isBlank(SysConstant.WEB_PROJECT_NAME)) {
            SysConstant.WEB_PROJECT_NAME = request.getContextPath();
        }
        return notLoginCheck(request, response, handler);
    }

    /**
     * 未登录用户检查
     * 
     * @param request HttpServletRequest
     * @param response HttpServletResponse
     * @param handler 响应操作对象
     * @return
     * @throws IOException
     */
    private boolean notLoginCheck(HttpServletRequest request, HttpServletResponse response, Object handler) throws IOException {
        System.out.println(handler.getClass().getName());
        if (notLoginAllowAccessClassNameList.contains(handler.getClass().getName())) {
            // 用户登录Action
            return true;
        } else if (existSessionInfo(request)) {
            // 登录用户的其他Action
            return true;
        } else { // 用户未登录 引导到登录界面
            if (handler.getClass().getName().endsWith(AJAX_VIEW_ACTION)) {// Ajax请求

                JSONObject jSONObject = JSONObject.fromObject(new JosnViewObject(false, true));

                response.setContentType(AbstractViewAction.RESPONSE_CONTENT_TYPE);
                response.getWriter().write(jSONObject.toString());
            } else {
                response.sendRedirect(SysConstant.WEB_PROJECT_NAME + sessionTimeOut);
            }

            return false;
        }
    }

    /**
     * 校验Session信息是否存在
     * 
     * @param request
     * @return
     */
    private boolean existSessionInfo(HttpServletRequest request) {
        HttpSession session = request.getSession();
        if (session != null) {
            if (session.getAttribute(AbstractViewAction.USER_INFO) != null) {
                return true;
            }
        }
        return false;
    }

}
