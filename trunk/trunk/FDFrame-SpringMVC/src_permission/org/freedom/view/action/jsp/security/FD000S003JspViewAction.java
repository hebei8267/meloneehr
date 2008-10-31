/*
 * Copyright 2008 by hebei, All rights reserved.
 */
package org.freedom.view.action.jsp.security;

import java.lang.reflect.InvocationTargetException;

import javax.servlet.http.HttpServletRequest;

import org.freedom.core.domain.UserInfoSessionBean;
import org.freedom.core.view.action.AbstractViewAction;
import org.freedom.services.ui.IMenuNodeService;
import org.freedom.view.vo.security.s003.FD000S003ViewObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 用户登录成功--工作区主界面JspViewAction
 * 
 * @author 何贝
 * @since JDK1.5
 */
@Controller
public class FD000S003JspViewAction extends AbstractViewAction {

    private static final long serialVersionUID = -642239425475299301L;

    @Autowired
    private IMenuNodeService menuNodeService;

    /**
     * 用户登录成功--工作区主界面
     * 
     * @param request
     * @param model
     * @return
     * @throws IllegalAccessException
     * @throws InvocationTargetException
     */
    @RequestMapping("/FD000S003JspViewAction_ShowPageAction.faces")
    public String showPageAction(HttpServletRequest request, Model model) throws IllegalAccessException,
            InvocationTargetException {

        FD000S003ViewObject outPutObj = new FD000S003ViewObject();
        // 取得登录用户信息
        UserInfoSessionBean user = getUserInfoInSession(request);
        // 导航区列表
        outPutObj.getShipAreaList().addAll(
                menuNodeService.getNavigationAreaMenuNode_Service(user.getUserId(), user.getRoleId()));

        model.addAttribute("FD000S003ViewObject", outPutObj);

        return "WEB-INF/jsp/security/FD000S003";
    }

    public IMenuNodeService getMenuNodeService() {
        return menuNodeService;
    }

    public void setMenuNodeService(IMenuNodeService menuNodeService) {
        this.menuNodeService = menuNodeService;
    }

}
