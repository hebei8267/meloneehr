/*
 * Copyright 2009 by hebei, All rights reserved.
 */
package org.freedom.view.action.vobj.security.role.rs001;

import org.freedom.core.view.vo.jsp.AbstractViewObject;

/**
 * @author 何贝
 * @since JDK1.5
 */
public class RoleSetting001ViewObject extends AbstractViewObject {

    /**
     * 
     */
    private static final long serialVersionUID = -2033986271858423640L;
    /** 父节点ID */
    private String parentNodeID;
    /** 父节点内容 */
    private String parentNodeTxt;
    /** 节点编号 */
    private String nodeID;
    /** 节点内容 */
    private String nodeTxt;
    /** 节点详细描述 */
    private String nodeDetail;

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
     * 取得父节点内容
     * 
     * @return 父节点内容
     */
    public String getParentNodeTxt() {
        return parentNodeTxt;
    }

    /**
     * 设置父节点内容
     * 
     * @param parentNodeTxt 父节点内容
     */
    public void setParentNodeTxt(String parentNodeTxt) {
        this.parentNodeTxt = parentNodeTxt;
    }

    /**
     * 取得节点编号
     * 
     * @return 节点编号
     */
    public String getNodeID() {
        return nodeID;
    }

    /**
     * 设置节点编号
     * 
     * @param nodeID 节点编号
     */
    public void setNodeID(String nodeID) {
        this.nodeID = nodeID;
    }

    /**
     * 取得节点内容
     * 
     * @return 节点内容
     */
    public String getNodeTxt() {
        return nodeTxt;
    }

    /**
     * 设置节点内容
     * 
     * @param nodeTxt 节点内容
     */
    public void setNodeTxt(String nodeTxt) {
        this.nodeTxt = nodeTxt;
    }

    /**
     * 取得节点详细描述
     * 
     * @return 节点详细描述
     */
    public String getNodeDetail() {
        return nodeDetail;
    }

    /**
     * 设置节点详细描述
     * 
     * @param nodeDetail 节点详细描述
     */
    public void setNodeDetail(String nodeDetail) {
        this.nodeDetail = nodeDetail;
    }

}
