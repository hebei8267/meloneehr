/*
 * Copyright 2008 by hebei, All rights reserved.
 */
package org.freedom.services.ui.impl;

import java.util.ArrayList;
import java.util.List;

import org.freedom.dao.ui.MenuNodeDao;
import org.freedom.entity.ui.MenuNode;
import org.freedom.services.ui.IMenuNodeService;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * 系统菜单树相关服务
 * 
 * @author 何贝
 * @since JDK1.5
 */
@Component("menuNodeService")
@Scope("prototype")
public class MenuNodeServiceImpl implements IMenuNodeService {
    // ---------------------------------------------------------------------------
    // 接口实现
    // ---------------------------------------------------------------------------
    public List<MenuNode> getAllShipAreaMenuNode_Service() {
        MenuNode root = menuNodeDao.getMenuNodeByID(MenuNodeDao.ROOT_ID);

        return getAllShipAreaMenuNode(root);
    }

    /**
     * 取得其下一层子节点
     * 
     * @param parentNode
     * @return
     */
    private List<MenuNode> getAllShipAreaMenuNode(MenuNode parentNode) {
        List<MenuNode> _reList = new ArrayList<MenuNode>();
        for (MenuNode menuNode : parentNode.getSubNodeList()) {
            _reList.add(menuNode);
        }
        return _reList;
    }

    // ---------------------------------------------------------------------------
    // DAO
    // ---------------------------------------------------------------------------
    private MenuNodeDao menuNodeDao = null;

    public MenuNodeDao getMenuNodeDao() {
        return menuNodeDao;
    }

    public void setMenuNodeDao(MenuNodeDao menuNodeDao) {
        this.menuNodeDao = menuNodeDao;
    }
}
