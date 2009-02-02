/*
 * Copyright 2009 by hebei, All rights reserved.
 */
package org.freedom.view.action.ajax.security.menu;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.freedom.core.view.action.AbstractViewAction;
import org.freedom.core.view.vo.ajax.DataListBean;
import org.freedom.entity.common.Role;
import org.freedom.services.permit.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 菜单树设定-添加角色
 * 
 * @author 何贝
 * @since JDK1.5
 */
@Controller
public class MenuSetting003AjaxViewAction extends AbstractViewAction {

    /**
     * 
     */
    private static final long serialVersionUID = -7333698705599891052L;
    @Autowired
    private IRoleService roleService;

    /**
     * 取得所有角色信息
     * 
     * @return
     * @throws IOException
     */
    @RequestMapping("/security/menu/menuSetting/003/getAllRoleInfoListAction.ajax")
    public void getAllRoleInfoListAction(HttpServletRequest request, HttpServletResponse response) throws IOException {
        List<Role> roleList = roleService.getAllRoleInfoListService();

        // Json对象格式化
        DataListBean<Role> dataList = new DataListBean<Role>();
        dataList.setDataList(roleList);

        JSONObject jSONObject = JSONObject.fromObject(dataList);
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
