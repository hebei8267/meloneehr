/*
 * Copyright 2009 by hebei, All rights reserved.
 */
package org.freedom.services.permit.impl;

import java.util.ArrayList;
import java.util.List;

import org.freedom.dao.common.RoleDao;
import org.freedom.dao.ui.MenuNodeDao;
import org.freedom.dao.ui.MenuNodePermitDao;
import org.freedom.entity.common.Role;
import org.freedom.entity.ui.MenuNode;
import org.freedom.entity.ui.MenuNodePermit;
import org.freedom.entity.ui.MenuNodeType;
import org.freedom.services.permit.IMenuNodePermitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author 何贝
 * @since JDK1.5
 */
@Service("menuNodePermitService")
// 默认将类中的所有函数纳入事务管理.
@Transactional
public class MenuNodePermitServiceImpl implements IMenuNodePermitService {
    @Transactional(readOnly = true)
    public boolean checkMenuNodePermitService(String roleID, String nodeID) {
        if (Role.ADMIN_ROLE_ID.equals(roleID)) {// 系统管理员ID
            return true;
        }
        MenuNodePermit permit = menuNodePermitDao.getMenuNodePermitByRoleIDAndMenuNodeID(roleID, nodeID);
        if (permit != null) {
            return true;
        }
        return false;
    }

