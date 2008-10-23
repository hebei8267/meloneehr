/*
 * Copyright 2008 by hebei, All rights reserved.
 */
package org.freedom.core.view;

import java.util.Locale;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.freedom.core.bean.BaseBean;

/**
 * 公共View对象基类
 * 
 * @author 何贝
 * @since JDK1.5
 */
public abstract class AbstractViewBean extends BaseBean {
    private final String USER_INFO = "userInfo";

    public AbstractViewBean() {

    }

    /** Bean生命周期中初始化方法 */
    @PostConstruct
    protected void _create() {
        create();
    }

    /** Bean生命周期中初始化方法 */
    public void create() {

    }

    /** Bean生命周期中销毁方法 */
    @PreDestroy
    protected void _destroy() {
        destroy();
    }

    /** Bean生命周期中销毁方法 */
    public void destroy() {

    }

    /**
     * 默认初始化方法
     */
    public void init() {

    }

    /**
     * 添加错误消息
     * 
     * @deprecated
     * @param msgKey 消息内容Key
     */
    protected void addErrorMessage(String msgKey) {
        String msgStr = getMessage(msgKey);
        // TODO hebei no imple
    }

    /**
     * 取得消息资源
     * 
     * @deprecated
     * @param msgKey 消息Key
     * @return 消息
     */
    protected String getMessage(String msgKey) {
        return getMessage(msgKey, null, Locale.CHINA);
    }

    /**
     * 取得消息资源
     * 
     * @deprecated
     * @param msgKey 消息Key
     * @param args 消息Param
     * @return 消息
     */
    protected String getMessage(String msgKey, Object[] args) {
        return getMessage(msgKey, args, Locale.CHINA);
    }

    /**
     * 取得消息资源
     * 
     * @deprecated
     * @param msgKey 消息Key
     * @param args 消息Param
     * @param locale Locale
     * @return
     */
    protected String getMessage(String msgKey, Object[] args, Locale locale) {
        // TODO hebei no imple
        return "";
    }

}
