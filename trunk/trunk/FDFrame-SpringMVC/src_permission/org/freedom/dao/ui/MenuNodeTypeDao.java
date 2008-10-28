/*
 * Copyright 2008 by hebei, All rights reserved.
 */
package org.freedom.dao.ui;

import java.util.List;

import org.freedom.core.dao.impl.HibernateDaoImpl;
import org.freedom.entity.ui.MenuNodeType;
import org.springframework.stereotype.Component;

/**
 * 菜单树结点类型Dao
 * 
 * @author 何贝
 * @since JDK1.5
 */
@Component("menuNodeTypeDao")
public class MenuNodeTypeDao extends HibernateDaoImpl<MenuNodeType> {
    /**
     * 取得菜单树结点类型列表
     * 
     * @return 菜单树结点类型列表
     */
    @SuppressWarnings("unchecked")
    public List<MenuNodeType> getMenuNodeTypeList() {
        List<MenuNodeType> resultList = getHibernateTemplate().findByNamedQuery(
                "MenuNodeType.getMenuNodeTypeList", MenuNodeType.MASTER_ID);

        return resultList;
    }
}
