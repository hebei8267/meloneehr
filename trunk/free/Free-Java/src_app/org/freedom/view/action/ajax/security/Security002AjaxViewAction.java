/*
 * Copyright 2008 by hebei, All rights reserved.
 */
package org.freedom.view.action.ajax.security;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.freedom.core.view.action.AbstractViewAction;
import org.freedom.core.view.vo.ajax.JosnViewObject;
import org.freedom.services.security.ILoginLogoutService;
import org.freedom.view.action.SecurityMesssageID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 修改用户密码
 * 
 * @author 何贝
 * @since JDK1.5
 */
@Controller
public class Security002AjaxViewAction extends AbstractViewAction {

    /**
     * 
     */
    private static final long serialVersionUID = 7381281852688281621L;
    @Autowired
    private ILoginLogoutService loginLogoutService;

    @RequestMapping("/security/002/modPwdAction.ajax")
    public void modPwdAction(HttpServletRequest request, HttpServletResponse response) throws ServletRequestBindingException,
            IOException {
        // 取得request里面的参数
        String userId = ServletRequestUtils.getStringParameter(request, "userId");
        String oldPassword = ServletRequestUtils.getStringParameter(request, "oldPassword");
        String newPassword = ServletRequestUtils.getStringParameter(request, "newPassword");

        if (!loginLogoutService.modUserPwdService(userId, oldPassword, newPassword)) {// 修改失败
            JosnViewObject jViewObj = new JosnViewObject(false);

            jViewObj.setResultMsg(getMessage(request, SecurityMesssageID.ERROR_INVALIDATE_INPUT_PWD));
            JSONObject jSONObject = JSONObject.fromObject(jViewObj);

            response.setContentType(RESPONSE_CONTENT_TYPE);
            response.getWriter().write(jSONObject.toString());
        } else {

            JSONObject jSONObject = JSONObject.fromObject(new JosnViewObject());

            response.setContentType(RESPONSE_CONTENT_TYPE);
            response.getWriter().write(jSONObject.toString());
        }

    }

    public ILoginLogoutService getLoginLogoutService() {
        return loginLogoutService;
    }

    public void setLoginLogoutService(ILoginLogoutService loginLogoutService) {
        this.loginLogoutService = loginLogoutService;
    }
}
