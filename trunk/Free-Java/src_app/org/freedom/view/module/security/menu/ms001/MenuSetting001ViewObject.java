/*
 * Copyright 2009 by hebei, All rights reserved.
 */
package org.freedom.view.module.security.menu.ms001;

import org.freedom.sys.view.AbstractViewObject;

/**
 * 菜单树设定
 * 
 * @author 何贝
 * @since JDK1.5
 */
public class MenuSetting001ViewObject extends AbstractViewObject {

    /**
     * 
     */
    private static final long serialVersionUID = 700997080695294169L;
    /** 编号 */
    private String nodeID;
    /** 类型ID */
    private String nodeTypeID;
    /** 类型 */
    private String nodeType;
    /** 名称 */
    private String nodeTxt;
    /** 访问限制 true-有限制 false-无限制 */
    private boolean defaultPermit = false;
    /** Action URL */
    private String actionContent;
    /** 角色适用范围 true-仅该节点 false-包含所有子节点 */
    private boolean applyArea = true;
    /** 显示位置 */
    private Integer showIndex;

    /**
     * 取得编号
     * 
     * @return 编号
     */
    public String getNodeID() {
        return nodeID;
    }

    /**
     * 设置编号
     * 
     * @param nodeID 编号
     */
    public void setNodeID(String nodeID) {
        this.nodeID = nodeID;
    }

    /**
     * 取得类型ID
     * 
     * @return 类型ID
     */
    public String getNodeTypeID() {
        return nodeTypeID;
    }

    /**
     * 设置类型ID
     * 
     * @param nodeTypeID 类型ID
     */
    public void setNodeTypeID(String nodeTypeID) {
        this.nodeTypeID = nodeTypeID;
    }

    /**
     * 取得类型
     * 
     * @return 类型
     */
    public String getNodeType() {
        return nodeType;
    }

    /**
     * 设置类型
     * 
     * @param nodeType 类型
     */
    public void setNodeType(String nodeType) {
        this.nodeType = nodeType;
    }

    /**
     * 取得名称
     * 
     * @return 名称
     */
    public String getNodeTxt() {
        return nodeTxt;
    }

    /**
     * 设置名称
     * 
     * @param nodeTxt 名称
     */
    public void setNodeTxt(String nodeTxt) {
        this.nodeTxt = nodeTxt;
    }

    /**
     * 取得访问限制
     * 
     * @return 访问限制
     */
    public boolean getDefaultPermit() {
        return defaultPermit;
    }

    /**
     * 设置访问限制
     * 
     * @param defaultPermit 访问限制
     */
    public void setDefaultPermit(boolean defaultPermit) {
        this.defaultPermit = defaultPermit;
    }

    /**
     * 取得Action URL
     * 
     * @return Action URL
     */
    public String getActionContent() {
        return actionContent;
    }

    /**
     * 设置Action URL
     * 
     * @param actionContent Action URL
     */
    public void setActionContent(String actionContent) {
        this.actionContent = actionContent;
    }

    /**
     * 取得角色适用范围 true-仅该节点 false-包含所有子节点
     * 
     * @return 角色适用范围 true-仅该节点 false-包含所有子节点
     */
    public boolean getApplyArea() {
        return applyArea;
    }

    /**
     * 设置角色适用范围 true-仅该节点 false-包含所有子节点
     * 
     * @param applyArea 角色适用范围 true-仅该节点 false-包含所有子节点
     */
    public void setApplyArea(boolean applyArea) {
        this.applyArea = applyArea;
    }

    /**
     * 取得显示位置
     * 
     * @return 显示位置
     */
    public Integer getShowIndex() {
        return showIndex;
    }

    /**
     * 设置显示位置
     * 
     * @param showIndex 显示位置
     */
    public void setShowIndex(Integer showIndex) {
        this.showIndex = showIndex;
    }

}
