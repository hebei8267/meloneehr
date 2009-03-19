/*
 * Copyright 2008 by hebei, All rights reserved.
 */
package org.freedom.dao.ui;

import org.freedom.core.orm.hibernate.BaseHibernateDao;
import org.freedom.entity.ui.MenuNode;

import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

/**
 * 菜单树结点Dao
 * 
 * @author 何贝
 * @since JDK1.5
 */
@Repository("menuNodeDao")
public class MenuNodeDao extends BaseHibernateDao<MenuNode> {

    /**
     * 根据菜单树结点ID取得菜单树结点信息
     * 
     * @param nodeID 菜单树结点ID
     * @return 菜单树结点
     */
    public MenuNode getMenuNodeByID(String nodeID) {
        return (MenuNode) findUniqueByNamedQuery("MenuNode.getMenuNodeByID", nodeID);
    }

    /**
     * 自动计算当前最大ID
     * 
     * @return 最大ID
     */
    public String getMaxID() {
        String maxID = (String) findUniqueByNamedQuery("MenuNode.getMaxID");
        return formatMaxID(maxID);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.freedom.core.orm.hibernate.BaseHibernateDao#delete(java.lang.Object)
     */
    @Override
    public void delete(MenuNode menuNode) {
        if (MenuNode.MENU_NODE_TREE_ROOT_ID.equals(menuNode.getId())) {
            Assert.notNull(null, "不能删除根节点");
        }
        // 取得其父节点
        MenuNode parentNode = getMenuNodeByID(menuNode.getParentNodeID());
        parentNode.removeChildMenuNode(menuNode);
        // 更新兄弟节点index
        save(parentNode);
        // 删除自己
        super.delete(menuNode);
    }
}
