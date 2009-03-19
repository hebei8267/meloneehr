/*
 * Copyright 2009 by hebei, All rights reserved.
 */
package org.freedom.view.action.ajax.security.role;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.commons.beanutils.BeanUtils;
import org.freedom.entity.common.Role;
import org.freedom.services.permit.IRoleService;
import org.freedom.sys.view.JosnViewObject;
import org.freedom.view.SecurityMesssageID;
import org.freedom.view.action.AbstractViewAction;
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
public class RoleSetting002AjaxViewAction extends AbstractViewAction {
    /**
     * 
     */
    private static final long serialVersionUID = 7145941877107583325L;
    @Autowired
    private IRoleService roleService;

    @RequestMapping("/security/role/roleSetting/002/addRoleAction")
    public void addRoleAction(HttpServletRequest request, HttpServletResponse response) throws ServletRequestBindingException,
            IllegalAccessException, InvocationTargetException, IOException {
        // 取得request里面的参数
        boolean inheritFlg = ServletRequestUtils.getBooleanParameter(request, "inheritFlg");
        Role role = new Role();
        BeanUtils.populate(role, request.getParameterMap());

        boolean _result = roleService.addRoleInfoService(role, inheritFlg);
        JosnViewObject outObj = new JosnViewObject();
        if (!_result) {
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
}
