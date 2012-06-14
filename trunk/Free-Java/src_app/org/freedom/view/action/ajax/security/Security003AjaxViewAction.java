/*
 * Copyright 2008 by hebei, All rights reserved.
 */
package org.freedom.view.action.ajax.security;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.freedom.services.permit.IMenuNodePermitService;
import org.freedom.services.permit.IMenuNodeService;
import org.freedom.sys.modules.TreeNode;
import org.freedom.sys.modules.UserInfoSessionBean;
import org.freedom.sys.view.JosnViewObject;
import org.freedom.view.SecurityMesssageID;
import org.freedom.view.action.AbstractViewAction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 主框架
 * 
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
    @Autowired
    private IMenuNodePermitService menuNodePermitService;

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
    public void getAreaMenuNodeTreeAction(HttpServletRequest request, HttpServletResponse response) throws ServletRequestBindingException,
            IOException, IllegalAccessException, InvocationTargetException {

        // 取得request里面的参数
        String nodeId = ServletRequestUtils.getStringParameter(request, "nodeId");

        // 取得登录用户信息
        UserInfoSessionBean user = getUserInfoInSession(request);
        // 菜单树节点和其所有子节点信息
        TreeNode rootNode = menuNodeService.getMenuNodeInfoTreeService(nodeId, user.getUserId(), user.getRoleId());

        JSONArray jSONArray = JSONArray.fromObject(rootNode.getChildren());
        response.setContentType(RESPONSE_CONTENT_TYPE);
        response.getWriter().write(jSONArray.toString());
    }

    /**
     * 校验选中节点的访问权限
     * 
     * @param request
     * @param response
     * @throws ServletRequestBindingException
     * @throws IOException
     * @throws IllegalAccessException
     * @throws InvocationTargetException
     */
    @RequestMapping("/security/003/checkMenuNodePermitAction.ajax")
    public void checkMenuNodePermitAction(HttpServletRequest request, HttpServletResponse response) throws ServletRequestBindingException,
            IOException, IllegalAccessException, InvocationTargetException {
        // 取得request里面的参数
        String nodeId = ServletRequestUtils.getStringParameter(request, "nodeId");
        // 取得登录用户信息
        UserInfoSessionBean user = getUserInfoInSession(request);

        boolean _result = menuNodePermitService.checkMenuNodePermitService(user.getRoleId(), nodeId);

        JosnViewObject outObj = new JosnViewObject();
        if (!_result) {
            outObj.setProcessResult(false);
            outObj.setResultMsg(getMessage(request, SecurityMesssageID.ERROR_NO_ACCESS_MENU_NODE_PERMIT));
        }
        JSONObject jSONObject = JSONObject.fromObject(outObj);
        response.setContentType(RESPONSE_CONTENT_TYPE);
        response.getWriter().write(jSONObject.toString());
    }

    public IMenuNodeService getMenuNodeService() {
        return menuNodeService;
    }

    public void setMenuNodeService(IMenuNodeService menuNodeService) {
        this.menuNodeService = menuNodeService;
    }

    public IMenuNodePermitService getMenuNodePermitService() {
        return menuNodePermitService;
    }

    public void setMenuNodePermitService(IMenuNodePermitService menuNodePermitService) {
        this.menuNodePermitService = menuNodePermitService;
    }
}
