/*
 * Copyright 2009 by hebei, All rights reserved.
 */
package org.freedom.sys;

/**
 * @author 何贝
 * @since JDK1.5
 */
public class SysConstant {
    // 用户session key
    public static final String USER_INFO = "userInfo";
    // 添加数据是默认用户ID
    public final static String SYS_USER_ID = "sys";
    // AjaxViewAction默认java文件名后缀
    public final static String AJAX_VIEW_ACTION = "AjaxViewAction";
    // 用户第一次请求faces/ajax时设置
    // org.freedom.sys.interceptor.HttpAccessInterceptor
    public static String WEB_PROJECT_NAME = "";
    public final static String ROLE_TREE_ROOT_NAME = "角色树更节点";
    public final static String MENU_NODE_TREE_ROOT_NAME = "系统菜单树根节点";
    // PostgreSQL DB
    public final static String DB_SYS_DATE_YMD = "to_char(now(), 'yyyymmdd')";
    // TODO hebei 公共js修改后,该处也要修改
    public final static String EXTJS_GRID_AJAX_LOAD_EXCEPTION = " function() { showMessageBox(getErrorMsg_AM001()); } ";
    public final static String EXTJS_GRID_ROW_NUMBERER_HEADER = "序号";
}
