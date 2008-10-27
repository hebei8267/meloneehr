/*
 * Copyright 2008 by hebei, All rights reserved.
 */
package org.freedom.dao.security;

import java.util.List;

import org.freedom.core.dao.impl.HibernateDaoImpl;
import org.freedom.entity.security.Role;
import org.springframework.stereotype.Component;

/**
 * 登录用户角色Dao
 * 
 * @author 何贝
 * @since JDK1.5
 */
@Component("roleDao")
public class RoleDao extends HibernateDaoImpl<Role> {
    /**
     * 根据用户ID取得用户信息
     * 
     * @param roleID 登录用户角色ID
     * @return Role 登录用户角色
     */
    @SuppressWarnings("unchecked")
    public Role getRoleByID(String roleID) {
        List<Role> resultList = getHibernateTemplate().findByNamedQuery("Role.getRoleByID", roleID);
        if (resultList.size() > 0) {
            return resultList.get(0);
        }
        return null;
    }
}
