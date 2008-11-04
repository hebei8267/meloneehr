/*
 * Copyright 2008 by hebei, All rights reserved.
 */
package org.freedom.services.ui.impl;

import java.util.ArrayList;
import java.util.List;

import org.freedom.dao.security.RoleDao;
import org.freedom.dao.ui.MenuNodeDao;
import org.freedom.dao.ui.MenuNodePermitDao;
import org.freedom.entity.security.Role;
import org.freedom.entity.ui.MenuNode;
import org.freedom.entity.ui.MenuNodePermit;
import org.freedom.services.ui.IMenuNodePermitService;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * 系统菜单树访问权限相关服务
 * 
 * @author 何贝
 * @since JDK1.5
 */
@Component("menuNodePermitService")
@Scope("prototype")
public class MenuNodePermitServiceImpl implements IMenuNodePermitService {
    // ---------------------------------------------------------------------------
    // DAO
    // ---------------------------------------------------------------------------
    private MenuNodePermitDao menuNodePermitDao = null;
    private MenuNodeDao menuNodeDao = null;
    private RoleDao roleDao = null;

    public MenuNodePermitDao getMenuNodePermitDao() {
        return menuNodePermitDao;
    }

    public void setMenuNodePermitDao(MenuNodePermitDao menuNodePermitDao) {
        this.menuNodePermitDao = menuNodePermitDao;
    }

    public MenuNodeDao getMenuNodeDao() {
        return menuNodeDao;
    }

    public void setMenuNodeDao(MenuNodeDao menuNodeDao) {
        this.menuNodeDao = menuNodeDao;
    }

    public RoleDao getRoleDao() {
        return roleDao;
    }

    public void setRoleDao(RoleDao roleDao) {
        this.roleDao = roleDao;
    }

    // ---------------------------------------------------------------------------
    // 接口实现
    // ---------------------------------------------------------------------------

    public void addMenuNodePermit_Service(String menuNodeID, List<Role> roleList) {
        // 菜单节点对象
        MenuNode menuNode = menuNodeDao.getMenuNodeByID(menuNodeID);

        for (Role _role : roleList) {

            MenuNodePermit existObj = menuNodePermitDao.getMenuNodePermitByRoleIDAndMenuNodeID(_role.getId(),
                    menuNodeID);
            if (existObj != null) {// 要添加的对象已经存在
                continue;
            }

            // 登录用户角色可访问菜单树结点权限
            MenuNodePermit rolePermit = new MenuNodePermit();
            rolePermit.setMenuNode(menuNode);
            // 角色对象
            Role role = roleDao.getRoleByID(_role.getId());
            rolePermit.setRole(role);

            menuNodePermitDao.save(rolePermit);
        }
    }

    public boolean checkUserAccessMenuNodePermit_Service(String userID, String roleID, String menuNodeID) {
        if (Role.ADMIN_ROLE_ID.equals(roleID)) {// 系统管理员ID
            return true;
        }
        List<String> nodeList = menuNodePermitDao.getMenuNodePermitListByUserID(userID);
        if (nodeList.contains(menuNodeID)) {
            return true;
        }
        return false;
    }

    public int delMenuNodePermit_Service(String menuNodeID, List<Role> roleList) {
        String[] roleIDs = new String[roleList.size()];
        for (int i = 0; i < roleList.size(); i++) {
            roleIDs[i] = roleList.get(i).getId();
        }
        return menuNodePermitDao.delRoleMenuNodePermitByMenuNodeID(menuNodeID, roleIDs);
    }

    public List<Role> getAccessMenuNodePermitRoleInfoList_Service(String menuNodeID) {
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

}
