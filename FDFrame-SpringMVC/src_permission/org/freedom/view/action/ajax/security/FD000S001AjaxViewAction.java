/*
 * Copyright 2008 by hebei, All rights reserved.
 */
package org.freedom.view.action.ajax.security;

import static org.freedom.view.constant.MesssageIDSecurity.ERROR_LOGIN_FAILED;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.freedom.core.domain.UserInfoSessionBean;
import org.freedom.core.view.action.AbstractViewAction;
import org.freedom.core.view.vo.ajax.JosnViewObject;
import org.freedom.entity.security.User;
import org.freedom.services.security.ISecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 用户登录AjaxView
 * 
 * @author 何贝
 * @since JDK1.5
 */
@Controller
public class FD000S001AjaxViewAction extends AbstractViewAction {

    private static final long serialVersionUID = -6277601235716651095L;
    @Autowired
    private ISecurityService securityService;

    /**
     * 用户登录
     * 
     * @param request
     * @param response
     * @throws ServletRequestBindingException
     * @throws IOException
     * @throws IllegalAccessException
     * @throws InvocationTargetException
     */
    @RequestMapping("/FD000S001AjaxViewAction_LoginAction.ajax")
    public void login_Action(HttpServletRequest request, HttpServletResponse response)
            throws ServletRequestBindingException, IOException, IllegalAccessException,
            InvocationTargetException {

        // 取得request里面的参数
        String userId = ServletRequestUtils.getStringParameter(request, "userId");
        String password = ServletRequestUtils.getStringParameter(request, "password");

        User user = securityService.userLogin_Service(userId, password);
        if (user == null) {// 输入用户名或密码错误

            JosnViewObject jViewObj = new JosnViewObject(false);

            jViewObj.setResultMsg(getMessage(request, ERROR_LOGIN_FAILED));
            JSONObject jSONObject = JSONObject.fromObject(jViewObj);

            response.setContentType(RESPONSE_CONTENT_TYPE);
            response.getWriter().write(jSONObject.toString());
        } else {// 用户登录成功

            JosnViewObject jViewObj = new JosnViewObject();
            JSONObject jSONObject = JSONObject.fromObject(jViewObj);

            response.setContentType(RESPONSE_CONTENT_TYPE);
            response.getWriter().write(jSONObject.toString());

            saveUserInfoToSession(request, user);
        }
    }

    /**
     * 初始化 登录用户信息
     */
    private void saveUserInfoToSession(HttpServletRequest request, User user) {

        UserInfoSessionBean userInfo = new UserInfoSessionBean();
        userInfo.setUserId(user.getId());
        userInfo.setUserName(user.getName());
        userInfo.setRoleId(user.getRoleID());

        saveUserInfo(request, userInfo);
    }

    public ISecurityService getSecurityService() {
        return securityService;
    }

    public void setSecurityService(ISecurityService securityService) {
        this.securityService = securityService;
    }

}