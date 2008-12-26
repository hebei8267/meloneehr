/*
 * Copyright 2008 by hebei, All rights reserved.
 */
package org.freedom.view.action.vobj.security.s003;

import java.util.ArrayList;
import java.util.List;

import org.freedom.core.domain.TreeNode;
import org.freedom.core.view.vo.jsp.AbstractViewObject;

/**
 * @author 何贝
 * @since JDK1.5
 */
public class Security003ViewObject extends AbstractViewObject {

    /**
     * 
     */
    private static final long serialVersionUID = 2296676160446468009L;

    /**
     * 导航区列表
     */
    private List<TreeNode> areaMenuNodeList = new ArrayList<TreeNode>();

    /**
     * 取得导航区列表
     * 
     * @return 导航区列表
     */
    public List<TreeNode> getAreaMenuNodeList() {
        return areaMenuNodeList;
    }

    /**
     * 设置导航区列表
     * 
     * @param areaMenuNodeList 导航区列表
     */
    public void setAreaMenuNodeList(List<TreeNode> areaMenuNodeList) {
        this.areaMenuNodeList = areaMenuNodeList;
    }

}
