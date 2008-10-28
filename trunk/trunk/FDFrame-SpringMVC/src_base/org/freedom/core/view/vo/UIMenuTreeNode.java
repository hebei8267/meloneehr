/*
 * Copyright 2008 by hebei, All rights reserved.
 */
package org.freedom.core.view.vo;

import java.util.ArrayList;
import java.util.List;

import org.freedom.core.bean.BaseBean;

/**
 * UI树节点
 * 
 * @author 何贝
 * @since JDK1.5
 */
public class UIMenuTreeNode extends BaseBean {

    private static final long serialVersionUID = 6909488869312107426L;
    /** 节点编号 */
    private String id;
    /** 节点内容 */
    private String text;
    /** 子节点标记 */
    private boolean leaf = true;
    /** 页面迁移内容 */
    private String actionContent;
    /** 子节点 */
    private List<UIMenuTreeNode> children = new ArrayList<UIMenuTreeNode>();
    /** 节点类型 */
    private String treeNodeType;

    /** 默认权限 "true"无访问限制 "false"有访问限制 */
    private Boolean defaultPermit = Boolean.TRUE;

    /** 父节点ID */
    private String parentNodeID;

    /** 节点显示位置 */
    private String treeNodeIndex;

    public UIMenuTreeNode() {

    }

    public UIMenuTreeNode(String id, String text, String actionContent) {
        this.id = id;
        this.text = text;
        this.actionContent = actionContent;
    }

    public UIMenuTreeNode(String id, String text, boolean leaf, String actionContent) {
        this.id = id;
        this.text = text;
        this.leaf = leaf;
        this.actionContent = actionContent;
    }

    /**
     * 取得节点编号
     * 
     * @return 节点编号
     */
    public String getId() {
        return id;
    }

    /**
     * 设置节点编号
     * 
     * @param id 节点编号
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * 取得节点内容
     * 
     * @return 节点内容
     */
    public String getText() {
        return text;
    }

    /**
     * 设置节点内容
     * 
     * @param text 节点内容
     */
    public void setText(String text) {
        this.text = text;
    }

    /**
     * 取得子节点标记
     * 
     * @return 子节点标记
     */
    public boolean getLeaf() {
        return leaf;
    }

    /**
     * 设置子节点标记
     * 
     * @param leaf 子节点标记
     */
    public void setLeaf(boolean leaf) {
        this.leaf = leaf;
    }

    /**
     * 取得子节点
     * 
     * @return 子节点
     */
    public List<UIMenuTreeNode> getChildren() {
        return children;
    }

    /**
     * 设置子节点
     * 
     * @param children 子节点
     */
    public void setChildren(List<UIMenuTreeNode> children) {
        this.children = children;
    }

    public void addChildren(UIMenuTreeNode uiNode) {
        this.children.add(uiNode);
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
    public String getTreeNodeType() {
        return treeNodeType;
    }

    /**
     * 设置节点类型
     * 
     * @param treeNodeType 节点类型
     */
    public void setTreeNodeType(String treeNodeType) {
        this.treeNodeType = treeNodeType;
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
     * 取得父节点ID
     * 
     * @return 父节点ID
     */
    public String getParentNodeID() {
        return parentNodeID;
    }

    /**
     * 设置父节点ID
     * 
     * @param parentNodeID 父节点ID
     */
    public void setParentNodeID(String parentNodeID) {
        this.parentNodeID = parentNodeID;
    }

    /**
     * 取得节点显示位置
     * 
     * @return 节点显示位置
     */
    public String getTreeNodeIndex() {
        return treeNodeIndex;
    }

    /**
     * 设置节点显示位置
     * 
     * @param treeNodeIndex 节点显示位置
     */
    public void setTreeNodeIndex(String treeNodeIndex) {
        this.treeNodeIndex = treeNodeIndex;
    }
}
