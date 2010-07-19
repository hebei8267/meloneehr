package com.jquery.core.modules.grid;

import java.util.List;

/**
 * DataGridList对象
 * 
 * @since JDK1.5
 */
@SuppressWarnings("unchecked")
public class DataGridList {
    /** 列表数据总行数 */
    private int total;

    /** 要显示的列表数据 */

    private List rows;

    public DataGridList(int total, List rows) {
        this.total = total;
        this.rows = rows;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List getRows() {
        return rows;
    }

    public void setRows(List rows) {
        this.rows = rows;
    }
}
