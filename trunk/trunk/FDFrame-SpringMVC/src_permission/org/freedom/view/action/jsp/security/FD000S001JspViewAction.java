/*
 * Copyright 2008 by hebei, All rights reserved.
 */
package org.freedom.view.action.jsp.security;

import org.freedom.core.view.action.AbstractViewAction;
import org.freedom.view.vo.security.s001.FD000S001ViewObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 默认画面迁移到用户登录页面JspViewAction
 * 
 * @author 何贝
 * @since JDK1.5
 */
@Controller
public class FD000S001JspViewAction extends AbstractViewAction {

    private static final long serialVersionUID = 6066674675465360677L;

    /**
     * 默认画面迁移到用户登录页面
     * 
     * @return
     */
    @RequestMapping("/index.faces")
    public String showPage_Action(Model model) {
        // 用户登录页面初始化
        FD000S001ViewObject outPutObj = new FD000S001ViewObject();

        model.addAttribute("FD000S001ViewObject", outPutObj);

        return "WEB-INF/jsp/security/FD000S001";
    }

}
