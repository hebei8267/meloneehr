/*
 * Copyright 2008 by hebei, All rights reserved.
 */
package org.freedom.entity.ui;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

import org.freedom.entity.MasterEntityBean;

/**
 * 菜单树结点类型
 * 
 * @author 何贝
 * @since JDK1.5
 */
@Entity
@DiscriminatorValue(value = "MenuNodeType")
@NamedQueries( {
        @NamedQuery(name = "MenuNodeType.getMenuNodeTypeByID", query = "select obj from MenuNodeType obj where obj.masterID = ? and obj.slaveID = ? "),
        @NamedQuery(name = "MenuNodeType.getMenuNodeTypeList", query = "select obj from MenuNodeType obj where obj.masterID = ?  order by obj.slaveID desc ") })
public class MenuNodeType extends MasterEntityBean {

    private static final long serialVersionUID = 3504185821760440924L;

    public final static String MASTER_ID = "0001";
    /**
     * 节点类型--未定义
     */
    public final static String NONE_NODE_TYPE = "none";
    /**
     * 节点类型--导航条
     */
    public final static String AREA_NODE_TYPE = "area";
    /**
     * 节点类型--文件夹
     */
    public final static String FOLDER_NODE_TYPE = "folder";
    /**
     * 节点类型--叶节点
     */
    public final static String LEAF_NODE_TYPE = "node";

    public MenuNodeType() {

    }
}
