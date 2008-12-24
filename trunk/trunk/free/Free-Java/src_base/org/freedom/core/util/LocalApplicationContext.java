/*
 * Copyright 2008 by hebei, All rights reserved.
 */
package org.freedom.core.util;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 开发时LocalApplicationContext
 * 
 * @author 何贝
 * @since JDK1.5
 */
public class LocalApplicationContext {
    private static ClassPathXmlApplicationContext appContext = null;

    static {
        appContext = new ClassPathXmlApplicationContext(new String[] { "applicationContext.xml" });
    }

    public static ClassPathXmlApplicationContext getAppContext() {
        return appContext;
    }
}