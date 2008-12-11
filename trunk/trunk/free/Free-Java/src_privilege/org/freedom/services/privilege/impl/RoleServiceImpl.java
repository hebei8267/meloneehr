/*
 * Copyright 2008 by hebei, All rights reserved.
 */
package org.freedom.services.privilege.impl;

import org.apache.commons.lang.StringUtils;
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
@Component("roleServiceImpl")
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
        // TODO Auto-generated method stub
        return false;
    }

    public boolean getAllRoleInfoTreeService() {
        // TODO Auto-generated method stub
        return false;
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
