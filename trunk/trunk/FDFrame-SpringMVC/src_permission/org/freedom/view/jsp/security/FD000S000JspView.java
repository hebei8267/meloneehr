/*
 * Copyright 2008 by hebei, All rights reserved.
 */
package org.freedom.view.jsp.security;

import org.freedom.core.view.AbstractViewBean;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 默认画面迁移到用户登录页面JspView
 * 
 * @author 何贝
 * @since JDK1.5
 */
@Controller
public class FD000S000JspView extends AbstractViewBean {

    private static final long serialVersionUID = 6066674675465360677L;

    @RequestMapping("/index.faces")
    public String defaultTransplantAction() {
        System.out.println("1111111111111111");
        return "WEB-INF/jsp/security/FD000S001";
    }
}
