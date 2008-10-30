/*
 * Copyright 2008 by hebei, All rights reserved.
 */
package org.freedom.services.ui.impl;

import java.util.ArrayList;
import java.util.List;

import org.freedom.core.view.vo.ajax.UITreeNode;
import org.freedom.dao.security.RoleDao;
import org.freedom.dao.security.RoleMenuNodePermitDao;
import org.freedom.dao.ui.MenuNodeDao;
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
    // 接口实现
    // ---------------------------------------------------------------------------
    /**
     * 取得所有导航区列表菜单节点列表
     * 
     * @param roleMenuNodePermitList 拥有访问权限列表
     * @return 导航区列表菜单节点列表
     */
    public List<UITreeNode> getAllShipAreaMenuNode_Service(String userID) {
        MenuNode root = menuNodeDao.getMenuNodeByID(MenuNode.ROOT_ID);
        // 取得用户可访问的菜单树结点权限列表
        List<String> roleMenuNodePermitList = roleMenuNodePermitDao.getRoleMenuNodePermitListByUserID(userID);
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
    private List<UITreeNode> getAllShipAreaMenuNode(MenuNode parentNode, List<String> roleMenuNodePermitList) {
        List<UITreeNode> _reList = new ArrayList<UITreeNode>();
        for (MenuNode menuNode : parentNode.getSubNodeList()) {

            // 默认权限 "true"无访问限制 "false"有访问限制
            // 拥有访问权限
            if (menuNode.getDefaultPermit() || roleMenuNodePermitList.contains(menuNode.getId())) {
                UITreeNode uiNode = new UITreeNode(menuNode.getId(), menuNode.getNodeTxt(), null);

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
    public UITreeNode getMenuTreeNode_Service(String rootNodeId, String userID) {
        MenuNode dbNodeRoot = menuNodeDao.getMenuNodeByID(rootNodeId);

        if (dbNodeRoot != null) {
            // 取得用户可访问的菜单树结点权限列表
            List<String> roleMenuNodePermitList = roleMenuNodePermitDao
                    .getRoleMenuNodePermitListByUserID(userID);

            UITreeNode uiTreeNode = new UITreeNode();

            uiTreeNode.setId(dbNodeRoot.getId());
            uiTreeNode.setText(dbNodeRoot.getNodeTxt());
            uiTreeNode.setLeaf(MenuNodeType.LEAF_NODE_TYPE.equals(dbNodeRoot.getNodeType()));
            uiTreeNode.setActionContent(dbNodeRoot.getActionContent());
            uiTreeNode.setUiNodeType(dbNodeRoot.getNodeType());
            uiTreeNode.setDefaultPermit(dbNodeRoot.getDefaultPermit());
            uiTreeNode.setParentNodeID(dbNodeRoot.getParentNodeID());

            buildSubMenuTree(uiTreeNode, dbNodeRoot, roleMenuNodePermitList);

            return uiTreeNode;
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
    private void buildSubMenuTree(UITreeNode parentNode, MenuNode dbNodeRoot,
            List<String> roleMenuNodePermitList) {

        for (MenuNode dbNode : dbNodeRoot.getSubNodeList()) {

            if (dbNode != null) {
                // 默认权限 "true"无访问限制 "false"有访问限制
                // 拥有访问权限
                if (dbNode.getDefaultPermit() || roleMenuNodePermitList.contains(dbNode.getId())) {
                    UITreeNode uiTreeNode = new UITreeNode();

                    uiTreeNode.setId(dbNode.getId());
                    uiTreeNode.setText(dbNode.getNodeTxt());
                    uiTreeNode.setLeaf(MenuNodeType.LEAF_NODE_TYPE.equals(dbNode.getNodeType()));
                    uiTreeNode.setActionContent(dbNode.getActionContent());
                    uiTreeNode.setUiNodeType(dbNode.getNodeType());
                    uiTreeNode.setDefaultPermit(dbNode.getDefaultPermit());
                    uiTreeNode.setParentNodeID(dbNode.getParentNodeID());

                    parentNode.addChildren(uiTreeNode);

                    buildSubMenuTree(uiTreeNode, dbNode, roleMenuNodePermitList);
                }
            }
        }

    }

    /**
     * 取得菜单树结点类型列表
     * 
     * @return 菜单树结点类型列表
     */
    public List<MenuNodeType> getMenuNodeTypeList_Service() {
        return menuNodeTypeDao.getMenuNodeTypeList();
    }

    /**
     * 取得所有菜单树节点和其所有子节点信息
     * 
     * @param rootNodeId 菜单树节点
     * @return 所有菜单树节点和其所有子节点信息
     */
    public UITreeNode getAllMenuTreeNode_Service(String rootNodeId) {
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

            buildSubVOList(uiTreeNode, dbNodeRoot);

            return uiTreeNode;
        }
        return null;
    }

    /**
     * 构建整个树结构
     * 
     * @param parentNode
     * @param dbMenuNode
     */
    private void buildSubVOList(UITreeNode parentNode, MenuNode dbParentNode) {
        for (MenuNode dbChildNode : dbParentNode.getSubNodeList()) {

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

            buildSubVOList(childNode, dbChildNode);
        }
    }

    /**
     * 取得可访问菜单节点的角色列表
     * 
     * @param menuNodeID 菜单节点
     * @return
     */
    public List<Role> getRoleList_Service(String menuNodeID) {
        List<Role> roleList = roleDao.getRoleListByMenuNodeID(menuNodeID);
        if (roleList != null && roleList.size() != 0) {
            List<Role> resultList = new ArrayList<Role>();
            for (Role role : roleList) {
                Role _role = new Role();

                _role.setId(role.getId());
                _role.setName(role.getName());
                _role.setVersion(role.getVersion());

                resultList.add(_role);
            }
            return resultList;
        }

        return null;
    }

    // ---------------------------------------------------------------------------
    // DAO
    // ---------------------------------------------------------------------------
    private MenuNodeDao menuNodeDao = null;
    private MenuNodeTypeDao menuNodeTypeDao = null;
    private RoleMenuNodePermitDao roleMenuNodePermitDao = null;
    private RoleDao roleDao = null;

    public MenuNodeDao getMenuNodeDao() {
        return menuNodeDao;
    }

    public void setMenuNodeDao(MenuNodeDao menuNodeDao) {
        this.menuNodeDao = menuNodeDao;
    }

    public RoleMenuNodePermitDao getRoleMenuNodePermitDao() {
        return roleMenuNodePermitDao;
    }

    public void setRoleMenuNodePermitDao(RoleMenuNodePermitDao roleMenuNodePermitDao) {
        this.roleMenuNodePermitDao = roleMenuNodePermitDao;
    }

    public MenuNodeTypeDao getMenuNodeTypeDao() {
        return menuNodeTypeDao;
    }

    public void setMenuNodeTypeDao(MenuNodeTypeDao menuNodeTypeDao) {
        this.menuNodeTypeDao = menuNodeTypeDao;
    }

    public RoleDao getRoleDao() {
        return roleDao;
    }

    public void setRoleDao(RoleDao roleDao) {
        this.roleDao = roleDao;
    }

}
