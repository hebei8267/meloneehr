/*
 * Copyright 2008 by hebei, All rights reserved.
 */
package org.freedom.view.system.utils;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

/**
 * jsp里面直接操作WebApplicationContext
 * 
 * @author 何贝
 * @since JDK1.5
 */
public class JspApplicationContext {
    /**
     * 取得ApplicationContext
     * 
     * @param request HttpServletRequest
     * @return
     */
    public static WebApplicationContext getApplicationContext(HttpServletRequest request) {
        ServletContext servletContext = request.getSession().getServletContext();
        return WebApplicationContextUtils.getRequiredWebApplicationContext(servletContext);
    }

    /**
     * 取得ApplicationContext里面的指定对象
     * 
     * @param request HttpServletRequest
     * @param beanName
     * @return
     */
    public static Object getApplicationBean(HttpServletRequest request, String beanName) {
        WebApplicationContext wac = getApplicationContext(request);

        return wac.getBean(beanName);
    }
}
