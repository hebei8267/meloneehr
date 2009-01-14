/*
 * Copyright 2009 by hebei, All rights reserved.
 */
package org.freedom.view.domain.system;

import org.freedom.core.domain.TreeNode;
import org.freedom.view.SysConstant;

/**
 * 角色树节点
 * 
 * @author 何贝
 * @since JDK1.5
 */
public class RoleTreeNodeViewObject extends TreeNode {
    /**
     * 
     */
    private static final long serialVersionUID = -1987051473474665434L;

    public RoleTreeNodeViewObject() {

        setIcon("/" + SysConstant.WEB_PROJECT_NAME + "/images/role.gif");

    }
}
