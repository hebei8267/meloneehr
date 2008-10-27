/*
 * Copyright 2008 by hebei, All rights reserved.
 */
package org.freedom.view.vo.security;

import java.util.ArrayList;
import java.util.List;

import org.freedom.core.view.vo.AbstractViewObject;
import org.freedom.core.view.vo.LabelValueBean;

/**
 * 菜单树管理界面ViewObject
 * 
 * @author 何贝
 * @since JDK1.5
 */
public class FD000S004ViewObject extends AbstractViewObject {

    private static final long serialVersionUID = -3699212498804881787L;
    /** 节点编号 */
    private String id = "id";

    /** 节点类型 */
    private String nodeType = "nodeType";

    /** 节点类型列表 */
    private List<LabelValueBean> nodeTypeList = new ArrayList<LabelValueBean>();

    /** 节点名称 */
    private String nodeTxt = "nodeTxt";

    /** 默认权限 "true"无访问限制 "false"有访问限制 */
    private boolean defaultPermit = false;

    /** 页面迁移内容 */
    private String actionContent = "actionContent";

    /** 显示位置 */
    private Integer index = 0;

    /** 父节点ID */
    private String parentNodeID;

    public FD000S004ViewObject() {
        nodeTypeList.add(new LabelValueBean("&nbsp;", ""));
        nodeTypeList.add(new LabelValueBean("1", "1"));
        nodeTypeList.add(new LabelValueBean("2", "2"));
        nodeTypeList.add(new LabelValueBean("3", "3"));
        nodeTypeList.add(new LabelValueBean("4", "4"));
        nodeTypeList.add(new LabelValueBean("nodeType", "nodeType"));

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
     * 取得默认权限 "true"无访问限制 "false"有访问限制
     * 
     * @return 默认权限 "true"无访问限制 "false"有访问限制
     */
    public boolean getDefaultPermit() {
        return defaultPermit;
    }

    /**
     * 设置默认权限 "true"无访问限制 "false"有访问限制
     * 
     * @param defaultPermit 默认权限 "true"无访问限制 "false"有访问限制
     */
    public void setDefaultPermit(boolean defaultPermit) {
        this.defaultPermit = defaultPermit;
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
     * 取得显示位置
     * 
     * @return 显示位置
     */
    public Integer getIndex() {
        return index;
    }

    /**
     * 设置显示位置
     * 
     * @param index 显示位置
     */
    public void setIndex(Integer index) {
        this.index = index;
    }

    /**
     * 取得父节点ID
     * 
     * @return 父节点ID
     */
    public String getParentNodeID() {
        return parentNodeID;
    }

    /**
     * 设置父节点ID
     * 
     * @param parentNodeID 父节点ID
     */
    public void setParentNodeID(String parentNodeID) {
        this.parentNodeID = parentNodeID;
    }
}
