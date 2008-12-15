/*
 * Copyright 2008 by hebei, All rights reserved.
 */
package org.freedom.services.privilege;

import org.freedom.core.domain.TreeNode;
import org.freedom.entity.ui.MenuNode;

/**
 * 菜单树结点相关服务
 * 
 * @author 何贝
 * @since JDK1.5
 */
public interface IMenuNodeService {
    /**
     * 添加菜单结点在默认的根节点下
     * 
     * @param menuNode 要添加的角色
     * @return true-成功 false-失败
     */
    public boolean addMenuNodeInfoService(MenuNode menuNode);

    /**
     * 添加菜单结点在指定的父菜单结点对象下
     * 
     * @param menuNode 要添加的菜单结点
     * @param parentMenuNodeID 父菜单结点ID
     * @return true-成功 false-失败
     */
    public boolean addMenuNodeInfoService(MenuNode menuNode, String parentMenuNodeID);

    /**
     * 修改菜单结点信息
     * 
     * @param menuNode 要修改的菜单结点
     * @return true-成功 false-失败
     */
    public boolean modMenuNodeInfoService(MenuNode menuNode);

    /**
     * 删除指定的菜单结点(同时删除角色和其关联的信息)
     * 
     * @param menuNodeID 要删除的菜单结点ID
     * @return true-成功 false-失败
     */
    public boolean delMenuNodeInfoService(String menuNodeID);

    /**
     * 取得菜单结点树信息
     * 
     * @return 菜单结点树根节点
     */
    public TreeNode getAllMenuNodeInfoTreeService();
}
