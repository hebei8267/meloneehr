/*
 * Copyright 2008 by hebei, All rights reserved.
 */
package org.freedom.services.ui.impl;

import java.util.ArrayList;
import java.util.List;

import org.freedom.core.view.vo.ajax.UITreeNode;
import org.freedom.dao.ui.MenuNodeDao;
import org.freedom.dao.ui.MenuNodePermitDao;
import org.freedom.dao.ui.MenuNodeTypeDao;
import org.freedom.entity.security.Role;
import org.freedom.entity.ui.MenuNode;
import org.freedom.entity.ui.MenuNodeType;
import org.freedom.services.ui.IMenuNodeService;
import org.freedom.view.vo.security.s004.FD000S004ViewObject;
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
    // DAO
    // ---------------------------------------------------------------------------
    private MenuNodeDao menuNodeDao = null;
    private MenuNodeTypeDao menuNodeTypeDao = null;
    private MenuNodePermitDao menuNodePermitDao = null;

    public MenuNodeDao getMenuNodeDao() {
        return menuNodeDao;
    }

    public void setMenuNodeDao(MenuNodeDao menuNodeDao) {
        this.menuNodeDao = menuNodeDao;
    }

    public MenuNodePermitDao getMenuNodePermitDao() {
        return menuNodePermitDao;
    }

    public void setMenuNodePermitDao(MenuNodePermitDao menuNodePermitDao) {
        this.menuNodePermitDao = menuNodePermitDao;
    }

    public MenuNodeTypeDao getMenuNodeTypeDao() {
        return menuNodeTypeDao;
    }

    public void setMenuNodeTypeDao(MenuNodeTypeDao menuNodeTypeDao) {
        this.menuNodeTypeDao = menuNodeTypeDao;
    }

    // ---------------------------------------------------------------------------
    // 接口实现
    // ---------------------------------------------------------------------------

    public boolean delMenuTreeNode_Service(String menuNodeID) {
        // 菜单节点对象
        MenuNode menuNode = menuNodeDao.getMenuNodeByID(menuNodeID);
        if (menuNode == null) {// 要删除的对象不存在
            return false;
        }
        // 删除子菜单节点对象
        delSubMenuTreeNode(menuNode.getSubNodeList());
        // 删除该菜单节点关联的角色对象
        delMenuNodePermit(menuNodeID);
        // 删除该菜单节点对象
        menuNodeDao.remove(menuNode);
        return true;
    }

    /**
     * 删除子菜单节点对象
     * 
     * @param subMenuNodeList
     */
    private void delSubMenuTreeNode(List<MenuNode> subMenuNodeList) {
        if (subMenuNodeList != null) {
            for (MenuNode menuNode : subMenuNodeList) {
                if (menuNode != null) {
                    // 删除子菜单节点对象
                    delSubMenuTreeNode(menuNode.getSubNodeList());
                    // 删除该菜单节点关联的角色对象
                    delMenuNodePermit(menuNode.getId());
                }
            }
        }
    }

    /**
     * 删除该菜单节点关联的角色对象
     * 
     * @param menuNodeID 菜单节点ID
     */
    private void delMenuNodePermit(String menuNodeID) {
        menuNodePermitDao.delMenuNodePermitByMenuNodeID(menuNodeID);
    }

    public UITreeNode getAllMenuTreeInfo_Service(String rootNodeId) {
        MenuNode dbNodeRoot = menuNodeDao.getMenuNodeByID(rootNodeId);
        if (dbNodeRoot != null) {

            UITreeNode uiTreeNode = new UITreeNode();

            uiTreeNode.setId(dbNodeRoot.getId());
            uiTreeNode.setText(dbNodeRoot.getNodeTxt());
            uiTreeNode.setLeaf(MenuNodeType.LEAF_NODE_TYPE.equals(dbNodeRoot.getNodeType()));
            uiTreeNode.setActionContent(dbNodeRoot.getActionContent());
            uiTreeNode.setUiNodeType(dbNodeRoot.getNodeType());
            uiTreeNode.setDefaultPermit(dbNodeRoot.getDefaultPermit());
            uiTreeNode.setParentNodeID(dbNodeRoot.getParentNodeID());
            uiTreeNode.setUiNodeIndex("");
            // 构建整个菜单树结构
            buildSubMenuTreeInfo(uiTreeNode, dbNodeRoot);

            return uiTreeNode;
        }
        return null;
    }

    /**
     * 构建整个菜单树结构
     * 
     * @param parentNode 父节点
     * @param dbMenuNode 数据库中取得的菜单节点
     */
    private void buildSubMenuTreeInfo(UITreeNode parentNode, MenuNode dbParentNode) {
        for (MenuNode dbChildNode : dbParentNode.getSubNodeList()) {
            if (dbChildNode != null) {

                FD000S004ViewObject childNode = new FD000S004ViewObject();

                childNode.setId(dbChildNode.getId());
                childNode.setText(dbChildNode.getNodeTxt());
                childNode.setLeaf(MenuNodeType.LEAF_NODE_TYPE.equals(dbChildNode.getNodeType()));
                childNode.setActionContent(dbChildNode.getActionContent());
                childNode.setUiNodeType(dbChildNode.getNodeType());
                childNode.setDefaultPermit(dbChildNode.getDefaultPermit());
                childNode.setParentNodeID(dbChildNode.getParentNodeID());
                childNode.setUiNodeIndex(String.valueOf(dbChildNode.getIndex()));

                parentNode.addChildren(childNode);

                buildSubMenuTreeInfo(childNode, dbChildNode);
            }
        }
    }

    public List<MenuNodeType> getMenuNodeTypeList_Service() {
        return menuNodeTypeDao.getMenuNodeTypeList();
    }

    public List<UITreeNode> getNavigationAreaMenuNode_Service(String userID, String roleID) {
        MenuNode root = menuNodeDao.getMenuNodeByID(MenuNode.ROOT_ID);

        List<String> roleMenuNodePermitList = null;
        if (!Role.ADMIN_ROLE_ID.equals(roleID)) {// 不是系统管理员ID
            // 取得用户可访问的菜单树结点权限列表
            roleMenuNodePermitList = menuNodePermitDao.getMenuNodePermitListByUserID(userID);
        }

        return getNavigationAreaMenuNode(root, roleID, roleMenuNodePermitList);
    }

    /**
     * 取得其下一层子节点
     * 
     * hibernate lazy load原因
     * 
     * @param parentNode 父节点
     * @param roleID 用户角色ID
     * @param roleMenuNodePermitList 拥有访问权限列表
     * @return
     */
    private List<UITreeNode> getNavigationAreaMenuNode(MenuNode parentNode, String roleID,
            List<String> roleMenuNodePermitList) {
        List<UITreeNode> _reList = new ArrayList<UITreeNode>();
        for (MenuNode menuNode : parentNode.getSubNodeList()) {
            // 系统管理员ID
            // 默认权限 "true"无访问限制 "false"有访问限制
            // 拥有访问权限
            if (Role.ADMIN_ROLE_ID.equals(roleID) || menuNode.getDefaultPermit()
                    || roleMenuNodePermitList.contains(menuNode.getId())) {
                UITreeNode uiNode = new UITreeNode(menuNode.getId(), menuNode.getNodeTxt(), null);

                _reList.add(uiNode);
            }
        }
        return _reList;
    }

    public UITreeNode getNavigationAreaMenuTreeInfo_Service(String rootNodeId, String userID, String roleID) {
        MenuNode dbNodeRoot = menuNodeDao.getMenuNodeByID(rootNodeId);

        if (dbNodeRoot != null) {
            List<String> roleMenuNodePermitList = null;
            if (!Role.ADMIN_ROLE_ID.equals(roleID)) {// 不是系统管理员ID
                // 取得用户可访问的菜单树结点权限列表
                roleMenuNodePermitList = menuNodePermitDao.getMenuNodePermitListByUserID(userID);
            }

            // 系统管理员ID
            // 默认权限 "true"无访问限制 "false"有访问限制
            // 拥有访问权限
            if (Role.ADMIN_ROLE_ID.equals(roleID) || dbNodeRoot.getDefaultPermit()
                    || roleMenuNodePermitList.contains(dbNodeRoot.getId())) {
                UITreeNode uiTreeNode = new UITreeNode();

                uiTreeNode.setId(dbNodeRoot.getId());
                uiTreeNode.setText(dbNodeRoot.getNodeTxt());
                uiTreeNode.setLeaf(MenuNodeType.LEAF_NODE_TYPE.equals(dbNodeRoot.getNodeType()));
                uiTreeNode.setActionContent(dbNodeRoot.getActionContent());
                uiTreeNode.setUiNodeType(dbNodeRoot.getNodeType());
                uiTreeNode.setDefaultPermit(dbNodeRoot.getDefaultPermit());
                uiTreeNode.setParentNodeID(dbNodeRoot.getParentNodeID());
                // 创建导航区菜单树结构
                buildSubNavigationAreaMenuTreeInfo(uiTreeNode, dbNodeRoot, roleMenuNodePermitList, roleID);

                return uiTreeNode;
            }

        }
        return null;
    }

    /**
     * 创建导航区菜单树结构
     * 
     * @param parentNode 父节点
     * @param dbNodeRoot
     * @param roleMenuNodePermitList 拥有访问权限列表
     * @param roleID 用户角色ID
     */
    private void buildSubNavigationAreaMenuTreeInfo(UITreeNode parentNode, MenuNode dbNodeRoot,
            List<String> roleMenuNodePermitList, String roleID) {

        for (MenuNode dbNode : dbNodeRoot.getSubNodeList()) {

            if (dbNode != null) {
                // 系统管理员ID
                // 默认权限 "true"无访问限制 "false"有访问限制
                // 拥有访问权限
                if (Role.ADMIN_ROLE_ID.equals(roleID) || dbNode.getDefaultPermit()
                        || roleMenuNodePermitList.contains(dbNode.getId())) {
                    UITreeNode uiTreeNode = new UITreeNode();

                    uiTreeNode.setId(dbNode.getId());
                    uiTreeNode.setText(dbNode.getNodeTxt());
                    uiTreeNode.setLeaf(MenuNodeType.LEAF_NODE_TYPE.equals(dbNode.getNodeType()));
                    uiTreeNode.setActionContent(dbNode.getActionContent());
                    uiTreeNode.setUiNodeType(dbNode.getNodeType());
                    uiTreeNode.setDefaultPermit(dbNode.getDefaultPermit());
                    uiTreeNode.setParentNodeID(dbNode.getParentNodeID());

                    parentNode.addChildren(uiTreeNode);

                    buildSubNavigationAreaMenuTreeInfo(uiTreeNode, dbNode, roleMenuNodePermitList, roleID);
                }
            }
        }

    }
}
