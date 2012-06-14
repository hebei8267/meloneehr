/*
 * Copyright 2008 by hebei, All rights reserved.
 */
package org.freedom.view.action.ajax.security;

import static org.freedom.view.constant.MesssageIDSecurity.ERROR_INPUT_OLD_PWD;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.freedom.core.view.action.AbstractViewAction;
import org.freedom.core.view.vo.ajax.JosnViewObject;
import org.freedom.services.security.ISecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 用户密码变更AjaxView
 * 
 * @author 何贝
 * @since JDK1.5
 */
@Controller
public class FD000S002AjaxViewAction extends AbstractViewAction {

    private static final long serialVersionUID = -4693702061279466832L;
    @Autowired
    private ISecurityService securityService;

    /**
     * 用户密码变更
     * 
     * @param request
     * @param response
     * @throws ServletRequestBindingException
     * @throws IOException
     * @throws IllegalAccessException
     * @throws InvocationTargetException
     */
    @RequestMapping("/FD000S002AjaxViewAction_ModPwdAction.ajax")
    public void modPwd_Action(HttpServletRequest request, HttpServletResponse response)
            throws ServletRequestBindingException, IOException, IllegalAccessException,
            InvocationTargetException {

        // 取得request里面的参数
        String userId = ServletRequestUtils.getStringParameter(request, "userId");
        String oldPassword = ServletRequestUtils.getStringParameter(request, "oldPassword");
        String newPassword = ServletRequestUtils.getStringParameter(request, "newPassword");

        if (!securityService.modUserPassword_Service(userId, oldPassword, newPassword)) {// 修改失败
            JosnViewObject jViewObj = new JosnViewObject(false);

            jViewObj.setResultMsg(getMessage(request, ERROR_INPUT_OLD_PWD));
            JSONObject jSONObject = JSONObject.fromObject(jViewObj);

            response.setContentType(RESPONSE_CONTENT_TYPE);
            response.getWriter().write(jSONObject.toString());
        } else {
            JosnViewObject jViewObj = new JosnViewObject();

            JSONObject jSONObject = JSONObject.fromObject(jViewObj);

            response.setContentType(RESPONSE_CONTENT_TYPE);
            response.getWriter().write(jSONObject.toString());
        }
    }

    public ISecurityService getSecurityService() {
        return securityService;
    }

    public void setSecurityService(ISecurityService securityService) {
        this.securityService = securityService;
    }
}
