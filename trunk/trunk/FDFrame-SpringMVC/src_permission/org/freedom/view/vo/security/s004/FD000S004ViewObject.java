/*
 * Copyright 2008 by hebei, All rights reserved.
 */
package org.freedom.view.vo.security.s004;

import org.freedom.core.view.vo.jsp.AbstractViewObject;

/**
 * 菜单树管理界面ViewObject
 * 
 * @author 何贝
 * @since JDK1.5
 */
public class FD000S004ViewObject extends AbstractViewObject {

    private static final long serialVersionUID = -3699212498804881787L;
    /** 节点编号 */
    private String nodeId;

    /** 节点内容 */
    private String nodeText;

    /** 节点类型 */
    private String nodeType = "未定义";

    /** 默认权限 "true"无访问限制 "false"有访问限制 */
    private Boolean defaultPermit = Boolean.TRUE;

    /** 角色适用范围 "true"仅该节点 "false"所有子节点 */
    private Boolean selfFlag = Boolean.TRUE;

    /** 节点显示位置 */
    private String nodeIndex;

    /** 页面迁移内容 */
    private String actionContent;

    public FD000S004ViewObject() {

    }

    /**
     * 取得节点编号
     * 
     * @return 节点编号
     */
    public String getNodeId() {
        return nodeId;
    }

    /**
     * 设置节点编号
     * 
     * @param nodeId 节点编号
     */
    public void setNodeId(String nodeId) {
        this.nodeId = nodeId;
    }

    /**
     * 取得节点内容
     * 
     * @return 节点内容
     */
    public String getNodeText() {
        return nodeText;
    }

    /**
     * 设置节点内容
     * 
     * @param nodeText 节点内容
     */
    public void setNodeText(String nodeText) {
        this.nodeText = nodeText;
    }

    /**
     * 取得节点类型
     * 
     * @return 节点类型
     */
    public String getNodeType() {
        return nodeType;
    }

    /**
     * 设置节点类型
     * 
     * @param nodeType 节点类型
     */
    public void setNodeType(String nodeType) {
        this.nodeType = nodeType;
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
     * 取得角色适用范围 "true"仅该节点 "false"所有子节点
     * 
     * @return 角色适用范围 "true"仅该节点 "false"所有子节点
     */
    public Boolean getSelfFlag() {
        return selfFlag;
    }

    /**
     * 设置角色适用范围 "true"仅该节点 "false"所有子节点
     * 
     * @param selfFlag 角色适用范围 "true"仅该节点 "false"所有子节点
     */
    public void setSelfFlag(Boolean selfFlag) {
        this.selfFlag = selfFlag;
    }

    /**
     * 取得节点显示位置
     * 
     * @return 节点显示位置
     */
    public String getNodeIndex() {
        return nodeIndex;
    }

    /**
     * 设置节点显示位置
     * 
     * @param nodeIndex 节点显示位置
     */
    public void setNodeIndex(String nodeIndex) {
        this.nodeIndex = nodeIndex;
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

}
