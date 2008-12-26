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

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
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

    private static final long serialVersionUID = -7494181740833493859L;
    /** Ajax处理结果 默认页面编码UTF-8 */
    public static String RESPONSE_CONTENT_TYPE = "text/html;charset=UTF-8;";
    public static final String USER_INFO = "userInfo";

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

    private static String ERROR_INFO = "ERROR_";
    private static String NEW_LINE = "<br>";

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
        if (msgKey.startsWith(ERROR_INFO)) {// 消息级别--错误级别
            return NEW_LINE + msgObj.getMessage(msgKey, args, locale);
        }
        return msgObj.getMessage(msgKey, args, locale);
    }

    /**
     * 保存Session登录用户信息
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
     * 取得Session登录用户信息
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
     * 删除Session登录用户信息
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

    /**
     * 将json对象列表转换成pojo对象列表
     * 
     * @param jsonString
     * @param pojoClass
     * @return
     */
    @SuppressWarnings("unchecked")
    protected List jsonStr2PojoList(String jsonString, Class pojoClass) {

        List list = new ArrayList();

        if (!StringUtils.isEmpty(jsonString)) {
            JSONArray jsonArray = JSONArray.fromObject(jsonString);

            JSONObject jsonObject = null;
            Object pojoValue = null;

            for (int i = 0; i < jsonArray.size(); i++) {
                jsonObject = jsonArray.getJSONObject(i);
                pojoValue = JSONObject.toBean(jsonObject, pojoClass);
                list.add(pojoValue);
            }
        }
        return list;
    }
}
