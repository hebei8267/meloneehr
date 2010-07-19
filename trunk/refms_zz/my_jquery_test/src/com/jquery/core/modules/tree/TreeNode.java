package com.jquery.core.modules.tree;

import java.util.ArrayList;
import java.util.List;


/**
 * 树节点
 * 
 * @since JDK1.5
 */
public class TreeNode {
    /** 节点编号 */
    private String id;

    /** 节点内容 */
    private String text;

    /** 子节点 */
    private List<TreeNode> children = new ArrayList<TreeNode>();

    /** 节点其他属性 */
    private DefaultTreeNodeAttributes attributes;

    public TreeNode() {

    }

    public TreeNode(String id, String text) {
        this.id = id;
        this.text = text;
    }

    public TreeNode(String id, String text, DefaultTreeNodeAttributes attributes) {
        this.id = id;
        this.text = text;
        this.attributes = attributes;
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
     * 取得节点其他属性
     * 
     * @return 节点其他属性
     */
    public DefaultTreeNodeAttributes getAttributes() {
        return attributes;
    }

    /**
     * 设置节点其他属性
     * 
     * @param attributes 节点其他属性
     */
    public void setAttributes(DefaultTreeNodeAttributes attributes) {
        this.attributes = attributes;
    }
}
