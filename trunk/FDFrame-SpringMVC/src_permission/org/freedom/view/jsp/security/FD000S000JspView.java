/*
 * Copyright 2008 by hebei, All rights reserved.
 */
package org.freedom.view.jsp.security;

import org.freedom.core.view.AbstractViewBean;
import org.freedom.view.vo.FD000S001ViewObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 默认画面迁移到用户登录页面JspView
 * 
 * @author 何贝
 * @since JDK1.5
 */
@Controller
public class FD000S000JspView extends AbstractViewBean {

    private static final long serialVersionUID = 6066674675465360677L;

    /**
     * 默认画面迁移到用户登录页面
     * 
     * @return
     */
    @RequestMapping("/index.faces")
    @ModelAttribute
    public String defaultTransplantAction(Model model) {
        // 用户登录页面初始化
        FD000S001ViewObject vObj = new FD000S001ViewObject();

        model.addAttribute("FD000S001ViewObject", vObj);

        return "WEB-INF/jsp/security/FD000S001";
    }

}
