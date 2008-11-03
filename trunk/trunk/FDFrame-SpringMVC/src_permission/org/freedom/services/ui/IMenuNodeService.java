/*
 * Copyright 2008 by hebei, All rights reserved.
 */
package org.freedom.services.ui;

import java.util.List;

import org.freedom.core.view.vo.ajax.UITreeNode;
import org.freedom.entity.security.Role;
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
     * @param roleID 用户角色ID
     * @return 导航区列表菜单节点列表
     */
    public List<UITreeNode> getNavigationAreaMenuNode_Service(String userID, String roleID);

    /**
     * 取得菜单树节点和其所有子节点信息
     * 
     * @param rootNodeId 菜单树节点
     * @param userID 用户ID
     * @param roleID 用户角色ID
     * @return 菜单树节点和其所有子节点信息
     */
    public UITreeNode getMenuTreeNode_Service(String rootNodeId, String userID, String roleID);

    /**
     * 取得所有菜单树节点和其所有子节点信息
     * 
     * @param rootNodeId 菜单树节点
     * @return 所有菜单树节点和其所有子节点信息
     */
    public UITreeNode getNavigationAreaSubMenuTreeNode_Service(String rootNodeId);

    /**
     * 取得菜单树结点类型列表
     * 
     * @return 菜单树结点类型列表
     */
    public List<MenuNodeType> getMenuNodeTypeList_Service();

    /**
     * 取得可访问菜单节点的角色列表
     * 
     * @param menuNodeID 菜单节点
     * @return
     */
    public List<Role> getMenuNodeAccessRoleList_Service(String menuNodeID);

    /**
     * 删除指定的菜单树节点
     * 
     * @param menuNodeID
     * @return
     */
    public boolean delMenuTreeNode_Service(String menuNodeID);

    /**
     * 检查用户访问菜单节点的权限
     * 
     * @param userID 用户ID
     * @param roleID 用户角色ID
     * @param menuNodeID
     * @return true-有访问权限 false-无访问权限
     */
    public boolean checkUserAccessMenuNodePermit_Service(String userID, String roleID, String menuNodeID);
}
