/*
 * Copyright 2008 by hebei, All rights reserved.
 */
package org.freedom.view.action.jsp.security;

import java.lang.reflect.InvocationTargetException;

import javax.servlet.http.HttpServletRequest;

import org.freedom.core.view.action.AbstractViewAction;
import org.freedom.view.vo.security.s001.FD000S001ViewObject;
import org.freedom.view.vo.security.s002.FD000S002ViewObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 用户登录成功--修改密码JspViewAction
 * 
 * @author 何贝
 * @since JDK1.5
 */
@Controller
public class FD000S002JspViewAction extends AbstractViewAction {

    private static final long serialVersionUID = 968684109252172382L;

    /**
     * 用户登录成功--修改密码
     * 
     * @param model
     * @return
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     */
    @RequestMapping("/FD000S002JspViewAction_ShowPageAction.faces")
    public String showPageAction(HttpServletRequest request, Model model) throws IllegalAccessException,
            InvocationTargetException {

        // 修改密码
        FD000S002ViewObject outPutObj = new FD000S002ViewObject();

        model.addAttribute("FD000S002ViewObject", outPutObj);

        return "WEB-INF/jsp/security/FD000S002";

    }

    /**
     * 用户退出,画面迁移到用户登录页面
     * 
     * @return
     */
    @RequestMapping("/UserLoginOutSystem.faces")
    public String userLoginOutSystem(HttpServletRequest request, Model model) {
        // 用户登录页面初始化
        FD000S001ViewObject outPutObj = new FD000S001ViewObject();
        // 删除Session登录用户信息
        removeUserInfo(request);

        model.addAttribute("FD000S001ViewObject", outPutObj);

        return "WEB-INF/jsp/security/FD000S001";
    }
}
