/*
 * Copyright 2009 by hebei, All rights reserved.
 */
package org.freedom.services.permit.impl;

import java.util.ArrayList;
import java.util.List;

import org.freedom.dao.common.RoleDao;
import org.freedom.dao.ui.MenuNodePermitDao;
import org.freedom.entity.common.Role;
import org.freedom.entity.ui.MenuNodePermit;
import org.freedom.services.permit.IMenuNodePermitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * @author 何贝
 * @since JDK1.5
 */
@Component("menuNodePermitService")
@Scope("prototype")
public class MenuNodePermitServiceImpl implements IMenuNodePermitService {
    public boolean checkMenuNodePermitService(String userID, String roleID, String nodeID) {
        if (Role.ADMIN_ROLE_ID.equals(roleID)) {// 系统管理员ID
            return true;
        }
        MenuNodePermit permit = menuNodePermitDao.getMenuNodePermitByRoleIDAndMenuNodeID(roleID, nodeID);
        if (permit != null) {
            return true;
        }
        return false;
    }

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

    // ---------------------------------------------------------------------------
    // DAO
    // ---------------------------------------------------------------------------
    @Autowired
    private MenuNodePermitDao menuNodePermitDao;
    @Autowired
    private RoleDao roleDao;

    public MenuNodePermitDao getMenuNodePermitDao() {
        return menuNodePermitDao;
    }

    public void setMenuNodePermitDao(MenuNodePermitDao menuNodePermitDao) {
        this.menuNodePermitDao = menuNodePermitDao;
    }

    public RoleDao getRoleDao() {
        return roleDao;
    }

    public void setRoleDao(RoleDao roleDao) {
        this.roleDao = roleDao;
    }

}
