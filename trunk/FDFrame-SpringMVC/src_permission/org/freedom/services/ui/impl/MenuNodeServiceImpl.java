/*
 * Copyright 2008 by hebei, All rights reserved.
 */
package org.freedom.services.ui.impl;

import java.util.ArrayList;
import java.util.List;

import org.freedom.core.view.vo.UIMenuTreeNode;
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
    public List<UIMenuTreeNode> getAllShipAreaMenuNode_Service() {
        MenuNode root = menuNodeDao.getMenuNodeByID(MenuNodeDao.ROOT_ID);

        return getAllShipAreaMenuNode(root);
    }

    /**
     * 取得其下一层子节点
     * 
     * @param parentNode
     * @return
     */
    private List<UIMenuTreeNode> getAllShipAreaMenuNode(MenuNode parentNode) {
        List<UIMenuTreeNode> _reList = new ArrayList<UIMenuTreeNode>();
        for (MenuNode menuNode : parentNode.getSubNodeList()) {

            UIMenuTreeNode uiNode = new UIMenuTreeNode(menuNode.getId(), menuNode.getNodeTxt());

            _reList.add(uiNode);
        }
        return _reList;
    }

    public UIMenuTreeNode getMenuTreeNode_Service(String rootNodeId) {
        MenuNode root = menuNodeDao.getMenuNodeByID(rootNodeId);

        if (root != null) {
            menuNodeDao.initialize(root);

            UIMenuTreeNode uiMenuNode = new UIMenuTreeNode(root.getId(), root.getNodeTxt());

            buildSubMenuTree(uiMenuNode, root);

            return uiMenuNode;
        }
        return null;
    }

    /**
     * 创建树结构
     * 
     * @param parentNode
     * @param subNodeList
     */
    private void buildSubMenuTree(UIMenuTreeNode parentNode, MenuNode root) {
        boolean firstFlg = true;
        for (MenuNode node : root.getSubNodeList()) {
            if (firstFlg) {
                parentNode.setLeaf(false);
                firstFlg = false;
            }
            if (node != null) {
                UIMenuTreeNode uiMenuNode = new UIMenuTreeNode(node.getId(), node.getNodeTxt());

                parentNode.addChildren(uiMenuNode);

                buildSubMenuTree(uiMenuNode, node);
            }
        }

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
