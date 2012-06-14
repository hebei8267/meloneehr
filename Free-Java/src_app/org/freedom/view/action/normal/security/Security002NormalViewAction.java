/*
 * Copyright 2008 by hebei, All rights reserved.
 */
package org.freedom.view.action.normal.security;

import javax.servlet.http.HttpServletRequest;

import org.freedom.view.action.AbstractViewAction;
import org.freedom.view.module.security.s001.Security001ViewObject;
import org.freedom.view.module.security.s002.Security002ViewObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 用户修改密码和用户退出
 * 
 * @author 何贝
 * @since JDK1.5
 */
@Controller
public class Security002NormalViewAction extends AbstractViewAction {

    /**
     * 
     */
    private static final long serialVersionUID = 3462436435609245122L;

    /**
     * 用户登录成功--修改密码
     * 
     * @param request
     * @param model
     * @return
     */
    @RequestMapping("/security/002/showPageAction")
    public String showPageAction(HttpServletRequest request, Model model) {
        model.addAttribute("Security002ViewObject", new Security002ViewObject());

        return "WEB-INF/jsp/security/security002";
    }

    /**
     * 用户退出,画面迁移到用户登录页面
     * 
     * @param request
     * @param model
     * @return
     */
    @RequestMapping("/security/002/exitSystemAction")
    public String exitSystemAction(HttpServletRequest request, Model model) {
        // 删除Session登录用户信息
        removeUserInfo(request);

        model.addAttribute("Security001ViewObject", new Security001ViewObject());
        return "WEB-INF/jsp/security/security001";
    }
}
