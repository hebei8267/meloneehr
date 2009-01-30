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
import org.freedom.core.domain.UserInfoSessionBean;
import org.freedom.core.view.action.AbstractViewAction;
import org.freedom.entity.ui.MenuNode;
import org.freedom.services.permit.IMenuNodeService;
import org.freedom.services.permit.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 角色#菜单树关联设定
 * 
 * @author 何贝
 * @since JDK1.5
 */
@Controller
public class RoleMenuSetting001AjaxViewAction extends AbstractViewAction {

    /**
     * 
     */
    private static final long serialVersionUID = 6799162176912990024L;
    @Autowired
    private IRoleService roleService;
    @Autowired
    private IMenuNodeService menuNodeService;

    @RequestMapping("/security/role/roleMenuSetting/001/getAllRoleInfoTreeAction.ajax")
    public void getAllRoleInfoTreeAction(HttpServletRequest request, HttpServletResponse response)
            throws ServletRequestBindingException, IOException, IllegalAccessException, InvocationTargetException {
        // 菜单树节点和其所有子节点信息
        TreeNode rootNode = roleService.getAllRoleInfoTreeService();

        JSONArray jSONArray = JSONArray.fromObject(rootNode.getChildren());
        response.setContentType(RESPONSE_CONTENT_TYPE);
        response.getWriter().write(jSONArray.toString());
    }

    @RequestMapping("/security/role/roleMenuSetting/001/getAllMenuInfoTreeAction.ajax")
    public void getAllMenuInfoTreeAction(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // 登录用户信息
        UserInfoSessionBean userInfo = getUserInfoInSession(request);
        // 取得菜单结点树信息(指定根节点开始)包含权限校验
        TreeNode rootNode = menuNodeService.getMenuNodeInfoTreeEmbodyPermitService(MenuNode.MENU_NODE_TREE_ROOT_ID, userInfo
                .getRoleId());

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

    public IMenuNodeService getMenuNodeService() {
        return menuNodeService;
    }

    public void setMenuNodeService(IMenuNodeService menuNodeService) {
        this.menuNodeService = menuNodeService;
    }
}
