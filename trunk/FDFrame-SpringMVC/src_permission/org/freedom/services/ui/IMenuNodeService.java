/*
 * Copyright 2008 by hebei, All rights reserved.
 */
package org.freedom.services.ui;

import java.util.List;

import org.freedom.core.view.vo.UIMenuTreeNode;
import org.freedom.entity.security.RoleMenuNodePermit;

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
     * @param roleMenuNodePermitList 拥有访问权限列表
     * @return 导航区列表菜单节点列表
     */
    public List<UIMenuTreeNode> getAllShipAreaMenuNode_Service(List<RoleMenuNodePermit> roleMenuNodePermitList);

    /**
     * 取得菜单树节点和其所有子节点信息
     * 
     * @param rootNodeId 菜单树节点
     * @return
     */
    public UIMenuTreeNode getMenuTreeNode_Service(String rootNodeId);
}
