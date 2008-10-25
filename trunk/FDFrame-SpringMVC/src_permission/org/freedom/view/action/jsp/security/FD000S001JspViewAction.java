/*
 * Copyright 2008 by hebei, All rights reserved.
 */
package org.freedom.view.action.jsp.security;

import java.lang.reflect.InvocationTargetException;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.beanutils.BeanUtils;
import org.freedom.core.domain.UserInfoSessionBean;
import org.freedom.core.view.action.AbstractViewAction;
import org.freedom.view.vo.security.FD000S001ViewObject;
import org.freedom.view.vo.security.FD000S002ViewObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import static org.freedom.view.constant.MesssageIDSecurity.ERROR_LOGIN_FAILED;

/**
 * 用户登录成功页面JspViewAction
 * 
 * @author 何贝
 * @since JDK1.5
 */
@Controller
public class FD000S001JspViewAction extends AbstractViewAction {

    private static final long serialVersionUID = 968684109252172382L;

    /**
     * 用户登录成功
     * 
     * @param model
     * @return
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     */
    @RequestMapping("/FD000S001JspViewAction_LoginSuccessAction.faces")
    public String loginSuccessAction(HttpServletRequest request, Model model) throws IllegalAccessException,
            InvocationTargetException {
        FD000S001ViewObject vObj = new FD000S001ViewObject();
        // 取得request里面的参数
        BeanUtils.populate(vObj, request.getParameterMap());

        // 取得登录用户信息
        UserInfoSessionBean user = getUserInfoInSession(request);

        if (user != null && !user.getUserId().equals(vObj.getUserId())) {// Session超时
            vObj.addErrorMessage(getMessage(request, ERROR_LOGIN_FAILED));

            model.addAttribute("FD000S001ViewObject", vObj);

            return "WEB-INF/jsp/security/FD000S001";
        } else if (Boolean.TRUE == vObj.getChangePassword()) {// 修改密码
            FD000S002ViewObject nextVObj = new FD000S002ViewObject();

            model.addAttribute("FD000S002ViewObject", nextVObj);

            return "WEB-INF/jsp/security/FD000S002";
        } else {// 操作区主界面
            return "";
        }

    }
}
