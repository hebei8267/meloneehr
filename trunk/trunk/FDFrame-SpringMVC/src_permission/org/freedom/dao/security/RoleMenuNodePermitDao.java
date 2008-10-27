/*
 * Copyright 2008 by hebei, All rights reserved.
 */
package org.freedom.dao.security;

import java.util.List;

import org.freedom.core.dao.impl.HibernateDaoImpl;
import org.freedom.entity.security.RoleMenuNodePermit;
import org.springframework.stereotype.Component;

/**
 * 登录用户角色可访问菜单树结点权限Dao
 * 
 * @author 何贝
 * @since JDK1.5
 */
@Component("roleMenuNodePermitDao")
public class RoleMenuNodePermitDao extends HibernateDaoImpl<RoleMenuNodePermit> {
    /**
     * 取得用户可访问的菜单树结点权限列表
     * 
     * @param userID 用户ID
     * @return 用户可访问的菜单树结点权限列表
     */
    @SuppressWarnings("unchecked")
    public List<RoleMenuNodePermit> getRoleMenuNodePermitListByUserID(String userID) {
        List<RoleMenuNodePermit> resultList = getHibernateTemplate().findByNamedQuery(
                "RoleMenuNodePermit.getRoleMenuNodePermitListByUserID", userID);

        return resultList;

    }
}
