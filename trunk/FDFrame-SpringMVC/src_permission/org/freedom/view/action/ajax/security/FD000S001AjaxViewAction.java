/*
 * Copyright 2008 by hebei, All rights reserved.
 */
package org.freedom.view.action.ajax.security;

import java.io.IOException;
import java.io.Writer;
import java.lang.reflect.InvocationTargetException;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.beanutils.BeanUtils;
import org.freedom.core.view.action.AbstractViewAction;
import org.freedom.entity.security.User;
import org.freedom.services.security.ISecurityService;
import org.freedom.view.vo.security.FD000S001ViewObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 用户登录AjaxView
 * 
 * @author 何贝
 * @since JDK1.5
 */
@Controller
public class FD000S001AjaxViewAction extends AbstractViewAction {

    private static final long serialVersionUID = -6277601235716651095L;

    private ISecurityService securityService;

    @RequestMapping("/loginAction.ajax")
    public void loginAction(HttpServletRequest request, Model model, Writer writer)
            throws ServletRequestBindingException, IOException, IllegalAccessException, InvocationTargetException {
        FD000S001ViewObject vObj = new FD000S001ViewObject();
        // 取得request里面的参数
        BeanUtils.populate(vObj, request.getParameterMap());

        System.out.println(vObj);
        User user = securityService.userLogin_Service(vObj.getUserId(), vObj.getPassword());
        System.out.println(user);
        writer.write("123");
    }

    public ISecurityService getSecurityService() {
        return securityService;
    }

    public void setSecurityService(ISecurityService securityService) {
        this.securityService = securityService;
    }

}
