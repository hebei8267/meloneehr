/*
 * Copyright 2009 by hebei, All rights reserved.
 */
package org.freedom.web.servlet.tags.grid;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.util.Assert;
import org.springframework.web.servlet.tags.form.AbstractFormTag;
import org.springframework.web.servlet.tags.form.TagWriter;

/**
 * 列表结构子项目
 * 
 * @author 何贝
 * @since JDK1.5
 */
public class ExtjsGridColumnTag extends AbstractFormTag {

    /**
     * 
     */
    private static final long serialVersionUID = 2357992482865489909L;

    private String id;
    private String header;
    // TODO
    private String sortable = "false";
    private String dataIndex;
    private String width;
    private String align;

    public String getId() {
        Assert.hasText(id, "'id' must not be empty");
        return id;
    }

    public void setId(String id) {
        Assert.hasText(header, "'header' must not be empty");
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

    @Override
    protected int writeTagContent(TagWriter tagWriter) throws JspException {
        return SKIP_BODY;
    }

    /**
     * 拷贝列表结构子项目信息
     * 
     * @return
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     */
    private GridColumnInfo copyGridColumnInfo() throws IllegalAccessException, InvocationTargetException {
        GridColumnInfo _copy = new GridColumnInfo();
        BeanUtils.copyProperties(_copy, this);
        return _copy;
    }

    /*
     * (non-Javadoc)
     * 
     * @see javax.servlet.jsp.tagext.TagSupport#doEndTag()
     */
    @Override
    public int doEndTag() throws JspException {

        // 保存GridColumn信息
        try {
            saveGridColumnInfo();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return EVAL_PAGE;
    }

    /**
     * 保存GridColumn信息
     * 
     * @param columnInfo
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     */
    @SuppressWarnings("unchecked")
    private void saveGridColumnInfo() throws IllegalAccessException, InvocationTargetException {
        List<GridColumnInfo> gridColumnInfoList = (List<GridColumnInfo>) this.pageContext
                .getAttribute(ExtjsGridTag.GRID_COLUMN_PAGE_ATTRIBUTE);
        if (gridColumnInfoList == null) {
            gridColumnInfoList = new ArrayList<GridColumnInfo>();
            this.pageContext.setAttribute(ExtjsGridTag.GRID_COLUMN_PAGE_ATTRIBUTE, gridColumnInfoList, PageContext.PAGE_SCOPE);
        }

        gridColumnInfoList.add(copyGridColumnInfo());

    }
}
