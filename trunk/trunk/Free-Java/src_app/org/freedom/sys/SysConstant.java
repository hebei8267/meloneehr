/*
 * Copyright 2009 by hebei, All rights reserved.
 */
package org.freedom.sys;

/**
 * @author 何贝
 * @since JDK1.5
 */
public class SysConstant {
    // 用户请求index.faces时设置
    // org.freedom.view.action.normal.security.Security001NormalViewAction
    public static String WEB_PROJECT_NAME = "";
    public final static String ROLE_TREE_ROOT_NAME = "角色树更节点";
    public final static String MENU_NODE_TREE_ROOT_NAME = "系统菜单树根节点";
    // PostgreSQL DB
    public final static String DB_SYS_DATE_YMD = "to_char(now(), 'yyyymmdd')";
    // TODO hebei 公共js修改后,该处也要修改
    public final static String EXTJS_GRID_AJAX_LOAD_EXCEPTION = " function() { showMessageBox(getErrorMsg_AM001()); } ";
    public final static String EXTJS_GRID_ROW_NUMBERER_HEADER = "序号";
}
