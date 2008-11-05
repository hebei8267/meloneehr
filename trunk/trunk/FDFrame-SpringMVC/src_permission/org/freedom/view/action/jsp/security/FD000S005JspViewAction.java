/*
 * Copyright 2008 by hebei, All rights reserved.
 */
package org.freedom.view.action.jsp.security;

import org.freedom.core.view.action.AbstractViewAction;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 所有角色显示界面JspViewAction
 * 
 * @author 何贝
 * @since JDK1.5
 */
@Controller
public class FD000S005JspViewAction extends AbstractViewAction {

    private static final long serialVersionUID = 854414729899737810L;

    /**
     * 显示界面
     * 
     * @return
     */
    @RequestMapping("/FD000S005JspViewAction_ShowPageAction.faces")
    public String showPage_Action() {
        return "WEB-INF/jsp/security/FD000S005";
    }

}
