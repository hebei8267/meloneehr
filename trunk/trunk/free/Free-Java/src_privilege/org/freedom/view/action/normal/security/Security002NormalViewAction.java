/*
 * Copyright 2008 by hebei, All rights reserved.
 */
package org.freedom.view.action.normal.security;

import javax.servlet.http.HttpServletRequest;

import org.freedom.core.view.action.AbstractViewAction;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author 何贝
 * @since JDK1.5
 */
@Controller
public class Security002NormalViewAction extends AbstractViewAction {

    /**
     * 
     */
    private static final long serialVersionUID = 3462436435609245122L;
    @RequestMapping("/security/002/showPageAction")
    public String showPageAction(HttpServletRequest request, Model model){
        return "WEB-INF/jsp/security/security002";
    }
}
