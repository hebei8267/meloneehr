/*
 * Copyright 2008 by hebei, All rights reserved.
 */
package org.freedom.core.view.action;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.freedom.core.bean.BaseBean;
import org.freedom.core.domain.UserInfoSessionBean;
import org.freedom.core.util.WebApplicationContextUtil;
import org.springframework.context.support.ResourceBundleMessageSource;

/**
 * 公共ViewAction对象基类
 * 
 * @author 何贝
 * @since JDK1.5
 */
public abstract class AbstractViewAction extends BaseBean {
    private final String USER_INFO = "userInfo";
    private List<String> msgList = new ArrayList<String>();

    public AbstractViewAction() {

    }

    /** Bean生命周期中初始化方法 */
    @PostConstruct
    protected void _create() {
        create();
    }

    /** Bean生命周期中初始化方法 */
    public void create() {

    }

    /** Bean生命周期中销毁方法 */
    @PreDestroy
    protected void _destroy() {
        destroy();
    }

    /** Bean生命周期中销毁方法 */
    public void destroy() {

    }

    /**
     * 默认初始化方法
     */
    public void init() {

    }

    /**
     * 添加错误消息
     * 
     * @param request HttpServletRequest
     * @param msgKey 消息内容Key
     */
    protected void addErrorMessage(HttpServletRequest request, String msgKey) {
        String msgStr = getMessage(request, msgKey);
        msgList.add(msgStr);
    }

    /**
     * 添加错误消息
     * 
     * @param request HttpServletRequest
     * @param msgKey 消息内容Key
     * @param args 消息Param
     */
    protected void addErrorMessage(HttpServletRequest request, String msgKey, Object[] args) {
        String msgStr = getMessage(request, msgKey, args);
        msgList.add(msgStr);
    }

    /**
     * 添加错误消息
     * 
     * @param request HttpServletRequest
     * @param msgKey 消息内容Key
     * @param args 消息Param
     * @param locale Locale
     */
    protected void addErrorMessage(HttpServletRequest request, String msgKey, Object[] args, Locale locale) {
        String msgStr = getMessage(request, msgKey, args, locale);
        msgList.add(msgStr);
    }

    /**
     * 取得消息资源
     * 
     * @param request HttpServletRequest
     * @param msgKey 消息Key
     * @return 消息
     */
    protected String getMessage(HttpServletRequest request, String msgKey) {
        return getMessage(request, msgKey, null, Locale.CHINA);
    }

    /**
     * 取得消息资源
     * 
     * @param request HttpServletRequest
     * @param msgKey 消息Key
     * @param args 消息Param
     * @return 消息
     */
    protected String getMessage(HttpServletRequest request, String msgKey, Object[] args) {
        return getMessage(request, msgKey, args, Locale.CHINA);
    }

    /**
     * 取得消息资源
     * 
     * @param request HttpServletRequest
     * @param msgKey 消息Key
     * @param args 消息Param
     * @param locale Locale
     * @return
     */
    protected String getMessage(HttpServletRequest request, String msgKey, Object[] args, Locale locale) {
        ResourceBundleMessageSource msgObj = (ResourceBundleMessageSource) WebApplicationContextUtil
                .getApplicationBean(request, "messageSource");

        return msgObj.getMessage(msgKey, args, locale);
    }

    /**
     * 保存登录用户信息
     * 
     * @param request HttpServletRequest
     * @param userInfo 登录用户信息
     * @return 执行结果
     */
    protected boolean saveUserInfo(HttpServletRequest request, UserInfoSessionBean userInfo) {
        HttpSession session = request.getSession();
        if (session != null) {
            session.setAttribute(USER_INFO, userInfo);
            return true;
        }
        return false;
    }

    /**
     * 取得登录用户信息
     * 
     * @param request HttpServletRequest
     * @return 登录用户信息
     */
    protected UserInfoSessionBean getUserInfoInSession(HttpServletRequest request) {
        HttpSession session = request.getSession();
        if (session != null) {
            return (UserInfoSessionBean) session.getAttribute(USER_INFO);
        }
        return null;
    }

    /**
     * 删除登录用户信息
     * 
     * @param request HttpServletRequest
     * @return 执行结果
     */
    protected boolean removeUserInfo(HttpServletRequest request) {
        HttpSession session = request.getSession();
        if (session != null) {
            session.removeAttribute(USER_INFO);
            return true;
        }
        return false;
    }

}
