/*
 * Copyright 2008 by hebei, All rights reserved.
 */
package org.freedom.view.action.jsp.security;

import org.freedom.core.view.action.AbstractViewAction;
import org.freedom.view.vo.security.FD000S001ViewObject;
import org.freedom.view.vo.security.FD000S004ViewObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 菜单树管理界面JspViewAction
 * 
 * @author 何贝
 * @since JDK1.5
 */
@Controller
public class FD000S004JspViewAction extends AbstractViewAction {

    private static final long serialVersionUID = 8782877423568374052L;

    /**
     * 显示菜单树管理界面
     * 
     * @return
     */
    @RequestMapping("/FD000S004JspViewAction_ShowPageAction.faces")
    public String showPageAction(Model model) {
        // 用户登录页面初始化
        FD000S004ViewObject outPutObj = new FD000S004ViewObject();

        model.addAttribute("FD000S004ViewObject", outPutObj);

        return "WEB-INF/jsp/security/FD000S004";
    }
}
