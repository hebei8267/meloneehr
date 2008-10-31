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

import org.freedom.core.domain.UserInfoSessionBean;
import org.freedom.core.view.action.AbstractViewAction;
import org.freedom.core.view.vo.ajax.JosnViewObject;
import org.freedom.core.view.vo.ajax.UITreeNode;
import org.freedom.services.ui.IMenuNodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.RequestMapping;

import static org.freedom.view.constant.MesssageIDSecurity.ERROR_NO_ACCESS_PERMIT;

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

        // 取得request里面的参数
        String nodeId = ServletRequestUtils.getStringParameter(request, "id");

        // 取得登录用户信息
        UserInfoSessionBean user = getUserInfoInSession(request);
        // 菜单树节点和其所有子节点信息
        UITreeNode outObj = menuNodeService.getMenuTreeNode_Service(nodeId, user.getUserId(), user
                .getRoleId());

        JSONArray jSONArray = JSONArray.fromObject(outObj.getChildren());
        response.setContentType(RESPONSE_CONTENT_TYPE);
        response.getWriter().write(jSONArray.toString());
    }

    /**
     * 检查用户访问菜单节点的权限
     * 
     * @param request
     * @param response
     * @throws ServletRequestBindingException
     * @throws IOException
     */
    @RequestMapping("/FD000S003AjaxViewAction_CheckMenuNodePermit.ajax")
    public void checkMenuNodePermit(HttpServletRequest request, HttpServletResponse response)
            throws ServletRequestBindingException, IOException {
        // 取得request里面的参数
        String nodeId = ServletRequestUtils.getStringParameter(request, "nodeId");
        // 取得登录用户信息
        UserInfoSessionBean user = getUserInfoInSession(request);

        boolean _result = menuNodeService.checkUserAccessMenuNodePermit_Service(user.getUserId(), user
                .getRoleId(), nodeId);

        JosnViewObject outObj = new JosnViewObject();
        if (!_result) {
            outObj.setProcessResult(false);
            outObj.setResultMsg(getMessage(request, ERROR_NO_ACCESS_PERMIT));
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

}
