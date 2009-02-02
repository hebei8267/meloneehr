/*
 * Copyright 2009 by hebei, All rights reserved.
 */
package org.freedom.view.action.normal.security.menu;

import java.lang.reflect.InvocationTargetException;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.beanutils.BeanUtils;
import org.freedom.core.view.action.AbstractViewAction;
import org.freedom.view.action.vobj.security.menu.ms003.MenuSetting003ViewObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 菜单树设定-添加角色
 * 
 * @author 何贝
 * @since JDK1.5
 */
@Controller
public class MenuSetting003NormalViewAction extends AbstractViewAction {

    /**
     * 
     */
    private static final long serialVersionUID = 8901465266436304085L;

    /**
     * 菜单树设定-添加角色
     * 
     * @param request
     * @param model
     * @return
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     */
    @RequestMapping("/security/menu/menuSetting/003/showPageAction")
    public String showPageAction(HttpServletRequest request, Model model) throws IllegalAccessException,
            InvocationTargetException {
        MenuSetting003ViewObject vObj = new MenuSetting003ViewObject();

        // 取得request里面的参数
        BeanUtils.populate(vObj, request.getParameterMap());

        model.addAttribute("MenuSetting003ViewObject", vObj);

        return "WEB-INF/jsp/security/menu/menuSetting003";
    }
}
