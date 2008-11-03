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
     * @param userID 用户ID
     * @param roleID 用户角色ID
     * @return 导航区列表菜单节点列表
     */
    public List<UITreeNode> getNavigationAreaMenuNode_Service(String userID, String roleID) {
        MenuNode root = menuNodeDao.getMenuNodeByID(MenuNode.ROOT_ID);

        List<String> roleMenuNodePermitList = null;
        if (!Role.ADMIN_ROLE_ID.equals(roleID)) {// 不是系统管理员ID
            // 取得用户可访问的菜单树结点权限列表
            roleMenuNodePermitList = roleMenuNodePermitDao.getRoleMenuNodePermitListByUserID(userID);
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
    private List<UITreeNode> getNavigationAreaMenuNode(MenuNode parentNode, String roleID, List<String> roleMenuNodePermitList) {
        List<UITreeNode> _reList = new ArrayList<UITreeNode>();
        for (MenuNode menuNode : parentNode.getSubNodeList()) {
            // 系统管理员ID
            // 默认权限 "true"无访问限制 "false"有访问限制
            // 拥有访问权限
            if (Role.ADMIN_ROLE_ID.equals(roleID) || menuNode.getDefaultPermit() || roleMenuNodePermitList.contains(menuNode.getId())) {
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
     * @param userID 用户ID
     * @param roleID 用户角色ID
     * @return 菜单树节点和其所有子节点信息
     */
    public UITreeNode getMenuTreeNode_Service(String rootNodeId, String userID, String roleID) {
        MenuNode dbNodeRoot = menuNodeDao.getMenuNodeByID(rootNodeId);

        if (dbNodeRoot != null) {
            List<String> roleMenuNodePermitList = null;
            if (!Role.ADMIN_ROLE_ID.equals(roleID)) {// 不是系统管理员ID
                // 取得用户可访问的菜单树结点权限列表
                roleMenuNodePermitList = roleMenuNodePermitDao.getRoleMenuNodePermitListByUserID(userID);
            }

            // 系统管理员ID
            // 默认权限 "true"无访问限制 "false"有访问限制
            // 拥有访问权限
            if (Role.ADMIN_ROLE_ID.equals(roleID) || dbNodeRoot.getDefaultPermit() || roleMenuNodePermitList.contains(dbNodeRoot.getId())) {
                UITreeNode uiTreeNode = new UITreeNode();

                uiTreeNode.setId(dbNodeRoot.getId());
                uiTreeNode.setText(dbNodeRoot.getNodeTxt());
                uiTreeNode.setLeaf(MenuNodeType.LEAF_NODE_TYPE.equals(dbNodeRoot.getNodeType()));
                uiTreeNode.setActionContent(dbNodeRoot.getActionContent());
                uiTreeNode.setUiNodeType(dbNodeRoot.getNodeType());
                uiTreeNode.setDefaultPermit(dbNodeRoot.getDefaultPermit());
                uiTreeNode.setParentNodeID(dbNodeRoot.getParentNodeID());

                buildSubMenuTree(uiTreeNode, dbNodeRoot, roleMenuNodePermitList, roleID);

                return uiTreeNode;
            }

        }
        return null;
    }

    /**
     * 创建树结构
     * 
     * @param parentNode 父节点
     * @param dbNodeRoot
     * @param roleMenuNodePermitList 拥有访问权限列表
     * @param roleID 用户角色ID
     */
    private void buildSubMenuTree(UITreeNode parentNode, MenuNode dbNodeRoot, List<String> roleMenuNodePermitList, String roleID) {

        for (MenuNode dbNode : dbNodeRoot.getSubNodeList()) {

            if (dbNode != null) {
                // 系统管理员ID
                // 默认权限 "true"无访问限制 "false"有访问限制
                // 拥有访问权限
                if (Role.ADMIN_ROLE_ID.equals(roleID) || dbNode.getDefaultPermit() || roleMenuNodePermitList.contains(dbNode.getId())) {
                    UITreeNode uiTreeNode = new UITreeNode();

                    uiTreeNode.setId(dbNode.getId());
                    uiTreeNode.setText(dbNode.getNodeTxt());
                    uiTreeNode.setLeaf(MenuNodeType.LEAF_NODE_TYPE.equals(dbNode.getNodeType()));
                    uiTreeNode.setActionContent(dbNode.getActionContent());
                    uiTreeNode.setUiNodeType(dbNode.getNodeType());
                    uiTreeNode.setDefaultPermit(dbNode.getDefaultPermit());
                    uiTreeNode.setParentNodeID(dbNode.getParentNodeID());

                    parentNode.addChildren(uiTreeNode);

                    buildSubMenuTree(uiTreeNode, dbNode, roleMenuNodePermitList, roleID);
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
    public UITreeNode getNavigationAreaSubMenuTreeNode_Service(String rootNodeId) {
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
    public List<Role> getMenuNodeAccessRoleList_Service(String menuNodeID) {
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

    /**
     * 检查用户访问菜单节点的权限
     * 
     * @param userID 用户ID
     * @param menuNodeID
     * @return true-有访问权限 false-无访问权限
     */
    public boolean checkUserAccessMenuNodePermit_Service(String userID, String roleID, String menuNodeID) {
        if (Role.ADMIN_ROLE_ID.equals(roleID)) {// 系统管理员ID
            return true;
        }
        List<String> nodeList = roleMenuNodePermitDao.getRoleMenuNodePermitListByUserID(userID);
        if (nodeList.contains(menuNodeID)) {
            return true;
        }
        return false;
    }

    /**
     * 删除指定的菜单树节点
     * 
     * @param menuNodeID
     * @return
     */
    public boolean delMenuTreeNode_Service(String menuNodeID) {
        // 菜单节点对象
        MenuNode menuNode = menuNodeDao.getMenuNodeByID(menuNodeID);
        if (menuNode == null) {// 要删除的对象不存在
            return false;
        }
        // 删除子菜单节点对象
        delSubMenuTreeNode(menuNode.getSubNodeList());
        // 删除该菜单节点关联的角色对象
        delMenuNodeAccessRole(menuNodeID);
        // 删除该菜单节点对象
        menuNodeDao.remove(menuNode);
        return true;
    }

    /**
     * 删除该菜单节点关联的角色对象
     * 
     * @param menuNodeID 菜单节点ID
     */
    private void delMenuNodeAccessRole(String menuNodeID) {
        roleMenuNodePermitDao.delRoleMenuNodePermitByMenuNodeID(menuNodeID);
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
                    delMenuNodeAccessRole(menuNode.getId());
                }
            }
        }
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
