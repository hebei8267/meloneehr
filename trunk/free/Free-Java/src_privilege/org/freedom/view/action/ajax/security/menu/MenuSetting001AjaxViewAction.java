/*
 * Copyright 2009 by hebei, All rights reserved.
 */
package org.freedom.view.action.ajax.security.menu;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.freedom.core.domain.TreeNode;
import org.freedom.core.view.action.AbstractViewAction;
import org.freedom.entity.ui.MenuNode;
import org.freedom.services.permit.IMenuNodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 菜单树设定
 * 
 * @author 何贝
 * @since JDK1.5
 */
@Controller
public class MenuSetting001AjaxViewAction extends AbstractViewAction {

    /**
     * 
     */
    private static final long serialVersionUID = -1302066115972903271L;
    @Autowired
    private IMenuNodeService menuNodeService;

    @RequestMapping("/security/menu/menuSetting/001/getAllMenuInfoTreeAction.ajax")
    public void getAllMenuInfoTreeAction(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // 取得所有菜单结点树信息(根节点开始)不包含权限校验
        TreeNode rootNode = menuNodeService.getMenuNodeInfoTreeService(MenuNode.MENU_NODE_TREE_ROOT_ID);

        JSONArray jSONArray = JSONArray.fromObject(rootNode.getChildren());
        response.setContentType(RESPONSE_CONTENT_TYPE);
        response.getWriter().write(jSONArray.toString());
    }

    public IMenuNodeService getMenuNodeService() {
        return menuNodeService;
    }

    public void setMenuNodeService(IMenuNodeService menuNodeService) {
        this.menuNodeService = menuNodeService;
    }

}
