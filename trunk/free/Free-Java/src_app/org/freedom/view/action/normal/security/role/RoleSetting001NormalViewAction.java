/*
 * Copyright 2009 by hebei, All rights reserved.
 */
package org.freedom.view.action.normal.security.role;

import javax.servlet.http.HttpServletRequest;

import org.freedom.core.view.action.AbstractViewAction;
import org.freedom.view.action.vobj.security.role.rs001.RoleSetting001ViewObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 角色设定
 * 
 * @author 何贝
 * @since JDK1.5
 */
@Controller
public class RoleSetting001NormalViewAction extends AbstractViewAction {

    /**
     * 
     */
    private static final long serialVersionUID = -9213022517369443093L;

    /**
     * 角色设定
     * 
     * @param request
     * @param model
     * @return
     */
    @RequestMapping("/security/role/roleSetting/001/showPageAction")
    public String showPageAction(HttpServletRequest request, Model model) {
        model.addAttribute("RoleSetting001ViewObject", new RoleSetting001ViewObject());

        return "WEB-INF/jsp/security/role/roleSetting001";
    }
}
