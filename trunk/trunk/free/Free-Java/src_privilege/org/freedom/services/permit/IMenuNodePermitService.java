/*
 * Copyright 2009 by hebei, All rights reserved.
 */
package org.freedom.services.permit;

import java.util.List;

import org.freedom.entity.common.Role;

/**
 * 菜单树结点权限相关服务
 * 
 * @author 何贝
 * @since JDK1.5
 */
public interface IMenuNodePermitService {
    /**
     * 校验选中节点的访问权限
     * 
     * @param userID 用户ID
     * @param roleID 角色ID
     * @param nodeID 节点ID
     * @return true-校验成功 false-校验失败
     */
    public boolean checkMenuNodePermitService(String userID, String roleID, String nodeID);

    /**
     * 取得可访问菜单节点的角色列表
     * 
     * @param menuNodeID 菜单节点
     * @return
     */
    public List<Role> getAccessMenuNodePermitRoleInfoListService(String menuNodeID);
}
