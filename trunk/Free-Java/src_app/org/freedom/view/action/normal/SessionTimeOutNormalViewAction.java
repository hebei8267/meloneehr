/*
 * Copyright 2008 by hebei, All rights reserved.
 */
package org.freedom.view.action.normal;

import org.freedom.view.action.AbstractViewAction;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author 何贝
 * @since JDK1.5
 */
@Controller
public class SessionTimeOutNormalViewAction extends AbstractViewAction {

    /**
     * 
     */
    private static final long serialVersionUID = -8548544051651876316L;

    @RequestMapping("/sessionTimeOut.faces")
    public String showPageAction() {
        return "/sessionTimeOut";
    }
}
