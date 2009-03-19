/*
 * Copyright 2008 by hebei, All rights reserved.
 */
package org.freedom.sys.view;

import java.util.ArrayList;
import java.util.List;

import org.freedom.core.bean.BaseBean;

/**
 * 公共ViewObject对象基类
 * 
 * @author 何贝
 * @since JDK1.5
 */
public abstract class AbstractViewObject extends BaseBean {

    private static final long serialVersionUID = -5895556101518158703L;
    /**
     * 错误消息List
     */
    private List<String> msgList = new ArrayList<String>();

    /**
     * 添加错误消息
     * 
     * @param msg 消息内容
     */
    public void addErrorMessage(String msg) {
        msgList.add(msg);
    }

    /**
     * 取得错误消息List
     * 
     * @return 错误消息List
     */
    public List<String> getMsgList() {
        return msgList;
    }

    /**
     * 设置错误消息List
     * 
     * @param msgList 错误消息List
     */
    public void setMsgList(List<String> msgList) {
        this.msgList = msgList;
    }

}
