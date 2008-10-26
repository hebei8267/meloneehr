/*
 * Copyright 2008 by hebei, All rights reserved.
 */
package org.freedom.core.view.vo;

import org.freedom.core.bean.BaseBean;

/**
 * 公共JosnViewObject对象基类
 * 
 * @author 何贝
 * @since JDK1.5
 */
public class JosnViewObject extends BaseBean {

    private static final long serialVersionUID = 1490292841454499665L;
    /** Session超时 */
    private Boolean sessionTimeOut = Boolean.FALSE;
    /** 处理结果 */
    private Boolean processResult = Boolean.TRUE;
    /** 处理结果消息 */
    private String resultMsg = "";

    public JosnViewObject() {

    }

    public JosnViewObject(Boolean processResult) {
        this.processResult = processResult;
    }

    public JosnViewObject(Boolean processResult, Boolean sessionTimeOut) {
        this.processResult = processResult;
        this.sessionTimeOut = sessionTimeOut;
    }

    /**
     * 取得Session超时
     * 
     * @return Session超时
     */
    public Boolean getSessionTimeOut() {
        return sessionTimeOut;
    }

    /**
     * 设置Session超时
     * 
     * @param sessionTimeOut Session超时
     */
    public void setSessionTimeOut(Boolean sessionTimeOut) {
        this.sessionTimeOut = sessionTimeOut;
    }

    /**
     * 取得处理结果
     * 
     * @return 处理结果
     */
    public Boolean getProcessResult() {
        return processResult;
    }

    /**
     * 设置处理结果
     * 
     * @param processResult 处理结果
     */
    public void setProcessResult(Boolean processResult) {
        this.processResult = processResult;
    }

    /**
     * 取得处理结果消息
     * 
     * @return 处理结果消息
     */
    public String getResultMsg() {
        return resultMsg;
    }

    /**
     * 设置处理结果消息
     * 
     * @param resultMsg 处理结果消息
     */
    public void setResultMsg(String resultMsg) {
        this.resultMsg = resultMsg;
    }
}
