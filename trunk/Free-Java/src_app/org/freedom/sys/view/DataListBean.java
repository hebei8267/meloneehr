/*
 * Copyright 2008 by hebei, All rights reserved.
 */
package org.freedom.sys.view;

import java.util.List;


/**
 * 公共DataList对象基类
 * 
 * @author 何贝
 * @since JDK1.5
 */
public class DataListBean<T> extends JosnViewObject {

    private static final long serialVersionUID = 5282109040660552471L;
    /** 列表大小 */
    private int totalCount;
    /** 数据列表 */
    private List<T> dataList;

    public DataListBean() {

    }

    /**
     * 取得列表大小
     * 
     * @return 列表大小
     */
    public int getTotalCount() {
        return totalCount;
    }

    /**
     * 设置列表大小
     * 
     * @param totalCount 列表大小
     */
    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    /**
     * 取得数据列表
     * 
     * @return 数据列表
     */
    public List<T> getDataList() {
        return dataList;
    }

    /**
     * 设置数据列表
     * 
     * @param dataList 数据列表
     */
    public void setDataList(List<T> dataList) {
        if (dataList != null) {
            // List大小
            setTotalCount(dataList.size());
        } else {
            // List大小
            setTotalCount(0);
        }
        this.dataList = dataList;
    }
}