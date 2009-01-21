/*
 * Copyright 2008 by hebei, All rights reserved.
 */
package org.freedom.view.action.normal.security;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.freedom.core.view.action.AbstractViewAction;
import org.freedom.view.SysConstant;
import org.freedom.view.action.vobj.security.s001.Security001ViewObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 默认画面迁移到用户登录页面ViewAction
 * 
 * @author 何贝
 * @since JDK1.5
 */
@Controller
public class Security001NormalViewAction extends AbstractViewAction {

    private static final long serialVersionUID = -5759646669247121953L;

    /**
     * 默认画面迁移到用户登录页面
     * 
     * @return
     */
    @RequestMapping("/index.faces")
    public String showPageAction(HttpServletRequest request, Model model) {
        // 用户登录页面初始化
        model.addAttribute("Security001ViewObject", new Security001ViewObject());

        // 第一次请求时设置web程序名称
        if (StringUtils.isBlank(SysConstant.WEB_PROJECT_NAME)) {
            SysConstant.WEB_PROJECT_NAME = request.getContextPath();
        }

        return "WEB-INF/jsp/security/security001";
    }
}
