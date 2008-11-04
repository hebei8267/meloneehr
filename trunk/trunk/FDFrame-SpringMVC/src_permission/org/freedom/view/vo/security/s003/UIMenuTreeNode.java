/*
 * Copyright 2008 by hebei, All rights reserved.
 */
package org.freedom.view.vo.security.s003;

import org.freedom.core.view.vo.ajax.UITreeNode;
import org.freedom.entity.ui.MenuNodeType;

/**
 * UI菜单树树节点
 * 
 * @author 何贝
 * @since JDK1.5
 */
public class UIMenuTreeNode extends UITreeNode {

    private static final long serialVersionUID = 7657513368317450150L;

    /** 页面迁移内容 */
    private String actionContent;

    /** 节点类型 */
    private String uiNodeType;

    /** 默认权限 "true"无访问限制 "false"有访问限制 */
    private Boolean defaultPermit = Boolean.TRUE;

    /** 节点显示位置 */
    private String uiNodeIndex;

    public UIMenuTreeNode() {

    }

    /**
     * 取得页面迁移内容
     * 
     * @return 页面迁移内容
     */
    public String getActionContent() {
        return actionContent;
    }

    /**
     * 设置页面迁移内容
     * 
     * @param actionContent 页面迁移内容
     */
    public void setActionContent(String actionContent) {
        this.actionContent = actionContent;
    }

    /**
     * 取得节点类型
     * 
     * @return 节点类型
     */
    public String getUiNodeType() {
        return uiNodeType;
    }

    /**
     * 设置节点类型
     * 
     * @param nodeType 节点类型
     */
    public void setUiNodeType(String nodeType) {
        if (MenuNodeType.AREA_NODE_TYPE.equals(nodeType)) {
            super.setIcon("images/area.gif");
        }
        this.uiNodeType = nodeType;
    }

    /**
     * 取得默认权限 "true"无访问限制 "false"有访问限制
     * 
     * @return 默认权限 "true"无访问限制 "false"有访问限制
     */
    public Boolean getDefaultPermit() {
        return defaultPermit;
    }

    /**
     * 设置默认权限 "true"无访问限制 "false"有访问限制
     * 
     * @param defaultPermit 默认权限 "true"无访问限制 "false"有访问限制
     */
    public void setDefaultPermit(Boolean defaultPermit) {
        this.defaultPermit = defaultPermit;
    }

    /**
     * 取得节点显示位置
     * 
     * @return 节点显示位置
     */
    public String getUiNodeIndex() {
        return uiNodeIndex;
    }

    /**
     * 设置节点显示位置
     * 
     * @param nodeIndex 节点显示位置
     */
    public void setUiNodeIndex(String nodeIndex) {
        this.uiNodeIndex = nodeIndex;
    }

}
