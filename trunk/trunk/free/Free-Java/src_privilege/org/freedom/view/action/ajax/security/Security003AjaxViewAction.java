/*
 * Copyright 2008 by hebei, All rights reserved.
 */
package org.freedom.view.action.ajax.security;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.freedom.core.domain.TreeNode;
import org.freedom.core.domain.UserInfoSessionBean;
import org.freedom.core.view.action.AbstractViewAction;
import org.freedom.services.permit.IMenuNodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author 何贝
 * @since JDK1.5
 */
@Controller
public class Security003AjaxViewAction extends AbstractViewAction {

    /**
     * 
     */
    private static final long serialVersionUID = 5384836167102534578L;
    @Autowired
    private IMenuNodeService menuNodeService;

    /**
     * 取得树节点数据
     * 
     * @param request
     * @param response
     * @throws ServletRequestBindingException
     * @throws IOException
     * @throws IllegalAccessException
     * @throws InvocationTargetException
     */
    @RequestMapping("/security/003/getAreaMenuNodeTreeAction.ajax")
    public void getAreaMenuNodeTreeAction(HttpServletRequest request, HttpServletResponse response)
            throws ServletRequestBindingException, IOException, IllegalAccessException, InvocationTargetException {

        // 取得request里面的参数
        String nodeId = ServletRequestUtils.getStringParameter(request, "id");

        // 取得登录用户信息
        UserInfoSessionBean user = getUserInfoInSession(request);
        // 菜单树节点和其所有子节点信息
        TreeNode rootNode = menuNodeService.getMenuNodeInfoTreeService(nodeId, user.getUserId(), user.getRoleId());

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
