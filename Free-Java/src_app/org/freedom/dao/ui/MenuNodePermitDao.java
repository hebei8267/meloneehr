/*
 * Copyright 2008 by hebei, All rights reserved.
 */
package org.freedom.dao.ui;

import java.util.List;

import org.freedom.core.orm.hibernate.BaseHibernateDao;
import org.freedom.entity.ui.MenuNodePermit;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

/**
 * 角色可访问菜单树结点权限Dao
 * 
 * @author 何贝
 * @since JDK1.5
 */
@Repository("menuNodePermitDao")
@SuppressWarnings("unchecked")
public class MenuNodePermitDao extends BaseHibernateDao<MenuNodePermit> {
    /**
     * 取得用户可访问的菜单树结点权限列表
     * 
     * @param userID 用户ID
     * @return 用户可访问的菜单树结点权限列表
     */
    public List<String> getMenuNodePermitListByUserID(String userID) {
        return findByNamedQuery("MenuNodePermit.getMenuNodePermitListByUserID", userID);
    }

    /**
     * 根据角色ID取得可访问的菜单树结点权限列表
     * 
     * @param roleID 角色ID
     * @return 可访问的菜单树结点权限列表
     */
    public List<MenuNodePermit> getMenuNodePermitListByRoleID(String roleID) {
        return findByNamedQuery("MenuNodePermit.getMenuNodePermitListByRoleID", roleID);
    }

    /**
     * 根据菜单节点ID取得可访问的菜单树结点权限列表
     * 
     * @param menuNodeID 菜单节点ID
     * @return 可访问的菜单树结点权限列表
     */
    public List<MenuNodePermit> getMenuNodePermitListByMenuNodeID(String menuNodeID) {
        return findByNamedQuery("MenuNodePermit.getMenuNodePermitListByMenuNodeID", menuNodeID);
    }

    /**
     * 根据用户ID和菜单节点ID取得访问权限对象
     * 
     * @param roleID 登录用户角色ID
     * @param menuNodeID 菜单节点ID
     * @return
     */
    public MenuNodePermit getMenuNodePermitByRoleIDAndMenuNodeID(String roleID, String menuNodeID) {
        return (MenuNodePermit) findUniqueByNamedQuery("MenuNodePermit.getMenuNodePermitByRoleIDAndMenuNodeID", roleID, menuNodeID);
    }

    /**
     * 删除可访问菜单树结点权限列表(包括子角色)
     * 
     * @param roleIDList 所有角色ID
     * @return 删除的记录行数
     */
    public Integer delMenuNodePermitByRoleID(List<String> roleIDList) {
        StringBuffer sql = new StringBuffer(" delete MenuNodePermit pObj where ");
        for (int i = 0; i < roleIDList.size(); i++) {
            if (i != 0) {
                sql.append(" or ");
            }
            sql.append(" pObj.roleID = '" + roleIDList.get(i) + "' ");
        }
        Query query = createQuery(sql.toString());
        return query.executeUpdate();
    }

    /**
     * 删除可访问菜单树结点权限列表
     * 
     * @param menuNodeID 菜单树结点ID
     * @return 删除的记录行数
     */
    public Integer delMenuNodePermitByMenuNodeID(List<String> menuIDList) {
        StringBuffer sql = new StringBuffer(" delete MenuNodePermit pObj where ");
        for (int i = 0; i < menuIDList.size(); i++) {
            if (i != 0) {
                sql.append(" or ");
            }
            sql.append(" pObj.menuNodeID = '" + menuIDList.get(i) + "' ");
        }

        Query query = createQuery(sql.toString());
        return query.executeUpdate();
    }

}
