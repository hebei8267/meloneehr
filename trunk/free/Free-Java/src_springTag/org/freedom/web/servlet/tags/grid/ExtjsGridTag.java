/*
 * Copyright 2009 by hebei, All rights reserved.
 */
package org.freedom.web.servlet.tags.grid;

import java.util.Iterator;
import java.util.List;

import javax.servlet.jsp.JspException;

import org.apache.commons.lang.StringUtils;
import org.freedom.view.SysConstant;
import org.freedom.web.servlet.tags.AbstractExtjsTag;
import org.springframework.util.Assert;
import org.springframework.web.servlet.tags.form.TagWriter;

/**
 * 普通列表结构
 * 
 * @author 何贝
 * @since JDK1.5
 */
public class ExtjsGridTag extends AbstractExtjsTag {

    /**
     * 
     */
    private static final long serialVersionUID = 99133210421619086L;

    public static final String LIST_VALUE_PAGE_ATTRIBUTE = "org.freedom.web.servlet.tags.grid.ExtjsGridTag.listValue";
    public static final String GRID_COLUMN_PAGE_ATTRIBUTE = "org.freedom.web.servlet.tags.grid.ExtjsGridTag.gridColumn";
    /** 数据url */
    protected String dataUrl;
    /** 数据斑马栏 */
    protected String stripeRows = "true";
    /** 表格title */
    protected String title;
    /** 显示自动编号栏 */
    protected String hasRowNumberer = "false";
    /** 是否为单选模式 */
    protected String singleSelect = "true";
    /** 选中记录js函数 */
    protected String rowselectFn;
    /** 放弃选择记录js函数 */
    protected String rowdeselectFn;
    /** 选中记录checkbox栏 */
    protected String checkboxSelection = "false";

    @Override
    protected int writeTagContent(TagWriter tagWriter) throws JspException {
        tagWriter.startTag("div");
        writeDefaultAttributes(tagWriter);
        tagWriter.endTag();

        return EVAL_BODY_INCLUDE;
    }

    /*
     * (non-Javadoc)
     * 
     * @see javax.servlet.jsp.tagext.TagSupport#doEndTag()
     */
    @Override
    public int doEndTag() throws JspException {

        // 添加Extjs脚本
        saveComponentScript(createComponentScript());
        return EVAL_PAGE;
    }

    @SuppressWarnings("unchecked")
    @Override
    protected String createComponentScript() throws JspException {
        List<GridColumnInfo> gridColumnInfoList = (List<GridColumnInfo>) this.pageContext
                .getAttribute(GRID_COLUMN_PAGE_ATTRIBUTE);

        if (gridColumnInfoList == null || gridColumnInfoList.size() == 0) {
            return "";
        }
        StringBuffer _sbuf = new StringBuffer();
        // 创建Ext.data.Record对象
        createExtDataRecordScript(gridColumnInfoList, _sbuf);
        // 创建Ext.data.Store对象
        createDataStoreScript(_sbuf);
        // 创建Ext.grid.CheckboxSelectionModel对象
        createExtGridSelectionModel(_sbuf);
        // 创建Ext.grid.GridPanel对象
        createExtGridPanel(gridColumnInfoList, _sbuf);
        // 清理gridColumn信息
        cleanGridColumnInfo();
        return _sbuf.toString();
    }

    /**
     * 清理gridColumn信息
     */
    private void cleanGridColumnInfo() {
        this.pageContext.removeAttribute(GRID_COLUMN_PAGE_ATTRIBUTE);
    }

    /**
     * 创建Ext.data.Record对象
     * 
     * @param gridColumnInfoList
     * @param _sbuf
     * @throws JspException
     */
    private void createExtDataRecordScript(List<GridColumnInfo> gridColumnInfoList, StringBuffer _sbuf) throws JspException {
        _sbuf.append(" var " + resolveId() + "Record = Ext.data.Record.create([ ");
        for (Iterator<GridColumnInfo> iterator = gridColumnInfoList.iterator(); iterator.hasNext();) {
            GridColumnInfo gridColumn = (GridColumnInfo) iterator.next();

            _sbuf.append(" {name: '" + gridColumn.getId() + "'}");
            if (iterator.hasNext()) {
                _sbuf.append(", ");
            }
        }
        _sbuf.append(" ]); ");
    }

    /**
     * 创建Ext.data.Store对象
     * 
     * @param _sbuf
     * @throws JspException
     */
    private void createDataStoreScript(StringBuffer _sbuf) throws JspException {
        _sbuf.append(" var " + resolveId() + "Store = new Ext.data.Store({ ");
        _sbuf.append(" id : '" + resolveId() + "Store', ");
        _sbuf.append(" proxy : new Ext.data.HttpProxy({ ");
        _sbuf.append(" url : '" + SysConstant.WEB_PROJECT_NAME + "/" + getDataUrl() + "', ");
        _sbuf.append(" method: 'POST' ");
        _sbuf.append(" }), ");
        _sbuf.append(" reader : new Ext.data.JsonReader({ ");
        _sbuf.append(" totalProperty: 'totalProperty', ");
        _sbuf.append(" root: 'dataList', ");
        _sbuf.append(" successProperty :'sessionTimeOut' ");
        _sbuf.append(" }, " + resolveId() + "Record), ");
        _sbuf.append(" listeners : { ");
        _sbuf.append(" loadexception : " + SysConstant.EXTJS_GRID_AJAX_LOAD_EXCEPTION);
        _sbuf.append(" } ");
        _sbuf.append(" }); ");
    }