    @Transactional(readOnly = true)
    public List<Role> getAccessMenuNodePermitRoleInfoListService(String menuNodeID) {
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

    public void updateMenuNodePermitService(MenuNode menuNode, List<String> roleIDList) {

        // 菜单节点的默认权限为[有访问限制]
        if (menuNode.getDefaultPermit() == false) {
            List<MenuNodePermit> permitList = menuNodePermitDao.getMenuNodePermitListByMenuNodeID(menuNode.getId());
            // 以数据库中的角色列表对象为基础进行比较--不存在当前权限对象时做删除操作
            for (MenuNodePermit permit : permitList) {
                if (!roleIDList.contains(permit.getRoleID())) {
                    menuNodePermitDao.delete(permit);
                }
            }
            // 以新角色列表对象为基础进行比较--不存在当前权限对象时做添加操作
            for (String roleID : roleIDList) {
                if (roleID.equals(Role.ADMIN_ROLE_ID) || roleID.equals(Role.ROLE_TREE_ROOT_ID)) {
                    // 系统管理员默认拥有所有权限,不用添加权限
                    continue;
                }
                MenuNodePermit _newPermit = new MenuNodePermit();
                _newPermit.setMenuNodeID(menuNode.getId());
                _newPermit.setRoleID(roleID);

                if (!permitList.contains(_newPermit)) {
                    _newPermit.setMenuNode(menuNodeDao.getMenuNodeByID(_newPermit.getMenuNodeID()));
                    _newPermit.setRole(roleDao.getRoleByID(_newPermit.getRoleID()));
                    // 不存在当前权限对象时做添加操作
                    menuNodePermitDao.save(_newPermit);
                }
            }
        }
        // 更新菜单(父节点)节点的权限
        updateMenuParentNodePermit(menuNode.getParentNode(), roleIDList);
    }

    /**
     * 更新菜单(父节点)节点的权限
     * 
     * @param parentNode 菜单(父节点)节点
     * @param roleIDList 可访问角色ID列表
     * @throws Exception
     */
    private void updateMenuParentNodePermit(MenuNode parentNode, List<String> roleIDList) {
        if (!parentNode.getId().equals(MenuNode.MENU_NODE_TREE_ROOT_ID)) {
            // 菜单节点的默认权限为[有访问限制]
            if (parentNode.getDefaultPermit() == false) {

                List<MenuNodePermit> permitList = menuNodePermitDao.getMenuNodePermitListByMenuNodeID(parentNode.getId());

                for (String roleID : roleIDList) {
                    if (roleID.equals(Role.ADMIN_ROLE_ID) || roleID.equals(Role.ROLE_TREE_ROOT_ID)) {
                        // 系统管理员默认拥有所有权限,不用添加权限
                        continue;
                    }
                    MenuNodePermit _newPermit = new MenuNodePermit();
                    _newPermit.setMenuNodeID(parentNode.getId());
                    _newPermit.setRoleID(roleID);

                    if (!permitList.contains(_newPermit)) {// 不存在当前权限对象时做添加操作
                        _newPermit.setMenuNode(menuNodeDao.getMenuNodeByID(_newPermit.getMenuNodeID()));
                        _newPermit.setRole(roleDao.getRoleByID(_newPermit.getRoleID()));
                        menuNodePermitDao.save(_newPermit);
                    }
                }
            }
            // 递归调用,修改其父节点
            updateMenuParentNodePermit(parentNode.getParentNode(), roleIDList);
        }
    }

    public void updateSubMenuNodePermitService(MenuNode parentNode, List<String> roleIDList) {
        // 叶节点没有子节点
        if (MenuNodeType.LEAF_NODE_TYPE.equals(parentNode.getNodeType())) {
            return;
        }
        List<MenuNode> childNodeList = parentNode.getChildNodeList();

        for (MenuNode childNode : childNodeList) {
            if (childNode == null) {
                continue;
            }

            // 菜单节点的默认权限为[有访问限制]
            if (childNode.getDefaultPermit() == false) {
                List<MenuNodePermit> permitList = menuNodePermitDao.getMenuNodePermitListByMenuNodeID(childNode.getId());
                // 以数据库中的角色列表对象为基础进行比较--不存在当前权限对象时做删除操作
                for (MenuNodePermit permit : permitList) {
                    if (!roleIDList.contains(permit.getRoleID())) {
                        menuNodePermitDao.delete(permit);
                    }
                }
                // 以新角色列表对象为基础进行比较--不存在当前权限对象时做添加操作
                for (String roleID : roleIDList) {
                    if (roleID.equals(Role.ADMIN_ROLE_ID) || roleID.equals(Role.ROLE_TREE_ROOT_ID)) {
                        // 系统管理员默认拥有所有权限,不用添加权限
                        continue;
                    }
                    MenuNodePermit _newPermit = new MenuNodePermit();
                    _newPermit.setMenuNodeID(childNode.getId());
                    _newPermit.setRoleID(roleID);

                    if (!permitList.contains(_newPermit)) {
                        _newPermit.setMenuNode(menuNodeDao.getMenuNodeByID(_newPermit.getMenuNodeID()));
                        _newPermit.setRole(roleDao.getRoleByID(_newPermit.getRoleID()));
                        // 不存在当前权限对象时做添加操作
                        menuNodePermitDao.save(_newPermit);
                    }
                }
            }
            // 递归调用,修改其子节点
            updateSubMenuNodePermitService(childNode, roleIDList);
        }

    }

    public int updateMenuNodePermitService(String roleID, int dataVersion, List<String> menuIDList) {

        Role role = roleDao.getRoleByID(roleID);
        if (role != null && role.getVersion().equals(dataVersion)) {
            List<MenuNodePermit> permitList = menuNodePermitDao.getMenuNodePermitListByRoleID(roleID);

            // 以数据库中的角色列表对象为基础进行比较--不存在当前权限对象时做删除操作
            for (MenuNodePermit permit : permitList) {
                if (!menuIDList.contains(permit.getMenuNodeID())) {
                    menuNodePermitDao.delete(permit);
                }
            }
            // 以新角色列表对象为基础进行比较--不存在当前权限对象时做添加操作
            for (String menuID : menuIDList) {
                if (roleID.equals(Role.ADMIN_ROLE_ID) || roleID.equals(Role.ROLE_TREE_ROOT_ID)) {
                    // 系统管理员默认拥有所有权限,不用添加权限
                    continue;
                }

                MenuNode menuNode = menuNodeDao.getMenuNodeByID(menuID);
                // 菜单节点的默认权限为[无访问限制]时,不用添加权限
                if (menuNode.getDefaultPermit() == true) {
                    continue;
                }

                MenuNodePermit _newPermit = new MenuNodePermit();
                _newPermit.setMenuNodeID(menuID);
                _newPermit.setRoleID(roleID);

                if (!permitList.contains(_newPermit)) {
                    _newPermit.setMenuNode(menuNodeDao.getMenuNodeByID(_newPermit.getMenuNodeID()));
                    _newPermit.setRole(roleDao.getRoleByID(_newPermit.getRoleID()));
                    // 不存在当前权限对象时做添加操作
                    menuNodePermitDao.save(_newPermit);
                }
            }
            return 0;
        }
        return 1;
    }

    // ---------------------------------------------------------------------------
    // DAO
    // ---------------------------------------------------------------------------
    @Autowired
    private MenuNodePermitDao menuNodePermitDao;
    @Autowired
    private RoleDao roleDao;
    @Autowired
    private MenuNodeDao menuNodeDao;

}
