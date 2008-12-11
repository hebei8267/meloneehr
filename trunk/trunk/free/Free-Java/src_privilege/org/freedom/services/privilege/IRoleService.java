/*
 * Copyright 2008 by hebei, All rights reserved.
 */
package org.freedom.services.privilege;

import org.freedom.entity.privilege.Role;

/**
 * 角色对象相关服务
 * 
 * @author 何贝
 * @since JDK1.5
 */
public interface IRoleService {
    /**
     * 添加角色在默认的根节点下
     * 
     * @param role 要添加的角色
     * @return true-成功 false-失败
     */
    public boolean addRoleInfoService(Role role);

    /**
     * 添加角色在指定的父角色对象下
     * 
     * @param role 要添加的角色
     * @param parentRoleID 父角色ID
     * @return true-成功 false-失败
     */
    public boolean addRoleInfoService(Role role, String parentRoleID);

    /**
     * 修改角色信息
     * 
     * @param role 要修改的角色
     * @return true-成功 false-失败
     */
    public boolean modRoleInfoService(Role role);

    public boolean delRoleInfoService(String roleID);

    public boolean getAllRoleInfoTreeService();
}
