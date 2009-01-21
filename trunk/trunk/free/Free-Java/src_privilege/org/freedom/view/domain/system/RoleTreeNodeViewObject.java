/*
 * Copyright 2009 by hebei, All rights reserved.
 */
package org.freedom.view.domain.system;

import org.freedom.core.domain.TreeNode;
import org.freedom.view.SysConstant;

/**
 * 角色树节点
 * 
 * @author 何贝
 * @since JDK1.5
 */
public class RoleTreeNodeViewObject extends TreeNode {
    /**
     * 
     */
    private static final long serialVersionUID = -1987051473474665434L;
    /** 父节点内容 */
    private String parentNodeText;
    /** 节点内容 */
    private String detail;

    public RoleTreeNodeViewObject() {

        setIcon(SysConstant.WEB_PROJECT_NAME + "/images/role.gif");

    }

    /**
     * 取得父节点内容
     * 
     * @return 父节点内容
     */
    public String getParentNodeText() {
        return parentNodeText;
    }

    /**
     * 设置父节点内容
     * 
     * @param parentNodeText 父节点内容
     */
    public void setParentNodeText(String parentNodeText) {
        this.parentNodeText = parentNodeText;
    }

    /**
     * 取得节点内容
     * 
     * @return 节点内容
     */
    public String getDetail() {
        return detail;
    }

    /**
     * 设置节点内容
     * 
     * @param detail 节点内容
     */
    public void setDetail(String detail) {
        this.detail = detail;
    }

}
