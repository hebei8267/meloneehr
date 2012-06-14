/*
 * Copyright 2008 by hebei, All rights reserved.
 */
package org.freedom.dao.ui;

import java.util.List;

import org.freedom.core.dao.impl.HibernateDaoImpl;
import org.freedom.entity.ui.MenuNode;
import org.springframework.stereotype.Component;

/**
 * 菜单树结点Dao
 * 
 * @author 何贝
 * @since JDK1.5
 */
@Component("menuNodeDao")
public class MenuNodeDao extends HibernateDaoImpl<MenuNode> {

    /**
     * 根据菜单树结点ID取得菜单树结点信息
     * 
     * @param nodeID 菜单树结点ID
     * @return 菜单树结点
     */
    @SuppressWarnings("unchecked")
    public MenuNode getMenuNodeByID(String nodeID) {
        List<MenuNode> resultList = getHibernateTemplate().findByNamedQuery("MenuNode.getMenuNodeByID",
                nodeID);
        if (resultList.size() > 0) {
            return resultList.get(0);
        }
        return null;
    }
}
