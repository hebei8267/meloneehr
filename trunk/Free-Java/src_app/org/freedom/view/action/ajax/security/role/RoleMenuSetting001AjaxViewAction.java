/*
 * Copyright 2009 by hebei, All rights reserved.
 */
package org.freedom.view.action.ajax.security.role;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.freedom.entity.ui.MenuNode;
import org.freedom.services.permit.IMenuNodePermitService;
import org.freedom.services.permit.IMenuNodeService;
import org.freedom.services.permit.IRoleService;
import org.freedom.sys.modules.TreeNode;
import org.freedom.sys.view.JosnViewObject;
import org.freedom.view.SecurityMesssageID;
import org.freedom.view.action.AbstractViewAction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.ServletRequestUtils;
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
    @Autowired
    private IMenuNodePermitService menuNodePermitService;

    @RequestMapping("/security/role/roleMenuSetting/001/getAllRoleInfoTreeAction.ajax")
    public void getAllRoleInfoTreeAction(HttpServletResponse response) throws ServletRequestBindingException, IOException,
            IllegalAccessException, InvocationTargetException {
        // 菜单树节点和其所有子节点信息
        TreeNode rootNode = roleService.getAllRoleInfoTreeService();

        JSONArray jSONArray = JSONArray.fromObject(rootNode.getChildren());
        response.setContentType(RESPONSE_CONTENT_TYPE);
        response.getWriter().write(jSONArray.toString());
    }

    @RequestMapping("/security/role/roleMenuSetting/001/getAllMenuInfoTreeAction.ajax")
    public void getAllMenuInfoTreeAction(HttpServletRequest request, HttpServletResponse response) throws IOException,
            ServletRequestBindingException {
        // 取得request里面的参数
        String roleID = ServletRequestUtils.getStringParameter(request, "roleId");

        JSONArray jSONArray = null;
        if (StringUtils.isNotEmpty(roleID)) {
            // 取得菜单结点树信息(指定根节点开始)包含权限校验
            TreeNode rootNode = menuNodeService.getMenuNodeInfoTreeEmbodyPermitService(MenuNode.MENU_NODE_TREE_ROOT_ID, roleID);
            jSONArray = JSONArray.fromObject(rootNode.getChildren());
        } else {// 角色id为空时，返回空菜单序列
            jSONArray = new JSONArray();
        }

        response.setContentType(RESPONSE_CONTENT_TYPE);
        response.getWriter().write(jSONArray.toString());
    }

    @RequestMapping("/security/role/roleMenuSetting/001/updateMenuNodePermitInfoAction.ajax")
    public void updateMenuNodePermitInfoAction(HttpServletRequest request, HttpServletResponse response)
            throws ServletRequestBindingException, IOException {
        // 取得request里面的参数
        String roleID = ServletRequestUtils.getStringParameter(request, "roleId");
        Integer dataVersion = ServletRequestUtils.getIntParameter(request, "dataVersion");
        String menuIDArray[] = ServletRequestUtils.getRequiredStringParameters(request, "menuIDList");
        List<String> menuIDList = Arrays.asList(menuIDArray);

        // 更新菜单树结点的可访问角色列表
        int _result = menuNodePermitService.updateMenuNodePermitService(roleID, dataVersion, menuIDList);
        JosnViewObject outObj = new JosnViewObject();
        if (_result != 0) {
            outObj.setProcessResult(false);

            outObj.setResultMsg(getMessage(request, SecurityMesssageID.ERROR_DATA_NO_SYNCHRONIZATION));

        }
        JSONObject jSONObject = JSONObject.fromObject(outObj);
        response.setContentType(RESPONSE_CONTENT_TYPE);
        response.getWriter().write(jSONObject.toString());
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

    public IMenuNodePermitService getMenuNodePermitService() {
        return menuNodePermitService;
    }

    public void setMenuNodePermitService(IMenuNodePermitService menuNodePermitService) {
        this.menuNodePermitService = menuNodePermitService;
    }
}
