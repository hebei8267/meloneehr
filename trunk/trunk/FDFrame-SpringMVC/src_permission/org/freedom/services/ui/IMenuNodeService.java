/*
 * Copyright 2008 by hebei, All rights reserved.
 */
package org.freedom.services.ui;

import java.util.List;

import org.freedom.core.view.vo.UITreeNode;
import org.freedom.entity.ui.MenuNodeType;

/**
 * 系统菜单树相关服务
 * 
 * @author 何贝
 * @since JDK1.5
 */
public interface IMenuNodeService {
    /**
     * 取得所有导航区列表菜单节点列表
     * 
     * @param userID 用户ID
     * @return 导航区列表菜单节点列表
     */
    public List<UITreeNode> getAllShipAreaMenuNode_Service(String userID);

    /**
     * 取得菜单树节点和其所有子节点信息
     * 
     * @param rootNodeId 菜单树节点
     * @param userID 用户ID
     * @return 菜单树节点和其所有子节点信息
     */
    public UITreeNode getMenuTreeNode_Service(String rootNodeId, String userID);

    /**
     * 取得所有菜单树节点和其所有子节点信息
     * 
     * @param rootNodeId 菜单树节点
     * @return 所有菜单树节点和其所有子节点信息
     */
    public UITreeNode getAllMenuTreeNode_Service(String rootNodeId);

    /**
     * 取得菜单树结点类型列表
     * 
     * @return 菜单树结点类型列表
     */
    public List<MenuNodeType> getMenuNodeTypeList_Service();
}
