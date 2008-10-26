/*
 * Copyright 2008 by hebei, All rights reserved.
 */
package org.freedom.view.action.jsp.security;

import java.lang.reflect.InvocationTargetException;

import javax.servlet.http.HttpServletRequest;

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
public class FD000S002JspViewAction {
    /**
     * 用户登录成功--工作区主界面
     * 
     * @param request
     * @param model
     * @return
     * @throws IllegalAccessException
     * @throws InvocationTargetException
     */
    @RequestMapping("/FD000S002JspViewAction_GoHomeAction.faces")
    public String goHomeAction(HttpServletRequest request, Model model) throws IllegalAccessException,
            InvocationTargetException {
        return "WEB-INF/jsp/security/FD000S003";
    }
}
