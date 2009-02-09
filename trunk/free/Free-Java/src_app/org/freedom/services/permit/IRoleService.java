/*
 * Copyright 2008 by hebei, All rights reserved.
 */
package org.freedom.services.permit;

import java.util.List;

import org.freedom.core.domain.TreeNode;
import org.freedom.entity.common.Role;

/**
 * 角色对象相关服务
 * 
 * @author 何贝
 * @since JDK1.5
 */
public interface IRoleService {
    /**
     * 添加角色在指定的父角色对象下(父节点为空时默认添加到根节点下)
     * 
     * @param role 要添加的角色
     * @param inheritFlg 继承权限
     * @return true-成功 false-失败
     */
    public boolean addRoleInfoService(Role role, boolean inheritFlg);

    /**
     * 更新角色信息
     * 
     * @param role 要更新的角色
     * @return true-成功 false-失败
     */
    public boolean updateRoleInfoService(Role role);

    /**
     * 删除指定的角色(删除之前会检查其所有子角色是否有对应的关联用户对象,如果有将不能删除)
     * 
     * @param roleID 要删除的角色ID
     * @param dataVersion 版本
     * @return 0-成功 1-失败(数据同步错误) 2-失败(该角色和其所有子角色有关联用户信息)
     */
    public int delRoleInfoService(String roleID, int dataVersion);

    /**
     * 取得角色树信息
     * 
     * @return 角色树根节点
     */
    public TreeNode getAllRoleInfoTreeService();

    /**
     * 取得角色列表信息
     * 
     * @return 角色列表信息
     */
    public List<Role> getAllRoleInfoListService();
}
