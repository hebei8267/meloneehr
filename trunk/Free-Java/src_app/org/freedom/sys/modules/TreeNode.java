/*
 * Copyright 2008 by hebei, All rights reserved.
 */
package org.freedom.sys.modules;

import java.util.ArrayList;
import java.util.List;

import org.freedom.core.bean.BaseBean;

/**
 * 树节点
 * 
 * @author 何贝
 * @since JDK1.5
 */
public class TreeNode extends BaseBean {

    private static final long serialVersionUID = 6909488869312107426L;

    /** 节点编号 */
    private String id;

    /** 节点内容 */
    private String text;

    /** 子节点标记 */
    private boolean leaf = true;

    /** 父节点ID */
    private String parentNodeID;

    /** 子节点 */
    private List<TreeNode> children = new ArrayList<TreeNode>();

    /** 节点图标 */
    private String icon;

    /** 数据版本号 */
    protected Integer version;

    public TreeNode() {

    }

    public TreeNode(String id, String text) {
        this.id = id;
        this.text = text;
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
     * 取得子节点
     * 
     * @return 子节点
     */
    public List<TreeNode> getChildren() {
        return children;
    }

    /**
     * 设置子节点
     * 
     * @param children 子节点
     */
    public void setChildren(List<TreeNode> children) {
        this.children = children;
    }

    /**
     * 添加子节点
     * 
     * @param node 子节点
     */
    public void addChildren(TreeNode node) {
        this.children.add(node);
    }

    /**
     * 取得节点图标
     * 
     * @return 节点图标
     */
    public String getIcon() {
        return icon;
    }

    /**
     * 设置节点图标
     * 
     * @param icon 节点图标
     */
    public void setIcon(String icon) {
        this.icon = icon;
    }

    /**
     * 取得数据版本号
     * 
     * @return 数据版本号
     */
    public Integer getVersion() {
        return version;
    }

    /**
     * 设置数据版本号
     * 
     * @param version 数据版本号
     */
    public void setVersion(Integer version) {
        this.version = version;
    }
}