    /**
     * 创建Ext.grid.CheckboxSelectionModel对象
     * 
     * @param _sbuf
     * @throws JspException
     */
    private void createExtGridSelectionModel(StringBuffer _sbuf) throws JspException {
        _sbuf.append(" var " + resolveId() + "SelectionModel = new Ext.grid.CheckboxSelectionModel({ ");
        _sbuf.append(" header : '', ");
        _sbuf.append(" singleSelect: " + getSingleSelect() + ", ");
        _sbuf.append(" listeners: { ");
        // 记录选中
        if (StringUtils.isNotBlank(getRowselectFn())) {
            _sbuf.append(" rowselect: " + getRowselectFn());
        }
        // 记录为选中
        if (StringUtils.isNotBlank(getRowdeselectFn())) {
            if (StringUtils.isNotBlank(getRowselectFn())) {
                _sbuf.append(",");
            }
            _sbuf.append(" rowdeselect: " + getRowdeselectFn());
        }
        _sbuf.append(" } ");
        _sbuf.append(" }); ");
    }

    /**
     * 创建Ext.grid.GridPanel对象
     * 
     * @param gridColumnInfoList
     * @param _sbuf
     * @throws JspException
     */
    @SuppressWarnings("unused")
    private void createExtGridPanel(List<GridColumnInfo> gridColumnInfoList, StringBuffer _sbuf) throws JspException {

        _sbuf.append(" var " + resolveId() + "Panel = new Ext.grid.GridPanel({ ");
        _sbuf.append(" store : " + resolveId() + "Store, ");
        _sbuf.append(" id : '" + resolveId() + "', ");
        _sbuf.append(" el : '" + resolveId() + "Div', ");
        _sbuf.append(" sm : " + resolveId() + "SelectionModel,");
        _sbuf.append(" columns : [ ");
        // 是否显示选中记录checkbox栏
        if (StringUtils.isNotBlank(checkboxSelection) && Boolean.TRUE.toString().equals(checkboxSelection)) {
            _sbuf.append(resolveId() + "SelectionModel, ");
        }
        // 是否显示自动编号栏
        if (StringUtils.isNotBlank(hasRowNumberer) && Boolean.TRUE.toString().equals(hasRowNumberer)) {
            _sbuf.append(" new Ext.grid.RowNumberer({ header : '" + SysConstant.EXTJS_GRID_ROW_NUMBERER_HEADER + "', width : 35 }),");
        }

        for (Iterator<GridColumnInfo> iterator = gridColumnInfoList.iterator(); iterator.hasNext();) {
            GridColumnInfo gridColumn = (GridColumnInfo) iterator.next();
            _sbuf.append(" {id : '" + gridColumn.getId() + "' ");
            if (StringUtils.isNotBlank(gridColumn.getHeader())) {
                _sbuf.append(" ,header : '" + gridColumn.getHeader() + "' ");
            }
            if (StringUtils.isNotBlank(gridColumn.getWidth())) {
                _sbuf.append(" ,width : " + gridColumn.getWidth() + " ");
            }
            if (StringUtils.isNotBlank(gridColumn.getDataIndex())) {
                _sbuf.append(" ,dataIndex : '" + gridColumn.getDataIndex() + "' ");
            } else {
                _sbuf.append(" ,dataIndex : '" + gridColumn.getId() + "' ");
            }
            if (StringUtils.isNotBlank(gridColumn.getAlign())) {
                _sbuf.append(" ,align : '" + gridColumn.getAlign() + "' ");
            }
            _sbuf.append(" ,sortable : " + gridColumn.getSortable() + "} ");

            if (iterator.hasNext()) {
                _sbuf.append(", ");
            }
        }

        _sbuf.append(" ], ");

        // 列表高度
        if (StringUtils.isNotBlank(height)) {
            _sbuf.append(" height : " + getHeight() + ", ");
        }
        // 列表宽度
        if (StringUtils.isNotBlank(width)) {
            _sbuf.append(" width : " + getWidth() + ", ");
        }
        // 列表表头
        if (StringUtils.isNotBlank(title)) {
            _sbuf.append(" title : '" + getTitle() + "', ");
        }
        // 行底色条纹
        _sbuf.append(" stripeRows : " + getStripeRows() + " ");
        _sbuf.append(" }); ");

        _sbuf.append(resolveId() + "Panel.render(); ");
        _sbuf.append(resolveId() + "Store.load(); ");
    }

    public String getDataUrl() {
        Assert.hasText(dataUrl, "'dataUrl' must not be empty");
        return dataUrl;
    }

    public void setDataUrl(String dataUrl) {
        this.dataUrl = dataUrl;
    }

    public String getStripeRows() {
        return stripeRows;
    }

    public void setStripeRows(String stripeRows) {
        this.stripeRows = stripeRows;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getHasRowNumberer() {
        return hasRowNumberer;
    }

    public void setHasRowNumberer(String hasRowNumberer) {
        this.hasRowNumberer = hasRowNumberer;
    }

    public String getSingleSelect() {
        return singleSelect;
    }

    public void setSingleSelect(String singleSelect) {
        this.singleSelect = singleSelect;
    }

    public String getRowselectFn() {
        return rowselectFn;
    }

    public void setRowselectFn(String rowselectFn) {
        this.rowselectFn = rowselectFn;
    }

    public String getRowdeselectFn() {
        return rowdeselectFn;
    }

    public void setRowdeselectFn(String rowdeselectFn) {
        this.rowdeselectFn = rowdeselectFn;
    }

    public String getCheckboxSelection() {
        return checkboxSelection;
    }

    public void setCheckboxSelection(String checkboxSelection) {
        this.checkboxSelection = checkboxSelection;
    }

}
