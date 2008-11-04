/*
 * Copyright 2008 by hebei, All rights reserved.
 */
package org.freedom.services.ui;

import java.util.List;

import org.freedom.entity.security.Role;

/**
 * 系统菜单树访问权限相关服务
 * 
 * @author 何贝
 * @since JDK1.5
 */
public interface IMenuNodePermitService {
    /**
     * 添加指定菜单节点的权限访问(已经存在的访问权限将忽略)
     * 
     * @param menuNodeID 菜单节点
     * @param roleList 要添加角色列表
     */
    public void addMenuNodePermit_Service(String menuNodeID, List<Role> roleList);

    /**
     * 删除指定菜单节点的权限访问
     * 
     * @param menuNodeID 菜单节点
     * @param roleList 要删除角色列表
     * @return 删除的记录行数
     */
    public int delMenuNodePermit_Service(String menuNodeID, List<Role> roleList);

    /**
     * 检查用户访问菜单节点的权限
     * 
     * @param userID 用户ID
     * @param roleID 用户角色ID
     * @param menuNodeID
     * @return true-有访问权限 false-无访问权限
     */
    public boolean checkUserAccessMenuNodePermit_Service(String userID, String roleID, String menuNodeID);

    /**
     * 取得可访问菜单节点的角色列表
     * 
     * @param menuNodeID 菜单节点
     * @return
     */
    public List<Role> getAccessMenuNodePermitRoleInfoList_Service(String menuNodeID);
}
