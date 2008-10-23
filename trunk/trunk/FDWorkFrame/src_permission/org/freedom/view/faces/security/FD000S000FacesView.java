/*
 * Copyright 2008 by hebei, All rights reserved.
 */
package org.freedom.view.faces.security;

import org.freedom.core.view.AbstractViewBean;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * 默认画面迁移到用户登录页面FacesView
 * 
 * @author 何贝
 * @since JDK1.5
 */
@Component("FD000S000FacesView")
@Scope("request")
public class FD000S000FacesView extends AbstractViewBean {

    private static final long serialVersionUID = 6066674675465360677L;

    public String defaultTransplantAction() {
        return "success";
    }
}
