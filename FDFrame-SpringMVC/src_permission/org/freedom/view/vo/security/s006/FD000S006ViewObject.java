/*
 * Copyright 2008 by hebei, All rights reserved.
 */
package org.freedom.view.vo.security.s006;

import java.util.ArrayList;
import java.util.List;

import org.freedom.core.view.vo.jsp.AbstractViewObject;
import org.freedom.core.view.vo.jsp.LabelValueBean;
import org.freedom.entity.ui.MenuNodeType;

/**
 * 添加菜单树节点界面ViewObject
 * 
 * @author 何贝
 * @since JDK1.5
 */
public class FD000S006ViewObject extends AbstractViewObject {

    private static final long serialVersionUID = -4983774123239417523L;
    /** 节点内容 */
    private String nodeText;
    /** 节点类型 */
    private String nodeType = MenuNodeType.NONE_NODE_TYPE;
    /** 默认权限 "true"无访问限制 "false"有访问限制 */
    private Boolean defaultPermit = Boolean.TRUE;
    /** 节点显示位置 */
    private String nodeIndex;

    /** 页面迁移内容 */
    private String actionContent;

    /** 节点类型列表 */
    private List<LabelValueBean> nodeTypeList = new ArrayList<LabelValueBean>();

    public FD000S006ViewObject() {

    }

    public FD000S006ViewObject(List<MenuNodeType> _menuNodeTypeList) {
        for (MenuNodeType menuNodeType : _menuNodeTypeList) {
            nodeTypeList.add(new LabelValueBean(menuNodeType.getName(), menuNodeType.getSlaveID()));
        }
    }

    /**
     * 取得节点内容
     * 
     * @return 节点内容
     */
    public String getNodeText() {
        return nodeText;
    }

    /**
     * 设置节点内容
     * 
     * @param nodeText 节点内容
     */
    public void setNodeText(String nodeText) {
        this.nodeText = nodeText;
    }

    /**
     * 取得节点类型
     * 
     * @return 节点类型
     */
    public String getNodeType() {
        return nodeType;
    }

    /**
     * 设置节点类型
     * 
     * @param nodeType 节点类型
     */
    public void setNodeType(String nodeType) {
        this.nodeType = nodeType;
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
    public String getNodeIndex() {
        return nodeIndex;
    }

    /**
     * 设置节点显示位置
     * 
     * @param nodeIndex 节点显示位置
     */
    public void setNodeIndex(String nodeIndex) {
        this.nodeIndex = nodeIndex;
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
     * 取得节点类型列表
     * 
     * @return 节点类型列表
     */
    public List<LabelValueBean> getNodeTypeList() {
        return nodeTypeList;
    }

    /**
     * 设置节点类型列表
     * 
     * @param nodeTypeList 节点类型列表
     */
    public void setNodeTypeList(List<LabelValueBean> nodeTypeList) {
        this.nodeTypeList = nodeTypeList;
    }
}
