/*
 * Copyright 2009 by hebei, All rights reserved.
 */
package org.freedom.services.permit;

import java.util.List;

import org.freedom.entity.common.Role;
import org.freedom.entity.ui.MenuNode;

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

    /**
     * 更新菜单树结点的可访问角色列表(访问角色列表与roleIDList内容信息相匹配,没有的添加,多余的删除)
     * 菜单节点的默认权限为[有访问限制]时操作,因为菜单树结构原因,同时修改其父节点的相关权限(只添加,不删除)
     * 
     * @param menuNode 菜单节点
     * @param roleIDList 可访问角色ID列表
     */
    public void updateMenuNodePermitService(MenuNode menuNode, List<String> roleIDList);

    /**
     * 更新菜单树结点的可访问角色列表
     * 
     * @param roleID 角色ID
     * @param dataVersion
     * @param menuIDList 菜单节点列表
     * @return 0-成功 1-失败(数据同步错误)
     */
    public int updateMenuNodePermitService(String roleID, int dataVersion, List<String> menuIDList);

    /**
     * 更新其所有子菜单树结点的可访问角色列表(访问角色列表与roleIDList内容信息相匹配,没有的添加,多余的删除)
     * 
     * @param parentNode 父菜单节点
     * @param roleIDList 可访问角色ID列表
     */
    public void updateSubMenuNodePermitService(MenuNode parentNode, List<String> roleIDList);
}
