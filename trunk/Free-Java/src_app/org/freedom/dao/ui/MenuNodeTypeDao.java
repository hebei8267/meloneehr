/*
 * Copyright 2008 by hebei, All rights reserved.
 */
package org.freedom.dao.ui;

import java.util.List;

import org.freedom.core.orm.hibernate.BaseHibernateDao;
import org.freedom.entity.ui.MenuNodeType;

import org.springframework.stereotype.Repository;

/**
 * 菜单树结点类型Dao
 * 
 * @author 何贝
 * @since JDK1.5
 */
@Repository("menuNodeTypeDao")
@SuppressWarnings("unchecked")
public class MenuNodeTypeDao extends BaseHibernateDao<MenuNodeType> {
    /**
     * 取得菜单树结点类型列表
     * 
     * @return 菜单树结点类型列表
     */
    public List<MenuNodeType> getMenuNodeTypeList() {
        return findByNamedQuery("MenuNodeType.getMenuNodeTypeList", MenuNodeType.MASTER_ID);
    }

    /**
     * 根据结点类型ID取得结点类型信息
     * 
     * @param userID 结点类型ID
     * @return MenuNodeType 结点类型
     */
    public MenuNodeType getMenuNodeTypeByID(String menuNodeTypeID) {
        return (MenuNodeType) findUniqueByNamedQuery("MenuNodeType.getMenuNodeTypeByID", MenuNodeType.MASTER_ID, menuNodeTypeID);
    }
}
