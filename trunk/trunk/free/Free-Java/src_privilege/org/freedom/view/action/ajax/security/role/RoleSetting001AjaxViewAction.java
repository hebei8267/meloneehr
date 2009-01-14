/*
 * Copyright 2009 by hebei, All rights reserved.
 */
package org.freedom.view.action.ajax.security.role;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.freedom.core.domain.TreeNode;
import org.freedom.core.view.action.AbstractViewAction;
import org.freedom.services.permit.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author 何贝
 * @since JDK1.5
 */
@Controller
public class RoleSetting001AjaxViewAction extends AbstractViewAction {

    /**
     * 
     */
    private static final long serialVersionUID = -1984547290821193213L;
    @Autowired
    private IRoleService roleService;

    @RequestMapping("/security/role/roleSetting/001/getAllRoleInfoTreeAction.ajax")
    public void getAllRoleInfoTreeAction(HttpServletRequest request, HttpServletResponse response)
            throws ServletRequestBindingException, IOException, IllegalAccessException, InvocationTargetException {
        // 菜单树节点和其所有子节点信息
        TreeNode rootNode = roleService.getAllRoleInfoTreeService();
        
        JSONArray jSONArray = JSONArray.fromObject(rootNode.getChildren());
        response.setContentType(RESPONSE_CONTENT_TYPE);
        response.getWriter().write(jSONArray.toString());
    }

    public IRoleService getRoleService() {
        return roleService;
    }

    public void setRoleService(IRoleService roleService) {
        this.roleService = roleService;
    }
}
