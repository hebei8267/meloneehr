/*
 * Copyright 2008 by hebei, All rights reserved.
 */
package org.freedom.dao.ui;

import java.sql.SQLException;
import java.util.List;

import org.freedom.core.dao.impl.HibernateDaoImpl;
import org.freedom.entity.ui.MenuNodePermit;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;

import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.stereotype.Component;

/**
 * 角色可访问菜单树结点权限Dao
 * 
 * @author 何贝
 * @since JDK1.5
 */
@Component("menuNodePermitDao")
public class MenuNodePermitDao extends HibernateDaoImpl<MenuNodePermit> {
    /**
     * 取得用户可访问的菜单树结点权限列表
     * 
     * @param userID 用户ID
     * @return 用户可访问的菜单树结点权限列表
     */
    @SuppressWarnings("unchecked")
    public List<String> getMenuNodePermitListByUserID(String userID) {
        List<String> resultList = getHibernateTemplate().findByNamedQuery(
                "MenuNodePermit.getMenuNodePermitListByUserID", userID);

        return resultList;

    }

    /**
     * 根据用户ID和菜单节点ID取得访问权限对象
     * 
     * @param roleID 登录用户角色ID
     * @param menuNodeID 菜单节点ID
     * @return
     */
    @SuppressWarnings("unchecked")
    public MenuNodePermit getMenuNodePermitByRoleIDAndMenuNodeID(String roleID, String menuNodeID) {
        List<MenuNodePermit> resultList = getHibernateTemplate().findByNamedQuery(
                "MenuNodePermit.getMenuNodePermitByRoleIDAndMenuNodeID", new String[] { roleID, menuNodeID });

        if (resultList.size() > 0) {
            return resultList.get(0);
        }
        return null;

    }

    /**
     * 删除可访问菜单树结点权限列表
     * 
     * @param menuNodeID 菜单树结点ID
     * @return 删除的记录行数
     */
    public Integer delMenuNodePermitByMenuNodeID(final String menuNodeID) {
        return (Integer) getHibernateTemplate().execute(new HibernateCallback() {

            public Object doInHibernate(Session session) throws HibernateException, SQLException {

                Query query = session.getNamedQuery("MenuNodePermit.delMenuNodePermitByMenuNodeID");
                query.setString(0, menuNodeID);
                return query.executeUpdate();
            }

        });
    }

    /**
     * 删除可访问菜单树结点权限列表
     * 
     * @param menuNodeID 菜单树结点ID
     * @param roleIDs 角色ID数组
     * @return 删除的记录行数
     */
    public Integer delRoleMenuNodePermitByMenuNodeID(final String menuNodeID, final String[] roleIDs) {

        return (Integer) getHibernateTemplate().execute(new HibernateCallback() {

            public Object doInHibernate(Session session) throws HibernateException, SQLException {
                StringBuffer sql = new StringBuffer(" delete MenuNodePermit pObj where pObj.menuNodeID = ? and ( ");
                for (int i = 0; i < roleIDs.length; i++) {
                    if (i != 0) {
                        sql.append(" or ");
                    }
                    sql.append(" pObj.roleID = '" + roleIDs[i] + "' ");
                }

                sql.append(" ) ");
                Query query = session.createQuery(sql.toString());
                query.setString(0, menuNodeID);
                return query.executeUpdate();
            }

        });
    }
}
