/*
 * Copyright 2009 by hebei, All rights reserved.
 */
package org.freedom.view.action.normal.security.role;

import javax.servlet.http.HttpServletRequest;

import org.freedom.core.view.action.AbstractViewAction;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 角色&菜单树关联设定
 * 
 * @author 何贝
 * @since JDK1.5
 */
@Controller
public class RoleMenuSetting001NormalViewAction extends AbstractViewAction {

    /**
     * 
     */
    private static final long serialVersionUID = -1438704521541987452L;

    /**
     * 角色&菜单树关联设定
     * 
     * @param request
     * @return
     */
    @RequestMapping("/security/role/roleMenuSetting/001/showPageAction")
    public String showPageAction(HttpServletRequest request) {
        return "WEB-INF/jsp/security/role/roleMenuSetting001";
    }
}
