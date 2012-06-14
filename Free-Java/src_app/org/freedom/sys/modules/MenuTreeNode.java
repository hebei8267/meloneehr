/*
 * Copyright 2008 by hebei, All rights reserved.
 */
package org.freedom.sys.modules;

import org.freedom.entity.ui.MenuNodeType;
import org.freedom.sys.SysConstant;

/**
 * 菜单树节点显示对象(不包含checkbox)
 * 
 * @author 何贝
 * @since JDK1.5
 */
public class MenuTreeNode extends TreeNode {

    /**
     * 
     */
    private static final long serialVersionUID = -8885568003528855747L;
    /** 页面迁移内容 */
    private String actionContent;

    /** 节点类型 */
    private String uiNodeType;

    /** 节点类型名称 */
    private String uiNodeTypeName;

    /** 默认权限 "true"无访问限制 "false"有访问限制 */
    private Boolean defaultPermit = Boolean.TRUE;

    /** 节点显示位置 */
    private String uiNodeIndex;

    public MenuTreeNode() {

    }

    /**
     * 取得页面迁移内容
     * 
     * @return 页面迁移内容
     */
    public String getActionContent() {
        return actionContent;
    }

    /**
     * 设置页面迁移内容
     * 
     * @param actionContent 页面迁移内容
     */
    public void setActionContent(String actionContent) {
        this.actionContent = actionContent;
    }

    /**
     * 取得节点类型
     * 
     * @return 节点类型
     */
    public String getUiNodeType() {
        return uiNodeType;
    }

    /**
     * 设置节点类型
     * 
     * @param nodeType 节点类型
     */
    public void setUiNodeType(String nodeType) {
        //TODO hebei
        if (MenuNodeType.NONE_NODE_TYPE.equals(nodeType)) {
            this.uiNodeTypeName = "未定义";
        } else if (MenuNodeType.AREA_NODE_TYPE.equals(nodeType)) {
            this.uiNodeTypeName = "导航条";
        } else if (MenuNodeType.FOLDER_NODE_TYPE.equals(nodeType)) {
            this.uiNodeTypeName = "文件夹";
        } else if (MenuNodeType.LEAF_NODE_TYPE.equals(nodeType)) {
            this.uiNodeTypeName = "叶节点";
        }
        if (MenuNodeType.AREA_NODE_TYPE.equals(nodeType)) {
            setIcon(SysConstant.WEB_PROJECT_NAME + "/images/area.gif");
        }
        this.uiNodeType = nodeType;
    }

    /**
     * 取得节点类型名称
     * 
     * @return 节点类型名称
     */
    public String getUiNodeTypeName() {
        return uiNodeTypeName;
    }

    /**
     * 设置节点类型名称
     * 
     * @param uiNodeTypeName 节点类型名称
     */
    public void setUiNodeTypeName(String uiNodeTypeName) {
        this.uiNodeTypeName = uiNodeTypeName;
    }

    /**
     * 取得默认权限 "true"无访问限制 "false"有访问限制
     * 
     * @return 默认权限 "true"无访问限制 "false"有访问限制
     */
    public Boolean getDefaultPermit() {
        return defaultPermit;
    }

    /**
     * 设置默认权限 "true"无访问限制 "false"有访问限制
     * 
     * @param defaultPermit 默认权限 "true"无访问限制 "false"有访问限制
     */
    public void setDefaultPermit(Boolean defaultPermit) {
        this.defaultPermit = defaultPermit;
    }

    /**
     * 取得节点显示位置
     * 
     * @return 节点显示位置
     */
    public String getUiNodeIndex() {
        return uiNodeIndex;
    }

    /**
     * 设置节点显示位置
     * 
     * @param nodeIndex 节点显示位置
     */
    public void setUiNodeIndex(String nodeIndex) {
        this.uiNodeIndex = nodeIndex;
    }

}
