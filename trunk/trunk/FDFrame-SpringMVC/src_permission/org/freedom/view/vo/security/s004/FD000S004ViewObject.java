/*
 * Copyright 2008 by hebei, All rights reserved.
 */
package org.freedom.view.vo.security.s004;

import java.util.ArrayList;
import java.util.List;

import org.freedom.core.view.vo.LabelValueBean;
import org.freedom.core.view.vo.UITreeNode;
import org.freedom.entity.ui.MenuNodeType;

/**
 * 菜单树管理界面ViewObject
 * 
 * @author 何贝
 * @since JDK1.5
 */
public class FD000S004ViewObject extends UITreeNode {

    private static final long serialVersionUID = -3699212498804881787L;

    /** 节点类型列表 */
    private List<LabelValueBean> nodeTypeList = new ArrayList<LabelValueBean>();

    public FD000S004ViewObject() {

    }

    public FD000S004ViewObject(List<MenuNodeType> _menuNodeTypeList) {
        for (MenuNodeType menuNodeType : _menuNodeTypeList) {
            nodeTypeList.add(new LabelValueBean(menuNodeType.getName(), menuNodeType.getSlaveID()));
        }
    }

    /**
     * 取得节点类型列表
     * 
     * @return 节点类型列表
     */
    public List<LabelValueBean> getNodeTypeList() {
        return nodeTypeList;
    }

    /**
     * 设置节点类型列表
     * 
     * @param nodeTypeList 节点类型列表
     */
    public void setNodeTypeList(List<LabelValueBean> nodeTypeList) {
        this.nodeTypeList = nodeTypeList;
    }

    /**
     * 设置节点类型
     * 
     * @param treeNodeType 节点类型
     */
    @Override
    public void setUiNodeType(String treeNodeType) {
        if (MenuNodeType.AREA_NODE_TYPE.equals(treeNodeType)) {
            super.setIcon("images/area.gif");
        }
        super.setUiNodeType(treeNodeType);
    }
}
