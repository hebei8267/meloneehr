/*
 * Copyright 2009 by hebei, All rights reserved.
 */
package org.freedom.view.action.vobj.security.menu.ms003;

import org.freedom.core.view.vo.jsp.AbstractViewObject;

/**
 * @author 何贝
 * @since JDK1.5
 */
public class MenuSetting003ViewObject extends AbstractViewObject {

    /**
     * 
     */
    private static final long serialVersionUID = 2747137438641441159L;
    /** 菜单节点编号 */
    private String nodeID;
    /** 菜单节点名称 */
    private String nodeTxt;

    /**
     * 取得菜单节点编号
     * 
     * @return 菜单节点编号
     */
    public String getNodeID() {
        return nodeID;
    }

    /**
     * 设置菜单节点编号
     * 
     * @param nodeID 菜单节点编号
     */
    public void setNodeID(String nodeID) {
        this.nodeID = nodeID;
    }

    /**
     * 取得菜单节点名称
     * 
     * @return 菜单节点名称
     */
    public String getNodeTxt() {
        return nodeTxt;
    }

    /**
     * 设置菜单节点名称
     * 
     * @param nodeTxt 菜单节点名称
     */
    public void setNodeTxt(String nodeTxt) {
        this.nodeTxt = nodeTxt;
    }

}
