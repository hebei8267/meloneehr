/*
 * Copyright 2009 by hebei, All rights reserved.
 */
package org.freedom.view.action.normal.security.menu;

import javax.servlet.http.HttpServletRequest;

import org.freedom.view.action.AbstractViewAction;
import org.freedom.view.module.security.menu.ms001.MenuSetting001ViewObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 菜单树设定
 * 
 * @author 何贝
 * @since JDK1.5
 */
@Controller
public class MenuSetting001NormalViewAction extends AbstractViewAction {

    /**
     * 
     */
    private static final long serialVersionUID = 4074766193298342766L;

    /**
     * 菜单树设定
     * 
     * @param request
     * @param model
     * @return
     */
    @RequestMapping("/security/menu/menuSetting/001/showPageAction")
    public String showPageAction(HttpServletRequest request, Model model) {
        model.addAttribute("MenuSetting001ViewObject", new MenuSetting001ViewObject());

        return "WEB-INF/jsp/security/menu/menuSetting001";
    }
}
