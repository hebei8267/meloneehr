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
    /**
     * 节点编号
     */
    private String id;
    /**
     * 节点内容
     */
    private String text;
    /**
     * 子节点标记
     */
    private boolean leaf = true;
    /**
     * 子节点
     */
    private List<UIMenuTreeNode> children = new ArrayList<UIMenuTreeNode>();

    public UIMenuTreeNode() {

    }

    public UIMenuTreeNode(String id, String text) {
        this.id = id;
        this.text = text;
    }

    public UIMenuTreeNode(String id, String text, boolean leaf) {
        this.id = id;
        this.text = text;
        this.leaf = leaf;
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

    public void addChildren(UIMenuTreeNode children) {
        this.children.add(children);
    }
}
