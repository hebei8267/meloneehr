/*
 * Copyright 2008 by hebei, All rights reserved.
 */
package org.freedom.dao.privilege;

import java.util.List;

import org.freedom.core.dao.impl.HibernateDaoImpl;
import org.freedom.entity.privilege.Role;

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

    /**
     * 自动计算当前最大ID
     * 
     * @return 最大ID
     */
    @SuppressWarnings("unchecked")
    public String getMaxID() {
        List<String> resultList = getHibernateTemplate().findByNamedQuery("Role.getMaxID");
        String maxID = "";
        if (resultList.size() > 0) {
            maxID = resultList.get(0);
        }

        return formatMaxID(maxID);
    }

    /**
     * 取得于角色关联登录用户的个数
     * 
     * @return 关联个数
     */
    @SuppressWarnings("unchecked")
    public long getRole4UserCount(List<String> list) {
        StringBuffer _tmpBuf = new StringBuffer();
        for (String _str : list) {
            _tmpBuf.append("'");
            _tmpBuf.append(_str);
            _tmpBuf.append("',");
        }
        List<Long> resultList = getHibernateTemplate().findByNamedQuery("Role.getRole4UserCount",
                _tmpBuf.substring(1, _tmpBuf.length() - 2));
        if (resultList.size() > 0) {
            System.out.println(resultList.get(0).getClass());
            return resultList.get(0);
        }
        return 0;
    }

    /**
     * 取得可访问菜单节点的角色列表
     * 
     * @param menuNodeID 菜单节点ID
     * @return
     */
    @SuppressWarnings("unchecked")
    public List<Role> getRoleListByMenuNodeID(String menuNodeID) {
        return getHibernateTemplate().findByNamedQuery("Role.getRoleListByMenuNodeID", menuNodeID);
    }
}
