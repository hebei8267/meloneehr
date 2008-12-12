/*
 * Copyright 2008 by hebei, All rights reserved.
 */
package org.freedom.services.privilege.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.freedom.core.domain.TreeNode;
import org.freedom.dao.privilege.RoleDao;
import org.freedom.entity.privilege.Role;
import org.freedom.services.privilege.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * 角色对象相关服务
 * 
 * @author 何贝
 * @since JDK1.5
 */
@Component("roleService")
@Scope("prototype")
public class RoleServiceImpl implements IRoleService {
    // ---------------------------------------------------------------------------
    // 接口实现
    // ---------------------------------------------------------------------------
    public boolean addRoleInfoService(Role role) {
        return addRoleInfoService(role, Role.ROLE_TREE_ROOT_ID);
    }

    public boolean addRoleInfoService(Role role, String parentRoleID) {
        if (StringUtils.isNotBlank(role.getName())) {
            Role parentRole = roleDao.getRoleByID(parentRoleID);
            if (parentRole != null) {
                role.setParentRole(parentRole);
                role.setId(roleDao.getMaxID());

                roleDao.save(role);
                return true;
            }
        }
        return false;
    }

    public boolean delRoleInfoService(String roleID) {
        Role dbRole = roleDao.getRoleByID(roleID);
        if (dbRole != null && checkRole4User(dbRole)) {

            roleDao.delete(dbRole);
            return true;
        }
        return false;
    }

    /**
     * 检验该角色是否可以删除(该角色和和其所有子角色是否关联用户信息)
     * 
     * @param role 检验的角色
     * @return true-未关联用户信息(可以删除) false-关联用户信息(不能删除)
     */
    private boolean checkRole4User(Role role) {
        List<String> roleIDList = new ArrayList<String>();

        getSubRoleID(role, roleIDList);

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

            Set<Role> subRoleSet = role.getSubRoleSet();
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
            TreeNode root = new TreeNode();
            
            root.setId(dbRole.getId());
            root.setText();
            root.setparentNodeID
            root.seticon
            // TODO Auto-generated method stub
            return root;
        }

        return null;
    }

    public boolean modRoleInfoService(Role role) {
        if (StringUtils.isNotBlank(role.getName())) {
            Role dbRole = roleDao.getRoleByID(role.getId());

            if (dbRole != null) {
                dbRole.setName(role.getName());
                dbRole.setDetail(role.getDetail());

                roleDao.save(dbRole);
                return true;
            }
        }
        return false;
    }

    // ---------------------------------------------------------------------------
    // DAO
    // ---------------------------------------------------------------------------
    @Autowired
    private RoleDao roleDao;

    public RoleDao getRoleDao() {
        return roleDao;
    }

    public void setRoleDao(RoleDao roleDao) {
        this.roleDao = roleDao;
    }
}
