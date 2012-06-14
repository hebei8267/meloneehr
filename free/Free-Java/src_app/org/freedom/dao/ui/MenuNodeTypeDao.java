/*
 * Copyright 2008 by hebei, All rights reserved.
 */
package org.freedom.dao.ui;

import java.util.List;

import org.freedom.core.dao.impl.HibernateDaoImpl;
import org.freedom.entity.ui.MenuNodeType;

import org.springframework.stereotype.Repository;

/**
 * 菜单树结点类型Dao
 * 
 * @author 何贝
 * @since JDK1.5
 */
@Repository("menuNodeTypeDao")
public class MenuNodeTypeDao extends HibernateDaoImpl<MenuNodeType> {
    /**
     * 取得菜单树结点类型列表
     * 
     * @return 菜单树结点类型列表
     */
    @SuppressWarnings("unchecked")
    public List<MenuNodeType> getMenuNodeTypeList() {
        List<MenuNodeType> resultList = getHibernateTemplate().findByNamedQuery("MenuNodeType.getMenuNodeTypeList",
                MenuNodeType.MASTER_ID);

        return resultList;
    }

    /**
     * 根据结点类型ID取得结点类型信息
     * 
     * @param userID 结点类型ID
     * @return MenuNodeType 结点类型
     */
    @SuppressWarnings("unchecked")
    public MenuNodeType getMenuNodeTypeByID(String menuNodeTypeID) {

        List<MenuNodeType> resultList = getHibernateTemplate().findByNamedQuery("MenuNodeType.getMenuNodeTypeByID",
                new String[] { MenuNodeType.MASTER_ID, menuNodeTypeID });
        if (resultList.size() > 0) {
            return resultList.get(0);
        }
        return null;
    }
}
