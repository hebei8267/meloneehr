/*
 * Copyright 2008 by hebei, All rights reserved.
 */
package org.freedom.services.permit.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import org.freedom.core.domain.TreeNode;
import org.freedom.dao.ui.MenuNodeDao;
import org.freedom.dao.ui.MenuNodePermitDao;
import org.freedom.dao.ui.MenuNodeTypeDao;
import org.freedom.entity.common.Role;
import org.freedom.entity.ui.MenuNode;
import org.freedom.entity.ui.MenuNodeType;
import org.freedom.services.permit.IMenuNodeService;
import org.freedom.view.domain.system.MenuTreeNodeViewObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * 菜单树结点相关服务
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
    public int addMenuNodeInfoService(MenuNode menuNode, boolean inheritFlg) {
        if (StringUtils.isNotBlank(menuNode.getNodeTxt()) && !MenuNodeType.NONE_NODE_TYPE.equals((menuNode.getNodeType()))
                && StringUtils.isNotBlank(menuNode.getActionContent())) {
            if (StringUtils.isBlank(menuNode.getParentNodeID())) {// 父节点为空时默认添加到根节点
                menuNode.setParentNodeID(MenuNode.MENU_NODE_TREE_ROOT_ID);
            }

            MenuNode parentMenuNode = menuNodeDao.getMenuNodeByID(menuNode.getParentNodeID());
            if (parentMenuNode == null) {
                boolean _check = menuNodeTypeCheck(parentMenuNode, menuNode);
                // 子菜单节点类型检查
                if (_check) {
                    menuNode.setParentNode(parentMenuNode);
                    menuNode.setId(menuNodeDao.getMaxID());

                    parentMenuNode.addChildNode(menuNode);
                    // 添加菜单节点
                    menuNodeDao.save(menuNode);

                    if (inheritFlg) {// 继承权限
                        // TODO
                    }

                    return 0;
                } else {
                    return 2;
                }
            }
        }
        return 1;
    }

    /**
     * 子菜单节点类型检查
     * 
     * @param parentMenuNode 父菜单节点
     * @param childMenuNode 子菜单节点
     * @return 检查结果
     */
    private boolean menuNodeTypeCheck(MenuNode parentMenuNode, MenuNode childMenuNode) {

        // [根]节点下面只能是[导航条]节点
        if (MenuNodeType.ROOT_NODE_TYPE.equals(parentMenuNode.getNodeType())) {
            if (!MenuNodeType.AREA_NODE_TYPE.equals(childMenuNode.getNodeType())) {
                return false;
            }
        }
        // [导航条]节点下面只能是[文件夹]节点或者[叶节点]节点
        if (MenuNodeType.AREA_NODE_TYPE.equals(parentMenuNode.getNodeType())) {
            if (!(MenuNodeType.FOLDER_NODE_TYPE.equals(childMenuNode.getNodeType()) || MenuNodeType.LEAF_NODE_TYPE
                    .equals(childMenuNode.getNodeType()))) {
                return false;
            }
        }
        // [文件夹]节点下面只能是[叶节点]节点
        if (MenuNodeType.FOLDER_NODE_TYPE.equals(parentMenuNode.getNodeType())) {
            if (!MenuNodeType.LEAF_NODE_TYPE.equals(childMenuNode.getNodeType())) {
                return false;
            }
        }

        return true;
    }

    public int delMenuNodeInfoService(String menuNodeID, int dataVersion) {
        // 取得菜单树结点信息
        MenuNode dbMenuNode = menuNodeDao.getMenuNodeByID(menuNodeID);
        if (dbMenuNode == null || !dbMenuNode.getVersion().equals(dataVersion)) {
            return 1;
        }
        List<String> menuIDList = new ArrayList<String>();
        // 取得所有菜单ID
        getSubMenuID(dbMenuNode, menuIDList);

        // 删除菜单节点的访问权限
        menuNodePermitDao.delMenuNodePermitByMenuNodeID(menuIDList);
        // 删除菜单节点
        menuNodeDao.delete(dbMenuNode);
        return 0;
    }

    /**
     * 取得所有子菜单ID
     * 
     * @param menuNode 父菜单
     * @param menuIDList 保存菜单ID的容器
     */
    private void getSubMenuID(MenuNode menuNode, List<String> menuIDList) {
        if (menuNode != null) {
            menuIDList.add(menuNode.getId());

            for (MenuNode subNode : menuNode.getChildNodeList()) {

                if (subNode != null) {
                    getSubMenuID(subNode, menuIDList);
                }
            }
        }
    }

    public TreeNode getMenuNodeInfoTreeService(String userID, String roleID) {
        return getMenuNodeInfoTreeService(MenuNode.MENU_NODE_TREE_ROOT_ID, userID, roleID);
    }

    public TreeNode getMenuNodeInfoTreeService(String rootID, String userID, String roleID) {
        // 取得菜单树结点信息
        MenuNode dbNodeRoot = menuNodeDao.getMenuNodeByID(rootID);

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
                MenuTreeNodeViewObject menuTreeNode = new MenuTreeNodeViewObject();

                menuTreeNode.setId(dbNodeRoot.getId());
                menuTreeNode.setText(dbNodeRoot.getNodeTxt());
                menuTreeNode.setLeaf(MenuNodeType.LEAF_NODE_TYPE.equals(dbNodeRoot.getNodeType()));
                menuTreeNode.setActionContent(dbNodeRoot.getActionContent());
                menuTreeNode.setUiNodeType(dbNodeRoot.getNodeType());
                menuTreeNode.setDefaultPermit(dbNodeRoot.getDefaultPermit());
                menuTreeNode.setParentNodeID(dbNodeRoot.getParentNodeID());
                // 创建导航区菜单树结构
                buildMenuNodeInfoTree(menuTreeNode, dbNodeRoot, roleMenuNodePermitList, roleID);

                return menuTreeNode;
            }

        }
        return null;
    }

    public TreeNode getMenuNodeInfoTreeService(String rootID) {
        // 取得菜单树结点信息
        MenuNode dbNodeRoot = menuNodeDao.getMenuNodeByID(rootID);
        if (dbNodeRoot != null) {
            MenuTreeNodeViewObject menuTreeNode = new MenuTreeNodeViewObject();

            menuTreeNode.setId(dbNodeRoot.getId());
            menuTreeNode.setText(dbNodeRoot.getNodeTxt());
            menuTreeNode.setLeaf(MenuNodeType.LEAF_NODE_TYPE.equals(dbNodeRoot.getNodeType()));
            menuTreeNode.setActionContent(dbNodeRoot.getActionContent());
            menuTreeNode.setUiNodeType(dbNodeRoot.getNodeType());
            menuTreeNode.setDefaultPermit(dbNodeRoot.getDefaultPermit());
            menuTreeNode.setParentNodeID(dbNodeRoot.getParentNodeID());
            menuTreeNode.setVersion(dbNodeRoot.getVersion());

            // 创建导航区菜单树结构
            buildMenuNodeInfoTree(menuTreeNode, dbNodeRoot);

            return menuTreeNode;
        }
        return null;
    }

    /**
     * 构建整个菜单树结构
     * 
     * @param parentNode 父节点
     * @param dbParentNode 数据库中取得的菜单节点
     */
    private void buildMenuNodeInfoTree(MenuTreeNodeViewObject parentNode, MenuNode dbParentNode) {
        for (MenuNode dbNode : dbParentNode.getChildNodeList()) {
            if (dbNode != null) {
                MenuTreeNodeViewObject menuTreeNode = new MenuTreeNodeViewObject();

                menuTreeNode.setId(dbNode.getId());
                menuTreeNode.setText(dbNode.getNodeTxt());
                menuTreeNode.setLeaf(MenuNodeType.LEAF_NODE_TYPE.equals(dbNode.getNodeType()));
                menuTreeNode.setActionContent(dbNode.getActionContent());
                menuTreeNode.setUiNodeType(dbNode.getNodeType());
                menuTreeNode.setDefaultPermit(dbNode.getDefaultPermit());
                menuTreeNode.setParentNodeID(dbNode.getParentNodeID());
                menuTreeNode.setUiNodeIndex(dbNode.getIndex().toString());
                menuTreeNode.setVersion(dbNode.getVersion());

                parentNode.addChildren(menuTreeNode);

                buildMenuNodeInfoTree(menuTreeNode, dbNode);
            }
        }

    }

    /**
     * 构建整个菜单树结构
     * 
     * @param parentNode 父节点
     * @param dbParentNode 数据库中取得的菜单节点
     * @param roleMenuNodePermitList 访问的菜单树结点权限列表
     * @param roleID 角色ID
     */
    private void buildMenuNodeInfoTree(MenuTreeNodeViewObject parentNode, MenuNode dbParentNode,
            List<String> roleMenuNodePermitList, String roleID) {
        for (MenuNode dbNode : dbParentNode.getChildNodeList()) {

            if (dbNode != null) {
                // 系统管理员ID
                // 默认权限 "true"无访问限制 "false"有访问限制
                // 拥有访问权限
                if (Role.ADMIN_ROLE_ID.equals(roleID) || dbNode.getDefaultPermit()
                        || roleMenuNodePermitList.contains(dbNode.getId())) {
                    MenuTreeNodeViewObject menuTreeNode = new MenuTreeNodeViewObject();

                    menuTreeNode.setId(dbNode.getId());
                    menuTreeNode.setText(dbNode.getNodeTxt());
                    menuTreeNode.setLeaf(MenuNodeType.LEAF_NODE_TYPE.equals(dbNode.getNodeType()));
                    menuTreeNode.setActionContent(dbNode.getActionContent());
                    menuTreeNode.setUiNodeType(dbNode.getNodeType());
                    menuTreeNode.setDefaultPermit(dbNode.getDefaultPermit());
                    menuTreeNode.setParentNodeID(dbNode.getParentNodeID());

                    parentNode.addChildren(menuTreeNode);

                    buildMenuNodeInfoTree(menuTreeNode, dbNode, roleMenuNodePermitList, roleID);
                }
            }
        }
    }

    public boolean modMenuNodeInfoService(MenuNode menuNode) {
        if (StringUtils.isNotBlank(menuNode.getNodeTxt()) && !MenuNodeType.NONE_NODE_TYPE.equals((menuNode.getNodeType()))
                && StringUtils.isNotBlank(menuNode.getActionContent())) {
            MenuNode _dbMenuNode = menuNodeDao.getMenuNodeByID(menuNode.getId());

            _dbMenuNode.setNodeTxt(menuNode.getNodeTxt());
            _dbMenuNode.setDefaultPermit(menuNode.getDefaultPermit());
            _dbMenuNode.setActionContent(menuNode.getActionContent());
            // index变更
            if (menuNode.getIndex() != null && !_dbMenuNode.getIndex().equals(menuNode.getIndex())) {
                _dbMenuNode.setIndex(menuNode.getIndex());
                _dbMenuNode.updateNodeIndex();
            }

            menuNodeDao.save(_dbMenuNode);
            return true;

        }
        return false;
    }

    public List<TreeNode> getAllAreaMenuNodeService(String userID, String roleID) {
        MenuNode root = menuNodeDao.getMenuNodeByID(MenuNode.MENU_NODE_TREE_ROOT_ID);

        List<String> roleMenuNodePermitList = null;
        if (!Role.ADMIN_ROLE_ID.equals(roleID)) {// 不是系统管理员ID
            // 取得用户可访问的菜单树结点权限列表
            roleMenuNodePermitList = menuNodePermitDao.getMenuNodePermitListByUserID(userID);
        }

        return buildAllAreaMenuNodeList(root, roleID, roleMenuNodePermitList);
    }

    /**
     * 取得所有导航条类型树节点
     * 
     * hibernate lazy load原因
     * 
     * @param parentNode 父节点
     * @param roleID 用户角色ID
     * @param roleMenuNodePermitList 拥有访问权限列表
     * @return
     */
    private List<TreeNode> buildAllAreaMenuNodeList(MenuNode parentNode, String roleID, List<String> roleMenuNodePermitList) {
        List<TreeNode> _reList = new ArrayList<TreeNode>();
        for (MenuNode menuNode : parentNode.getChildNodeList()) {
            // 系统管理员ID
            // 默认权限 "true"无访问限制 "false"有访问限制
            // 拥有访问权限
            if (Role.ADMIN_ROLE_ID.equals(roleID) || menuNode.getDefaultPermit()
                    || roleMenuNodePermitList.contains(menuNode.getId())) {
                TreeNode uiNode = new TreeNode(menuNode.getId(), menuNode.getNodeTxt());

                _reList.add(uiNode);
            }
        }
        return _reList;
    }

    public List<MenuNodeType> getMenuNodeTypeInfoListService() {
        return menuNodeTypeDao.getAll();
    }

    // ---------------------------------------------------------------------------
    // DAO
    // ---------------------------------------------------------------------------
    @Autowired
    private MenuNodeDao menuNodeDao;
    @Autowired
    private MenuNodeTypeDao menuNodeTypeDao;
    @Autowired
    private MenuNodePermitDao menuNodePermitDao;

    public MenuNodeDao getMenuNodeDao() {
        return menuNodeDao;
    }

    public void setMenuNodeDao(MenuNodeDao menuNodeDao) {
        this.menuNodeDao = menuNodeDao;
    }

    public MenuNodeTypeDao getMenuNodeTypeDao() {
        return menuNodeTypeDao;
    }

    public void setMenuNodeTypeDao(MenuNodeTypeDao menuNodeTypeDao) {
        this.menuNodeTypeDao = menuNodeTypeDao;
    }

    public MenuNodePermitDao getMenuNodePermitDao() {
        return menuNodePermitDao;
    }

    public void setMenuNodePermitDao(MenuNodePermitDao menuNodePermitDao) {
        this.menuNodePermitDao = menuNodePermitDao;
    }

}
