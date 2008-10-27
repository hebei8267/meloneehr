/*
 * Copyright 2008 by hebei, All rights reserved.
 */
package org.freedom.services.ui.impl;

import java.util.ArrayList;
import java.util.List;

import org.freedom.core.view.vo.UIMenuTreeNode;
import org.freedom.dao.ui.MenuNodeDao;
import org.freedom.entity.security.RoleMenuNodePermit;
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
    /**
     * 取得所有导航区列表菜单节点列表
     * 
     * @param roleMenuNodePermitList 拥有访问权限列表
     * @return 导航区列表菜单节点列表
     */
    public List<UIMenuTreeNode> getAllShipAreaMenuNode_Service(List<RoleMenuNodePermit> roleMenuNodePermitList) {
        MenuNode root = menuNodeDao.getMenuNodeByID(MenuNodeDao.ROOT_ID);

        return getAllShipAreaMenuNode(root, roleMenuNodePermitList);
    }

    /**
     * 取得其下一层子节点
     * 
     * hibernate lazy load原因
     * 
     * @param parentNode 父节点
     * @param roleMenuNodePermitList 拥有访问权限列表
     * @return 下一层子节点列表
     */
    private List<UIMenuTreeNode> getAllShipAreaMenuNode(MenuNode parentNode,
            List<RoleMenuNodePermit> roleMenuNodePermitList) {
        List<UIMenuTreeNode> _reList = new ArrayList<UIMenuTreeNode>();
        for (MenuNode menuNode : parentNode.getSubNodeList()) {
            boolean _addFlg = false;
            if (menuNode.getDefaultPermit()) {// 默认权限 "true"无访问限制 "false"有访问限制
                _addFlg = true;
            } else {
                for (RoleMenuNodePermit roleMenuNodePermit : roleMenuNodePermitList) {// 拥有访问权限
                    if (menuNode.getId().equals(roleMenuNodePermit.getMenuNodeID())) {
                        _addFlg = true;
                        break;
                    }
                }
            }

            if (_addFlg) {
                UIMenuTreeNode uiNode = new UIMenuTreeNode(menuNode.getId(), menuNode.getNodeTxt());

                _reList.add(uiNode);
            }
        }
        return _reList;
    }

    /**
     * 取得菜单树节点和其所有子节点信息
     * 
     * @param rootNodeId 菜单树节点
     * @param roleMenuNodePermitList 拥有访问权限列表
     * @return 菜单树节点和其所有子节点信息
     */
    public UIMenuTreeNode getMenuTreeNode_Service(String rootNodeId,
            List<RoleMenuNodePermit> roleMenuNodePermitList) {
        MenuNode dbNodeRoot = menuNodeDao.getMenuNodeByID(rootNodeId);

        if (dbNodeRoot != null) {
            menuNodeDao.initialize(dbNodeRoot);

            UIMenuTreeNode uiNodeRoot = new UIMenuTreeNode(dbNodeRoot.getId(), dbNodeRoot.getNodeTxt(),
                    MenuNode.LEAF_NODE_TYPE.equals(dbNodeRoot.getNodeType()));

            buildSubMenuTree(uiNodeRoot, dbNodeRoot, roleMenuNodePermitList);

            return uiNodeRoot;
        }
        return null;
    }

    /**
     * 创建树结构
     * 
     * @param parentNode
     * @param dbNodeRoot
     * @param roleMenuNodePermitList 拥有访问权限列表
     */
    private void buildSubMenuTree(UIMenuTreeNode parentNode, MenuNode dbNodeRoot,
            List<RoleMenuNodePermit> roleMenuNodePermitList) {

        for (MenuNode dbNode : dbNodeRoot.getSubNodeList()) {
            boolean _addFlg = false;
            if (dbNode != null) {
                if (dbNode.getDefaultPermit()) {// 默认权限 "true"无访问限制 "false"有访问限制
                    _addFlg = true;
                } else {
                    for (RoleMenuNodePermit roleMenuNodePermit : roleMenuNodePermitList) {// 拥有访问权限
                        if (dbNode.getId().equals(roleMenuNodePermit.getMenuNodeID())) {
                            _addFlg = true;
                            break;
                        }
                    }
                }

                if (_addFlg) {
                    UIMenuTreeNode uiNode = new UIMenuTreeNode(dbNode.getId(), dbNode.getNodeTxt(),
                            MenuNode.LEAF_NODE_TYPE.equals(dbNode.getNodeType()));

                    parentNode.addChildren(uiNode);

                    buildSubMenuTree(uiNode, dbNode, roleMenuNodePermitList);
                }
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
