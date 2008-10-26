/*
 * Copyright 2008 by hebei, All rights reserved.
 */
package org.freedom.services.ui;

import java.util.List;

import org.freedom.core.view.vo.UIMenuTreeNode;

/**
 * 系统菜单树相关服务
 * 
 * @author 何贝
 * @since JDK1.5
 */
public interface IMenuNodeService {
    /**
     * 取得所有导航区列表菜单节点
     * 
     * @return
     */
    public List<UIMenuTreeNode> getAllShipAreaMenuNode_Service();

    /**
     * 取得菜单树节点和其所有子节点信息
     * 
     * @param rootNodeId 菜单树节点
     * @return
     */
    public UIMenuTreeNode getMenuTreeNode_Service(String rootNodeId);
}
