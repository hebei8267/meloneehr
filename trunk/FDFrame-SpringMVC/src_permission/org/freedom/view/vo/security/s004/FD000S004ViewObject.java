/*
 * Copyright 2008 by hebei, All rights reserved.
 */
package org.freedom.view.vo.security.s004;

import java.util.ArrayList;
import java.util.List;

import org.freedom.core.view.vo.jsp.AbstractViewObject;
import org.freedom.core.view.vo.jsp.LabelValueBean;
import org.freedom.entity.ui.MenuNodeType;

/**
 * 菜单树管理界面ViewObject
 * 
 * @author 何贝
 * @since JDK1.5
 */
public class FD000S004ViewObject extends AbstractViewObject {

    private static final long serialVersionUID = -3699212498804881787L;
    /** 节点编号 */
    private String id;

    /** 节点内容 */
    private String text;

    /** 节点类型 */
    private String uiNodeType;

    /** 默认权限 "true"无访问限制 "false"有访问限制 */
    private Boolean defaultPermit = Boolean.TRUE;

    /** 节点显示位置 */
    private String uiNodeIndex;

    /** 页面迁移内容 */
    private String actionContent;

    /** 节点类型列表 */
    private List<LabelValueBean> nodeTypeList = new ArrayList<LabelValueBean>();

    public FD000S004ViewObject() {
        setUiNodeType(MenuNodeType.NONE_NODE_TYPE);
    }

    public FD000S004ViewObject(List<MenuNodeType> _menuNodeTypeList) {
        setUiNodeType(MenuNodeType.NONE_NODE_TYPE);

        for (MenuNodeType menuNodeType : _menuNodeTypeList) {
            nodeTypeList.add(new LabelValueBean(menuNodeType.getName(), menuNodeType.getSlaveID()));
        }
    }

    /**
     * 取得节点编号
     * 
     * @return 节点编号
     */
    public String getId() {
        return id;
    }

    /**
     * 设置节点编号
     * 
     * @param id 节点编号
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * 取得节点内容
     * 
     * @return 节点内容
     */
    public String getText() {
        return text;
    }

    /**
     * 设置节点内容
     * 
     * @param text 节点内容
     */
    public void setText(String text) {
        this.text = text;
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
     * @param uiNodeType 节点类型
     */
    public void setUiNodeType(String uiNodeType) {
        this.uiNodeType = uiNodeType;
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
}
