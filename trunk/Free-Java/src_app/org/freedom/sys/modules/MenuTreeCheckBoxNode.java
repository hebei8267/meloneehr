/*
 * Copyright 2009 by hebei, All rights reserved.
 */
package org.freedom.sys.modules;


/**
 * 菜单树节点显示对象(包含checkbox)
 * 
 * @author 何贝
 * @since JDK1.5
 */
public class MenuTreeCheckBoxNode extends MenuTreeNode {
    /**
     * 
     */
    private static final long serialVersionUID = -496794610612731666L;

    /** 是否选中状态 */
    private boolean checked = true;

    /**
     * 取得是否选中状态
     * 
     * @return 是否选中状态
     */
    public boolean isChecked() {
        return checked;
    }

    /**
     * 设置是否选中状态
     * 
     * @param checked 是否选中状态
     */
    public void setChecked(boolean checked) {
        this.checked = checked;
    }
}
