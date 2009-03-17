/*
 * Copyright 2008 by hebei, All rights reserved.
 */
package org.freedom.services.permit.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.freedom.core.domain.TreeNode;
import org.freedom.dao.common.RoleDao;
import org.freedom.dao.ui.MenuNodePermitDao;
import org.freedom.entity.common.Role;
import org.freedom.entity.ui.MenuNode;
import org.freedom.entity.ui.MenuNodePermit;
import org.freedom.services.permit.IRoleService;
import org.freedom.view.domain.system.RoleTreeNodeViewObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

/**
 * 角色对象相关服务
 * 
 * @author 何贝
 * @since JDK1.5
 */
@Service("roleService")
@Scope("prototype")
public class RoleServiceImpl implements IRoleService {
    // ---------------------------------------------------------------------------
    // 接口实现
    // ---------------------------------------------------------------------------
    public boolean addRoleInfoService(Role role, boolean inheritFlg) {

        if (StringUtils.isNotBlank(role.getName())) {
            if (StringUtils.isBlank(role.getParentRoleID())) {// 父节点为空时默认添加到根节点
                role.setParentRoleID(Role.ROLE_TREE_ROOT_ID);
            }

            Role parentRole = roleDao.getRoleByID(role.getParentRoleID());
            if (parentRole != null) {
                role.setParentRole(parentRole);
                role.setId(roleDao.getMaxID());
                // 添加角色
                roleDao.save(role);

                if (inheritFlg) {// 继承权限
                    List<MenuNodePermit> permitList = menuNodePermitDao.getMenuNodePermitListByRoleID(parentRole.getId());

                    for (MenuNodePermit _permitList : permitList) {
                        MenuNodePermit newPermit = new MenuNodePermit();

                        MenuNode menuNode = _permitList.getMenuNode();
                        newPermit.setMenuNode(menuNode);

                        newPermit.setRole(role);

                        menuNodePermitDao.save(newPermit);
                    }
                }
                return true;
            }
        }

        return false;
    }

    public int delRoleInfoService(String roleID, int dataVersion) {
        Role dbRole = roleDao.getRoleByID(roleID);
        if (dbRole == null || !dbRole.getVersion().equals(dataVersion)) {
            return 1;
        }

        List<String> roleIDList = new ArrayList<String>();
        // 取得所有角色ID
        getSubRoleID(dbRole, roleIDList);

        if (!checkRole4User(dbRole, roleIDList)) {
            return 2;
        }

        // 删除角色能访问的菜单节点
        menuNodePermitDao.delMenuNodePermitByRoleID(roleIDList);
        // 删除角色
        roleDao.delete(dbRole);

        return 0;
    }

    /**
     * 检验该角色是否可以删除(该角色和和其所有子角色是否关联用户信息)
     * 
     * @param role 检验的角色
     * @param roleIDList 所有子角色ID
     * @return true-未关联用户信息(可以删除) false-关联用户信息(不能删除)
     */
    private boolean checkRole4User(Role role, List<String> roleIDList) {

        int count = roleDao.getRole4UserCount(roleIDList);

        if (0 == count) {
            return true;
        }

        return false;
    }

    /**
     * 取得所有子角色ID
     * 
     * @param role 父角色
     * @param roleIDList 保存角色ID的容器
     */
    private void getSubRoleID(Role role, List<String> roleIDList) {
        if (role != null) {
            roleIDList.add(role.getId());

            Set<Role> subRoleSet = role.getChildRoleSet();
            if (!subRoleSet.isEmpty()) {
                for (Role _role : subRoleSet) {
                    getSubRoleID(_role, roleIDList);
                }
            }
        }
    }

    public TreeNode getAllRoleInfoTreeService() {
        Role dbRole = roleDao.getRoleByID(Role.ROLE_TREE_ROOT_ID);
        if (dbRole != null) {
            TreeNode root = new RoleTreeNodeViewObject();

            root.setId(dbRole.getId());
            root.setText(dbRole.getName());
            root.setVersion(dbRole.getVersion());
            root.setParentNodeID(dbRole.getParentRoleID());

            // 构建整个角色树结构
            buildSubMenuTreeInfo(root, dbRole);

            return root;
        }

        return null;
    }

    /**
     * 构建整个角色树结构
     * 
     * @param parentNode 父角色节点
     * @param dbParentRole 数据库中取得的父角色节点
     */
    private void buildSubMenuTreeInfo(TreeNode parentNode, Role dbParentRole) {
        for (Role dbChildRole : dbParentRole.getChildRoleSet()) {
            if (dbChildRole != null) {

                RoleTreeNodeViewObject childRole = new RoleTreeNodeViewObject();
                childRole.setId(dbChildRole.getId());
                childRole.setText(dbChildRole.getName());
                childRole.setDetail(dbChildRole.getDetail());
                childRole.setVersion(dbChildRole.getVersion());
                childRole.setParentNodeID(dbChildRole.getParentRoleID());
                childRole.setParentNodeText(dbChildRole.getParentRole().getName());

                parentNode.setLeaf(false);

                parentNode.addChildren(childRole);

                buildSubMenuTreeInfo(childRole, dbChildRole);
            }
        }
    }

    public boolean updateRoleInfoService(Role role) {
        if (StringUtils.isNotBlank(role.getName())) {
            Role dbRole = roleDao.getRoleByID(role.getId());

            if (dbRole != null) {
                if (dbRole.getVersion().equals(role.getVersion())) {
                    dbRole.setName(role.getName());
                    dbRole.setDetail(role.getDetail());

                    roleDao.save(dbRole);
                    return true;
                }
            }
        }
        return false;
    }

    public List<Role> getAllRoleInfoListService() {
        List<Role> dbRoleList = roleDao.getAll();

        List<Role> _resultRoleList = new ArrayList<Role>();

        for (Role dbRole : dbRoleList) {
            Role _role = new Role();
            // 不显示[角色树更节点]和[系统管理员]
            if (!Role.ROLE_TREE_ROOT_ID.equals(dbRole.getId()) && !Role.ADMIN_ROLE_ID.equals(dbRole.getId())) {
                _role.setId(dbRole.getId());
                _role.setName(dbRole.getName());

                _resultRoleList.add(_role);
            }

        }

        return _resultRoleList;

    }

    // ---------------------------------------------------------------------------
    // DAO
    // ---------------------------------------------------------------------------
    @Autowired
    private RoleDao roleDao;
    @Autowired
    private MenuNodePermitDao menuNodePermitDao;

    public RoleDao getRoleDao() {
        return roleDao;
    }

    public void setRoleDao(RoleDao roleDao) {
        this.roleDao = roleDao;
    }

    public MenuNodePermitDao getMenuNodePermitDao() {
        return menuNodePermitDao;
    }

    public void setMenuNodePermitDao(MenuNodePermitDao menuNodePermitDao) {
        this.menuNodePermitDao = menuNodePermitDao;
    }

}
