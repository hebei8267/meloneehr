/*
 * Copyright 2008 by hebei, All rights reserved.
 */
package org.freedom.services.privilege.impl;

import org.apache.commons.lang.StringUtils;

import org.freedom.core.domain.TreeNode;
import org.freedom.dao.ui.MenuNodeDao;
import org.freedom.dao.ui.MenuNodeTypeDao;
import org.freedom.entity.ui.MenuNode;
import org.freedom.entity.ui.MenuNodeType;
import org.freedom.services.privilege.IMenuNodeService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * 菜单树结点相关服务
 * 
 * @author 何贝
 * @since JDK1.5
 */
@Component("menuNodeService")
@Scope("prototype")
public class MenuNodeServiceImp implements IMenuNodeService {

    // ---------------------------------------------------------------------------
    // 接口实现
    // ---------------------------------------------------------------------------
    public boolean addMenuNodeInfoService(MenuNode menuNode) {
        return addMenuNodeInfoService(menuNode, MenuNode.MENU_NODE_TREE_ROOT_ID);
    }

    public boolean addMenuNodeInfoService(MenuNode menuNode, String parentMenuNodeID) {
        if (StringUtils.isNotBlank(menuNode.getNodeTxt())
                && !MenuNodeType.NONE_NODE_TYPE.equals((menuNode.getNodeType()))
                && StringUtils.isNotBlank(menuNode.getActionContent())) {

            MenuNode parentMenuNode = menuNodeDao.getMenuNodeByID(parentMenuNodeID);

            // 子菜单节点类型检查
            if (menuNodeTypeCheck(parentMenuNode, menuNode)) {

                menuNode.setParentNode(parentMenuNode);
                menuNode.setId(menuNodeDao.getMaxID());

                parentMenuNode.addChildNode(menuNode);

                menuNodeDao.save(menuNode);
                return true;

            }
        }
        return false;
    }

    /**
     * 子菜单节点类型检查
     * 
     * @param parentMenuNode 父菜单节点
     * @param childMenuNode 子菜单节点
     * @return 检查结果
     */
    private boolean menuNodeTypeCheck(MenuNode parentMenuNode, MenuNode childMenuNode) {
        if (parentMenuNode == null) {
            return false;
        }
        // [根]节点下面只能是[导航条]节点
        if (MenuNodeType.ROOT_NODE_TYPE.equals(parentMenuNode.getNodeType())) {
            if (!MenuNodeType.AREA_NODE_TYPE.equals(childMenuNode.getNodeType())) {
                return false;
            }
        }
        // [导航条]节点下面只能是[文件夹]节点或者[叶节点]节点
        if (MenuNodeType.AREA_NODE_TYPE.equals(parentMenuNode.getNodeType())) {
            if (!(MenuNodeType.FOLDER_NODE_TYPE.equals(childMenuNode.getNodeType()) || MenuNodeType.LEAF_NODE_TYPE
                    .equals(childMenuNode.getNodeType()))) {
                return false;
            }
        }
        // [文件夹]节点下面只能是[叶节点]节点
        if (MenuNodeType.FOLDER_NODE_TYPE.equals(parentMenuNode.getNodeType())) {
            if (!MenuNodeType.LEAF_NODE_TYPE.equals(childMenuNode.getNodeType())) {
                return false;
            }
        }

        return true;
    }

    public boolean delMenuNodeInfoService(String menuNodeID) {
        // TODO Auto-generated method stub
        return false;
    }

    public TreeNode getAllMenuNodeInfoTreeService() {
        // TODO Auto-generated method stub
        return null;
    }

    public boolean modMenuNodeInfoService(MenuNode menuNode) {
        if (StringUtils.isNotBlank(menuNode.getNodeTxt())
                && !MenuNodeType.NONE_NODE_TYPE.equals((menuNode.getNodeType()))
                && StringUtils.isNotBlank(menuNode.getActionContent())) {
            MenuNode _dbMenuNode = menuNodeDao.getMenuNodeByID(menuNode.getId());

            _dbMenuNode.setNodeTxt(menuNode.getNodeTxt());
            _dbMenuNode.setDefaultPermit(menuNode.getDefaultPermit());
            _dbMenuNode.setActionContent(menuNode.getActionContent());
            if (menuNode.getIndex() != null && !_dbMenuNode.getIndex().equals(menuNode.getIndex())) {// index变更
                _dbMenuNode.setIndex(menuNode.getIndex());
                _dbMenuNode.updateNodeIndex();
            }

            menuNodeDao.save(_dbMenuNode);
            return true;

        }
        return false;
    }

    // ---------------------------------------------------------------------------
    // DAO
    // ---------------------------------------------------------------------------
    @Autowired
    private MenuNodeDao menuNodeDao;
    @Autowired
    private MenuNodeTypeDao menuNodeTypeDao;

    public MenuNodeDao getMenuNodeDao() {
        return menuNodeDao;
    }

    public void setMenuNodeDao(MenuNodeDao menuNodeDao) {
        this.menuNodeDao = menuNodeDao;
    }

    public MenuNodeTypeDao getMenuNodeTypeDao() {
        return menuNodeTypeDao;
    }

    public void setMenuNodeTypeDao(MenuNodeTypeDao menuNodeTypeDao) {
        this.menuNodeTypeDao = menuNodeTypeDao;
    }
}
