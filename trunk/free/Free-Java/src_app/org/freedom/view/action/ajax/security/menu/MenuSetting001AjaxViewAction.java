/*
 * Copyright 2009 by hebei, All rights reserved.
 */
package org.freedom.view.action.ajax.security.menu;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.beanutils.BeanUtils;
import org.freedom.core.domain.TreeNode;
import org.freedom.core.view.action.AbstractViewAction;
import org.freedom.core.view.vo.ajax.DataListBean;
import org.freedom.core.view.vo.ajax.JosnViewObject;
import org.freedom.entity.common.Role;
import org.freedom.entity.ui.MenuNode;
import org.freedom.services.permit.IMenuNodePermitService;
import org.freedom.services.permit.IMenuNodeService;
import org.freedom.view.action.SecurityMesssageID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.ServletRequestUtils;
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
    @Autowired
    private IMenuNodePermitService menuNodePermitService;

    /**
     * 取得所有菜单结点树信息(根节点开始)不包含权限校验
     * 
     * @param request
     * @param response
     * @throws IOException
     */
    @RequestMapping("/security/menu/menuSetting/001/getAllMenuInfoTreeAction.ajax")
    public void getAllMenuInfoTreeAction(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // 取得所有菜单结点树信息(根节点开始)不包含权限校验
        TreeNode rootNode = menuNodeService.getMenuNodeInfoTreeService(MenuNode.MENU_NODE_TREE_ROOT_ID);

        JSONArray jSONArray = JSONArray.fromObject(rootNode.getChildren());
        response.setContentType(RESPONSE_CONTENT_TYPE);
        response.getWriter().write(jSONArray.toString());
    }

    /**
     * 取得菜单节点的角色信息
     * 
     * @param request
     * @param response
     * @throws ServletRequestBindingException
     * @throws IOException
     */
    @RequestMapping("/security/menu/menuSetting/001/getAccessMenuNodePermitRoleInfoListAction.ajax")
    public void getAccessMenuNodePermitRoleInfoListAction(HttpServletRequest request, HttpServletResponse response)
            throws ServletRequestBindingException, IOException {
        // 取得request里面的参数
        String menuNodeID = ServletRequestUtils.getStringParameter(request, "selectedMenuNodeID");
        // 取得可访问菜单节点的角色列表
        List<Role> roleList = menuNodePermitService.getAccessMenuNodePermitRoleInfoListService(menuNodeID);

        // Json对象格式化
        DataListBean<Role> dataList = new DataListBean<Role>();
        dataList.setDataList(roleList);
        JSONObject jSONObject = JSONObject.fromObject(dataList);
        response.setContentType(RESPONSE_CONTENT_TYPE);
        response.getWriter().write(jSONObject.toString());
    }

    /**
     * 删除菜单节点
     * 
     * @param request
     * @param response
     * @throws ServletRequestBindingException
     * @throws IOException
     */
    @RequestMapping("/security/menu/menuSetting/001/delMenuNodeInfoAction.ajax")
    public void delMenuNodeInfoAction(HttpServletRequest request, HttpServletResponse response)
            throws ServletRequestBindingException, IOException {
        // 取得request里面的参数
        String menuNodeID = ServletRequestUtils.getStringParameter(request, "nodeID");
        Integer dataVersion = ServletRequestUtils.getIntParameter(request, "dataVersion");

        int _result = menuNodeService.delMenuNodeInfoService(menuNodeID, dataVersion);
        JosnViewObject outObj = new JosnViewObject();
        if (_result != 0) {
            outObj.setProcessResult(false);

            outObj.setResultMsg(getMessage(request, SecurityMesssageID.ERROR_DATA_NO_SYNCHRONIZATION));

        }
        JSONObject jSONObject = JSONObject.fromObject(outObj);
        response.setContentType(RESPONSE_CONTENT_TYPE);
        response.getWriter().write(jSONObject.toString());
    }

    /**
     * 更新菜单节点
     * 
     * @param request
     * @param response
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     * @throws ServletRequestBindingException
     * @throws IOException
     */
    @RequestMapping("/security/menu/menuSetting/001/updateNodeInfoAction.ajax")
    public void updateNodeInfoAction(HttpServletRequest request, HttpServletResponse response) throws IllegalAccessException,
            InvocationTargetException, ServletRequestBindingException, IOException {
        MenuNode menuNode = new MenuNode();
        // 取得request里面的参数
        BeanUtils.populate(menuNode, request.getParameterMap());
        boolean applyArea = ServletRequestUtils.getBooleanParameter(request, "applyArea");
        String roleIDArray[] = ServletRequestUtils.getRequiredStringParameters(request, "roleIdList");
        List<String> roleIDList = Arrays.asList(roleIDArray);

        int _result = menuNodeService.modMenuNodeInfoService(menuNode, roleIDList, applyArea);
        JosnViewObject outObj = new JosnViewObject();
        if (_result != 0) {
            outObj.setProcessResult(false);

            outObj.setResultMsg(getMessage(request, SecurityMesssageID.ERROR_DATA_NO_SYNCHRONIZATION));

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
