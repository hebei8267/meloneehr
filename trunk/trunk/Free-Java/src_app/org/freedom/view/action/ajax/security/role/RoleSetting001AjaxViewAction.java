/*
 * Copyright 2009 by hebei, All rights reserved.
 */
package org.freedom.view.action.ajax.security.role;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.beanutils.BeanUtils;
import org.freedom.entity.common.Role;
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
 * 角色设定
 * 
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
    public void getAllRoleInfoTreeAction(HttpServletResponse response) throws ServletRequestBindingException, IOException,
            IllegalAccessException, InvocationTargetException {
        // 菜单树节点和其所有子节点信息
        TreeNode rootNode = roleService.getAllRoleInfoTreeService();

        JSONArray jSONArray = JSONArray.fromObject(rootNode.getChildren());
        response.setContentType(RESPONSE_CONTENT_TYPE);
        response.getWriter().write(jSONArray.toString());
    }

    @RequestMapping("/security/role/roleSetting/001/updateNodeInfoAction.ajax")
    public void updateNodeInfoAction(HttpServletRequest request, HttpServletResponse response) throws ServletRequestBindingException,
            IOException, IllegalAccessException, InvocationTargetException {

        Role role = new Role();

        // 取得request里面的参数
        BeanUtils.populate(role, request.getParameterMap());

        boolean _result = roleService.updateRoleInfoService(role);
        JosnViewObject outObj = new JosnViewObject();
        if (!_result) {
            outObj.setProcessResult(false);
            outObj.setResultMsg(getMessage(request, SecurityMesssageID.ERROR_DATA_NO_SYNCHRONIZATION));
        }
        JSONObject jSONObject = JSONObject.fromObject(outObj);
        response.setContentType(RESPONSE_CONTENT_TYPE);
        response.getWriter().write(jSONObject.toString());
    }

    @RequestMapping("/security/role/roleSetting/001/delNodeInfoAction.ajax")
    public void delNodeInfoAction(HttpServletRequest request, HttpServletResponse response) throws ServletRequestBindingException,
            IOException, IllegalAccessException, InvocationTargetException {
        // 取得request里面的参数
        String roleID = ServletRequestUtils.getStringParameter(request, "roleID");
        Integer dataVersion = ServletRequestUtils.getIntParameter(request, "dataVersion");

        int _result = roleService.delRoleInfoService(roleID, dataVersion);
        JosnViewObject outObj = new JosnViewObject();
        if (_result != 0) {
            outObj.setProcessResult(false);
            if (_result == 1) {
                outObj.setResultMsg(getMessage(request, SecurityMesssageID.ERROR_DATA_NO_SYNCHRONIZATION));
            }
            if (_result == 2) {
                outObj.setResultMsg(getMessage(request, SecurityMesssageID.ERROR_DATA_DEL_FAILURE_P2, new String[] { "角色节点",
                        "该角色节点及其所有子角色节点有关联的用户信息" }));
            }

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
}