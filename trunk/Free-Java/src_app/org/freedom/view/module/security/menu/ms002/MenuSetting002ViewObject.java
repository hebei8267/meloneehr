/*
 * Copyright 2009 by hebei, All rights reserved.
 */
package org.freedom.view.module.security.menu.ms002;

import java.util.ArrayList;
import java.util.List;

import org.freedom.entity.ui.MenuNodeType;
import org.freedom.sys.view.AbstractViewObject;
import org.freedom.web.modules.LabelValueBean;

/**
 * @author 何贝
 * @since JDK1.5
 */
public class MenuSetting002ViewObject extends AbstractViewObject {

    /**
     * 
     */
    private static final long serialVersionUID = -594686947001090323L;
    /** 父菜单节点编号 */
    private String parentNodeID;
    /** 父菜单节点名称 */
    private String parentNodeTxt;
    /** 父菜单节点类型 */
    private String parentNodeTypeID;
    /** 节点名称 */
    private String nodeTxt;
    /** 节点类型 */
    private String nodeType = MenuNodeType.NONE_NODE_TYPE;
    /** 节点类型列表 */
    private List<LabelValueBean> nodeTypeList = new ArrayList<LabelValueBean>();
    /** Action URL */
    private String actionContent;
    /** 默认访问权限 true-无访问限制 false-有访问限制 */
    private boolean defaultPermit = true;
    /** 继承访问权限 */
    private boolean inheritFlg = false;
    /** 显示位置 */
    private String showIndex;

    /**
     * 取得父菜单节点编号
     * 
     * @return 父菜单节点编号
     */
    public String getParentNodeID() {
        return parentNodeID;
    }

    /**
     * 设置父菜单节点编号
     * 
     * @param parentNodeID 父菜单节点编号
     */
    public void setParentNodeID(String parentNodeID) {
        this.parentNodeID = parentNodeID;
    }

    /**
     * 取得父菜单节点名称
     * 
     * @return 父菜单节点名称
     */
    public String getParentNodeTxt() {
        return parentNodeTxt;
    }

    /**
     * 设置父菜单节点名称
     * 
     * @param parentNodeTxt 父菜单节点名称
     */
    public void setParentNodeTxt(String parentNodeTxt) {
        this.parentNodeTxt = parentNodeTxt;
    }

    /**
     * 取得父菜单节点类型
     * 
     * @return 父菜单节点类型
     */
    public String getParentNodeTypeID() {
        return parentNodeTypeID;
    }

    /**
     * 设置父菜单节点类型
     * 
     * @param parentNodeTypeID 父菜单节点类型
     */
    public void setParentNodeTypeID(String parentNodeTypeID) {
        this.parentNodeTypeID = parentNodeTypeID;
    }

    /**
     * 取得节点名称
     * 
     * @return 节点名称
     */
    public String getNodeTxt() {
        return nodeTxt;
    }

    /**
     * 设置节点名称
     * 
     * @param nodeTxt 节点名称
     */
    public void setNodeTxt(String nodeTxt) {
        this.nodeTxt = nodeTxt;
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
     * 设置节点类型列表
     * 
     * @param nodeTypeList 节点类型列表
     */
    public void setNodeTypeInfoList(List<MenuNodeType> menuNodeTypeList) {
        for (MenuNodeType menuNodeType : menuNodeTypeList) {
            nodeTypeList.add(new LabelValueBean(menuNodeType.getName(), menuNodeType.getSlaveID()));
        }
    }

    /**
     * 取得Action URL
     * 
     * @return Action URL
     */
    public String getActionContent() {
        return actionContent;
    }

    /**
     * 设置Action URL
     * 
     * @param actionContent Action URL
     */
    public void setActionContent(String actionContent) {
        this.actionContent = actionContent;
    }

    /**
     * 取得默认访问权限 true-无访问限制 false-有访问限制
     * 
     * @return 默认访问权限 true-无访问限制 false-有访问限制
     */
    public boolean getDefaultPermit() {
        return defaultPermit;
    }

    /**
     * 设置默认访问权限 true-无访问限制 false-有访问限制
     * 
     * @param defaultPermit 默认访问权限 true-无访问限制 false-有访问限制
     */
    public void setDefaultPermit(boolean defaultPermit) {
        this.defaultPermit = defaultPermit;
    }

    /**
     * 取得继承访问权限
     * 
     * @return 继承访问权限
     */
    public boolean getInheritFlg() {
        return inheritFlg;
    }

    /**
     * 设置继承访问权限
     * 
     * @param inheritFlg 继承访问权限
     */
    public void setInheritFlg(boolean inheritFlg) {
        this.inheritFlg = inheritFlg;
    }

    /**
     * 取得显示位置
     * 
     * @return 显示位置
     */
    public String getShowIndex() {
        return showIndex;
    }

    /**
     * 设置显示位置
     * 
     * @param showIndex 显示位置
     */
    public void setShowIndex(String showIndex) {
        this.showIndex = showIndex;
    }

}
