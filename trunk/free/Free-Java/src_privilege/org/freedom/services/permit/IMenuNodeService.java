/*
 * Copyright 2008 by hebei, All rights reserved.
 */
package org.freedom.services.permit;

import java.util.List;

import org.freedom.core.domain.TreeNode;
import org.freedom.entity.ui.MenuNode;
import org.freedom.entity.ui.MenuNodeType;

/**
 * 菜单树结点相关服务
 * 
 * @author 何贝
 * @since JDK1.5
 */
public interface IMenuNodeService {
    /**
     * 添加菜单结点在指定的父菜单结点对象下(默认的根节点下)
     * 
     * @param menuNode 要添加的菜单结点
     * @param inheritFlg 继承权限
     * @return 0-成功 1-失败(数据同步错误) 2-失败(节点类型不匹配)
     */
    public int addMenuNodeInfoService(MenuNode menuNode, boolean inheritFlg);

    /**
     * 修改菜单结点信息(包含其权限适用角色列表,同时也会修改其所有父节点的权限适用角色列表)
     * 
     * @param menuNode 要修改的菜单结点
     * @param roleIDList 菜单权限适用角色列表
     * @param applyArea true-仅该节点 false-包含所有子节点
     * @return 0-成功 1-失败(数据同步错误)
     */
    public int modMenuNodeInfoService(MenuNode menuNode, List<String> roleIDList, boolean applyArea);

    /**
     * 删除指定的菜单结点(同时删除角色和其关联的信息)
     * 
     * @param menuNodeID 要删除的菜单结点ID
     * @param dataVersion 版本
     * @return 0-成功 1-失败(数据同步错误)
     */
    public int delMenuNodeInfoService(String menuNodeID, int dataVersion);

    /**
     * 取得菜单结点树信息(根节点开始)包含权限校验
     * 
     * @param userID 用户ID
     * @param roleID 角色ID
     * @return 菜单结点树根节点
     */
    public TreeNode getMenuNodeInfoTreeService(String userID, String roleID);

    /**
     * 取得菜单结点树信息(指定根节点开始)包含权限校验
     * 
     * @param userID 用户ID
     * @param roleID 角色ID
     * @param rootID 根节点ID
     * @return 菜单结点树根节点
     */
    public TreeNode getMenuNodeInfoTreeService(String rootID, String userID, String roleID);

    /**
     * 取得菜单结点树信息(指定根节点开始)不包含权限校验
     * 
     * @param rootID 根节点ID
     * @return 菜单结点树根节点
     */
    public TreeNode getMenuNodeInfoTreeService(String rootID);

    /**
     * 取得所有导航条类型树节点
     * 
     * @param userID 用户ID
     * @param roleID 角色ID
     * @return 所有导航条类型树节点列表
     */
    public List<TreeNode> getAllAreaMenuNodeService(String userID, String roleID);

    /**
     * 取得菜单节点类型列表
     * 
     * @return 菜单节点类型列表
     */
    public List<MenuNodeType> getMenuNodeTypeInfoListService();
}
