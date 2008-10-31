/*
 * Copyright 2008 by hebei, All rights reserved.
 */
package org.freedom.view.action.ajax.security;

import static org.freedom.view.constant.MesssageIDSecurity.ERROR_NO_DATA_DELETE;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.freedom.core.view.action.AbstractViewAction;
import org.freedom.core.view.vo.ajax.DataListBean;
import org.freedom.core.view.vo.ajax.JosnViewObject;
import org.freedom.core.view.vo.ajax.UITreeNode;
import org.freedom.entity.security.Role;
import org.freedom.services.security.ISecurityService;
import org.freedom.services.ui.IMenuNodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 菜单树管理界面JspViewAction
 * 
 * @author 何贝
 * @since JDK1.5
 */
@Controller
public class FD000S004AjaxViewAction extends AbstractViewAction {

    private static final long serialVersionUID = -1019032505634538776L;
    @Autowired
    private IMenuNodeService menuNodeService;
    @Autowired
    private ISecurityService securityService;

    /**
     * 取得所有树节点数据
     * 
     * @param request
     * @param response
     * @throws ServletRequestBindingException
     * @throws IOException
     * @throws IllegalAccessException
     * @throws InvocationTargetException
     */
    @RequestMapping("/FD000S004AjaxViewAction_GetAllTreeNodeInfoAction.ajax")
    public void getAllTreeNodeInfoAction(HttpServletRequest request, HttpServletResponse response)
            throws ServletRequestBindingException, IOException, IllegalAccessException,
            InvocationTargetException {

        // 取得request里面的参数
        String nodeId = ServletRequestUtils.getStringParameter(request, "id");

        // 所有菜单树节点和其所有子节点信息
        UITreeNode uiMenuNode = menuNodeService.getAllMenuTreeNode_Service(nodeId);

        // Json对象格式化
        JSONArray jSONArray = JSONArray.fromObject(uiMenuNode.getChildren());
        response.setContentType(RESPONSE_CONTENT_TYPE);
        response.getWriter().write(jSONArray.toString());
    }

    /**
     * 取得菜单接的的角色信息
     * 
     * @param request
     * @param response
     * @throws ServletRequestBindingException
     * @throws IOException
     */
    @RequestMapping("/FD000S004AjaxViewAction_GetRoleInfoListAction.ajax")
    public void getRoleInfoListAction(HttpServletRequest request, HttpServletResponse response)
            throws ServletRequestBindingException, IOException {
        // 取得request里面的参数
        String menuNodeID = ServletRequestUtils.getStringParameter(request, "menuNodeID");
        // 取得可访问菜单节点的角色列表
        List<Role> roleList = menuNodeService.getRoleList_Service(menuNodeID);

        // Json对象格式化
        DataListBean<Role> dataList = new DataListBean<Role>();
        dataList.setDataList(roleList);
        JSONObject jSONObject = JSONObject.fromObject(dataList);
        response.setContentType(RESPONSE_CONTENT_TYPE);
        response.getWriter().write(jSONObject.toString());
    }

    /**
     * 删除菜单接的的角色信息
     * 
     * @param request
     * @param response
     * @throws ServletRequestBindingException
     * @throws IOException
     */
    @SuppressWarnings("unchecked")
    @RequestMapping("/FD000S004AjaxViewAction_DelSelectedRole.ajax")
    public void delSelectedRole(HttpServletRequest request, HttpServletResponse response)
            throws ServletRequestBindingException, IOException {
        // 取得request里面的参数
        String menuNodeID = ServletRequestUtils.getStringParameter(request, "menuNodeID");
        String roleListStr = ServletRequestUtils.getStringParameter(request, "roleList");

        List<Role> roleList = jsonStr2PojoList(roleListStr, Role.class);
        int _result = securityService.delRoleMenuNodePermit_Service(menuNodeID, roleList);

        JosnViewObject outObj = new JosnViewObject();
        if (_result == 0) {
            outObj.setProcessResult(false);
            outObj.setResultMsg(getMessage(request, ERROR_NO_DATA_DELETE));
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

    public ISecurityService getSecurityService() {
        return securityService;
    }

    public void setSecurityService(ISecurityService securityService) {
        this.securityService = securityService;
    }
}
