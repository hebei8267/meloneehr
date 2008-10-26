/*
 * Copyright 2008 by hebei, All rights reserved.
 */
package org.freedom.view.vo.security;

import java.util.ArrayList;
import java.util.List;

import org.freedom.core.view.vo.AbstractViewObject;
import org.freedom.core.view.vo.UIMenuTreeNode;

/**
 * 工作区主界面ViewObject
 * 
 * @author 何贝
 * @since JDK1.5
 */
public class FD000S003ViewObject extends AbstractViewObject {

    private static final long serialVersionUID = 131598164708775825L;

    /**
     * 导航区列表
     */
    private List<UIMenuTreeNode> shipAreaList = new ArrayList<UIMenuTreeNode>();

    /**
     * 取得导航区列表
     * 
     * @return 导航区列表
     */
    public List<UIMenuTreeNode> getShipAreaList() {
        return shipAreaList;
    }

    /**
     * 设置导航区列表
     * 
     * @param shipAreaList 导航区列表
     */
    public void setShipAreaList(List<UIMenuTreeNode> shipAreaList) {
        this.shipAreaList = shipAreaList;
    }
}
