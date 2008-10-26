/*
 * Copyright 2008 by hebei, All rights reserved.
 */
package org.freedom.view.interceptor;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import org.freedom.core.view.action.AbstractViewAction;
import org.freedom.core.view.vo.JosnViewObject;
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
    private List<String> unLoginAllowAccessClassNameList;
    /**
     * SessionTimeOut Action地址列表
     */
    private String sessionTimeOut;

    public void setUnLoginAllowAccessClassNameList(List<String> unLoginAllowAccessClassNameList) {
        this.unLoginAllowAccessClassNameList = unLoginAllowAccessClassNameList;
    }

    public void setSessionTimeOut(String sessionTimeOut) {
        this.sessionTimeOut = sessionTimeOut;
    }

    private final static String AJAX_VIEW_ACTION = "AjaxViewAction";

    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println(handler.getClass());
        if (unLoginAllowAccessClassNameList.contains(handler.getClass().getName())) {
            // 用户登录Action
            return true;
        } else if (existSessionInfo(request)) {
            // 登录用户的其他Action
            return true;
        } else { // 用户未登录 引导到登录界面
            if (handler.getClass().getName().endsWith(AJAX_VIEW_ACTION)) {// Ajax请求
                JosnViewObject jViewObj = new JosnViewObject(false, true);

                JSONObject jSONObject = JSONObject.fromObject(jViewObj);

                response.setContentType(AbstractViewAction.RESPONSE_CONTENT_TYPE);
                response.getWriter().write(jSONObject.toString());
            } else {
                response.sendRedirect(sessionTimeOut);
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
