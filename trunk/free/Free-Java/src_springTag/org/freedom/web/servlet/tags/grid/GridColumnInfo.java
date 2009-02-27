/*
 * Copyright 2009 by hebei, All rights reserved.
 */
package org.freedom.web.servlet.tags.grid;

/**
 * @author 何贝
 * @since JDK1.5
 */
public class GridColumnInfo {
    private String id;
    private String header;
    private String sortable = "false";
    private String dataIndex;
    private String width;
    private String align;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public String getSortable() {
        return sortable;
    }

    public void setSortable(String sortable) {
        this.sortable = sortable;
    }

    public String getDataIndex() {
        return dataIndex;
    }

    public void setDataIndex(String dataIndex) {
        this.dataIndex = dataIndex;
    }

    public String getWidth() {
        return width;
    }

    public void setWidth(String width) {
        this.width = width;
    }

    public String getAlign() {
        return align;
    }

    public void setAlign(String align) {
        this.align = align;
    }
}
