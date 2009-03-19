/*
 * Copyright 2008 by hebei, All rights reserved.
 */
package org.freedom.view.action.ajax.security;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.freedom.entity.common.Role;
import org.freedom.entity.common.User;
import org.freedom.services.security.ILoginLogoutService;
import org.freedom.sys.modules.UserInfoSessionBean;
import org.freedom.sys.view.JosnViewObject;
import org.freedom.view.SecurityMesssageID;
import org.freedom.view.action.AbstractViewAction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 用户登录
 * 
 * @author 何贝
 * @since JDK1.5
 */
@Controller
public class Security001AjaxViewAction extends AbstractViewAction {

    /**
     * 
     */
    private static final long serialVersionUID = -7118588478153143314L;
    @Autowired
    private ILoginLogoutService loginLogoutService;

    @RequestMapping("/security/001/loginAction.ajax")
    public void loginAction(HttpServletRequest request, HttpServletResponse response) throws ServletRequestBindingException,
            IOException {
        // 取得request里面的参数
        String userId = ServletRequestUtils.getStringParameter(request, "userId");
        String password = ServletRequestUtils.getStringParameter(request, "password");

        User user = loginLogoutService.userLoginService(userId, password);
        if (user == null) {// 输入用户名或密码错误

            JosnViewObject jViewObj = new JosnViewObject(false);

            jViewObj.setResultMsg(getMessage(request, SecurityMesssageID.ERROR_LOGIN_FAILED));
            JSONObject jSONObject = JSONObject.fromObject(jViewObj);

            response.setContentType(RESPONSE_CONTENT_TYPE);
            response.getWriter().write(jSONObject.toString());
        } else {// 用户登录成功

            JSONObject jSONObject = JSONObject.fromObject(new JosnViewObject());

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
        Role role = loginLogoutService.getRoleInfoService(user.getRoleID());
        if (role != null) {
            userInfo.setRoleName(role.getName());
        }

        saveUserInfo(request, userInfo);
    }

    public ILoginLogoutService getLoginLogoutService() {
        return loginLogoutService;
    }

    public void setLoginLogoutService(ILoginLogoutService loginLogoutService) {
        this.loginLogoutService = loginLogoutService;
    }
}
