/*
 * Copyright 2008 by hebei, All rights reserved.
 */
package org.freedom.view.action.ajax.security;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.apache.commons.beanutils.BeanUtils;
import org.freedom.core.domain.UserInfoSessionBean;
import org.freedom.core.view.action.AbstractViewAction;
import org.freedom.core.view.vo.ajax.UITreeNode;
import org.freedom.services.ui.IMenuNodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 工作区主界面AjaxView
 * 
 * @author 何贝
 * @since JDK1.5
 */
@Controller
public class FD000S003AjaxViewAction extends AbstractViewAction {

    private static final long serialVersionUID = 7180328524977023850L;

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
    @RequestMapping("/FD000S003AjaxViewAction_GetTreeNodeInfoAction.ajax")
    public void getTreeNodeInfoAction(HttpServletRequest request, HttpServletResponse response)
            throws ServletRequestBindingException, IOException, IllegalAccessException,
            InvocationTargetException {
        UITreeNode inputObj = new UITreeNode();

        // 取得request里面的参数
        BeanUtils.populate(inputObj, request.getParameterMap());
        // 取得登录用户信息
        UserInfoSessionBean user = getUserInfoInSession(request);
        // 菜单树节点和其所有子节点信息
        UITreeNode outObj = menuNodeService.getMenuTreeNode_Service(inputObj.getId(), user.getUserId());

        JSONArray jSONArray = JSONArray.fromObject(outObj.getChildren());
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
