/*
 * Copyright 2008 by hebei, All rights reserved.
 */
package org.freedom.dao.ui;

import java.util.List;

import org.freedom.core.dao.impl.HibernateDaoImpl;
import org.freedom.entity.ui.MenuNode;

import org.springframework.stereotype.Repository;

/**
 * 菜单树结点Dao
 * 
 * @author 何贝
 * @since JDK1.5
 */
@Repository("menuNodeDao")
public class MenuNodeDao extends HibernateDaoImpl<MenuNode> {

    /**
     * 根据菜单树结点ID取得菜单树结点信息
     * 
     * @param nodeID 菜单树结点ID
     * @return 菜单树结点
     */
    @SuppressWarnings("unchecked")
    public MenuNode getMenuNodeByID(String nodeID) {
        List<MenuNode> resultList = getHibernateTemplate().findByNamedQuery("MenuNode.getMenuNodeByID", nodeID);
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
        List<String> resultList = getHibernateTemplate().findByNamedQuery("MenuNode.getMaxID");
        String maxID = "";
        if (resultList.size() > 0) {
            maxID = resultList.get(0);
        }

        return formatMaxID(maxID);
    }

    public void delete(MenuNode menuNode) {
        MenuNode parentNode = getMenuNodeByID(menuNode.getParentNodeID());
        parentNode.removeChildMenuNode(menuNode);
        // 更新兄弟节点index
        save(parentNode);
        // 删除自己
        super.delete(menuNode);
    }
}
