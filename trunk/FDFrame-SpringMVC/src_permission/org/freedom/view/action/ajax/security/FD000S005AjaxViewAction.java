/*
 * Copyright 2008 by hebei, All rights reserved.
 */
package org.freedom.view.action.ajax.security;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.freedom.core.view.action.AbstractViewAction;
import org.freedom.core.view.vo.ajax.DataListBean;
import org.freedom.entity.security.Role;
import org.freedom.services.security.ISecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 所有角色显示界面AjaxViewAction
 * 
 * @author 何贝
 * @since JDK1.5
 */
@Controller
public class FD000S005AjaxViewAction extends AbstractViewAction {

    private static final long serialVersionUID = -9165295575974842074L;
    @Autowired
    private ISecurityService securityService;

    /**
     * 角色显示界面
     * 
     * @return
     */
    @RequestMapping("/FD000S005JspViewAction_GetAllRoleInfoListAction.ajax")
    public void getAllRoleInfoList_Action(HttpServletRequest request, HttpServletResponse response)
            throws ServletRequestBindingException, IOException, IllegalAccessException,
            InvocationTargetException {
        List<Role> roleList = securityService.getAllRoleInfoList_Service();

        // Json对象格式化
        DataListBean<Role> dataList = new DataListBean<Role>();
        dataList.setDataList(roleList);

        JSONObject jSONObject = JSONObject.fromObject(dataList);
        response.setContentType(RESPONSE_CONTENT_TYPE);
        response.getWriter().write(jSONObject.toString());
    }

    public ISecurityService getSecurityService() {
        return securityService;
    }

    public void setSecurityService(ISecurityService securityService) {
        this.securityService = securityService;
    }
}
