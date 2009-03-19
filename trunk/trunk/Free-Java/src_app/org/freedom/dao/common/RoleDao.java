/*
 * Copyright 2008 by hebei, All rights reserved.
 */
package org.freedom.dao.common;

import java.util.List;

import org.freedom.core.orm.hibernate.BaseHibernateDao;
import org.freedom.entity.common.Role;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

/**
 * 登录用户角色Dao
 * 
 * @author 何贝
 * @since JDK1.5
 */
@Repository("roleDao")
@SuppressWarnings("unchecked")
public class RoleDao extends BaseHibernateDao<Role> {
    /**
     * 根据用户ID取得用户信息
     * 
     * @param roleID 登录用户角色ID
     * @return Role 登录用户角色
     */
    public Role getRoleByID(String roleID) {
        return (Role) findUniqueByNamedQuery("Role.getRoleByID", roleID);
    }

    /**
     * 自动计算当前最大ID
     * 
     * @return 最大ID
     */

    public String getMaxID() {
        String maxID = (String) findUniqueByNamedQuery("Role.getMaxID");
        return formatMaxID(maxID);
    }

    /**
     * 取得于角色关联登录用户的个数
     * 
     * @return 关联个数
     */
    public Long getRole4UserCount(final List<String> roleIDList) {
        Query query = getSession().getNamedQuery("Role.getRole4UserCount");
        query.setParameterList("roleIDs", roleIDList.toArray());
        return (Long) query.uniqueResult();

    }

    /**
     * 取得可访问菜单节点的角色列表
     * 
     * @param menuNodeID 菜单节点ID
     * @return
     */
    public List<Role> getRoleListByMenuNodeID(String menuNodeID) {
        return findByNamedQuery("Role.getRoleListByMenuNodeID", menuNodeID);
    }
}
