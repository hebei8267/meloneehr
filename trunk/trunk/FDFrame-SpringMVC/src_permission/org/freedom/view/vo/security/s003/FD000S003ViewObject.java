/*
 * Copyright 2008 by hebei, All rights reserved.
 */
package org.freedom.view.vo.security.s003;

import java.util.ArrayList;
import java.util.List;

import org.freedom.core.view.vo.AbstractViewObject;
import org.freedom.core.view.vo.UITreeNode;

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
    private List<UITreeNode> shipAreaList = new ArrayList<UITreeNode>();

    /**
     * 取得导航区列表
     * 
     * @return 导航区列表
     */
    public List<UITreeNode> getShipAreaList() {
        return shipAreaList;
    }

    /**
     * 设置导航区列表
     * 
     * @param shipAreaList 导航区列表
     */
    public void setShipAreaList(List<UITreeNode> shipAreaList) {
        this.shipAreaList = shipAreaList;
    }
}
