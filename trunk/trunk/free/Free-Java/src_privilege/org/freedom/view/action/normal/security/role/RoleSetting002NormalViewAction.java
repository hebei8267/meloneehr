/*
 * Copyright 2009 by hebei, All rights reserved.
 */
package org.freedom.view.action.normal.security.role;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.freedom.core.view.action.AbstractViewAction;
import org.freedom.entity.common.Role;
import org.freedom.view.SysConstant;
import org.freedom.view.action.vobj.security.role.rs001.RoleSetting002ViewObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 添加角色
 * 
 * @author 何贝
 * @since JDK1.5
 */
@Controller
public class RoleSetting002NormalViewAction extends AbstractViewAction {

    /**
     * 
     */
    private static final long serialVersionUID = -2143895090220923972L;

    /**
     * 添加角色
     * 
     * @param request
     * @param model
     * @return
     * @throws ServletRequestBindingException
     */
    @RequestMapping("/security/role/roleSetting/002/showPageAction")
    public String showPageAction(HttpServletRequest request, Model model) throws ServletRequestBindingException {

        // 取得request里面的参数
        String pNodeID = ServletRequestUtils.getStringParameter(request, "hnodeID");
        String pNodeTxt = ServletRequestUtils.getStringParameter(request, "nodeTxt");

        RoleSetting002ViewObject vObj = new RoleSetting002ViewObject();
        if (StringUtils.isEmpty(pNodeID)) {
            vObj.setParentNodeID(Role.ROLE_TREE_ROOT_ID);
        } else {
            vObj.setParentNodeID(pNodeID);
        }
        if (StringUtils.isEmpty(pNodeTxt)) {
            vObj.setParentNodeTxt(SysConstant.ROLE_TREE_ROOT_NAME);
        } else {
            vObj.setParentNodeTxt(pNodeTxt);
        }

        model.addAttribute("RoleSetting002ViewObject", vObj);

        return "WEB-INF/jsp/security/role/roleSetting002";
    }
}
