package com.jquery.core.modules.tree;

/**
 * 默认节点其他属性
 * 
 * @since JDK1.5
 */
public class DefaultTreeNodeAttributes {
    /** 父节点ID */
    protected String parentNodeID;

    /** 节点URL */
    protected String url;

    public DefaultTreeNodeAttributes() {

    }

    public DefaultTreeNodeAttributes(String url) {
        this.url = url;
    }

    public DefaultTreeNodeAttributes(String parentNodeID, String url) {
        this.parentNodeID = parentNodeID;
        this.url = url;
    }

    public String getParentNodeID() {
        return parentNodeID;
    }

    public void setParentNodeID(String parentNodeID) {
        this.parentNodeID = parentNodeID;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

}
