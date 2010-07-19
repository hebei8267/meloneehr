package com.jquery.web.servlet.tags.grid;

import java.util.Iterator;
import java.util.List;

import javax.servlet.jsp.JspException;

import org.springframework.util.StringUtils;
import org.springframework.web.servlet.tags.form.TagWriter;

import com.jquery.core.constant.SysWebConstant;
import com.jquery.web.servlet.tags.AbstractJQueryTag;

@SuppressWarnings("serial")
public class JQueryEasyUIDataGridTag extends AbstractJQueryTag {
    public static final String GRID_COLUMN_PAGE_ATTRIBUTE = "com.jquery.web.servlet.tags.grid.JQueryEasyUIDataGridTag.gridColumn";

    /** 表格标题 */
    protected String title = "";
    /** 表格宽度 */
    protected int width;
    /** 表格高度 */
    protected int height;
    /** 数据URL */
    protected String dataUrl;
    /** 行编号 */
    protected boolean rowNumbers = true;
    /** 是否单选--默认单选 */
    protected boolean singleSelect = true;
    /** 页数据行大小 默认20行 */
    protected int pageSize = 20;
    /** 分页栏 */
    protected boolean pagination = false;
    /** 分页栏 */
    protected String queryParams = "{}";
    /** 行条纹 */
    protected boolean striped = true;

    protected int writeTagContent(TagWriter tagWriter) throws JspException {
        tagWriter.startTag("table");
        writeDefaultAttributes(tagWriter);
        tagWriter.endTag(true);

        return EVAL_BODY_INCLUDE;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.jquery.web.servlet.tags.AbstractJQueryTag#writeDefaultAttributes(
     * org.springframework.web.servlet.tags.form.TagWriter)
     */
    @Override
    protected void writeDefaultAttributes(TagWriter tagWriter) throws JspException {
        writeOptionalAttribute(tagWriter, "id", resolveId());
    }

    /*
     * (non-Javadoc)
     * 
     * @see javax.servlet.jsp.tagext.TagSupport#doEndTag()
     */
    @Override
    public int doEndTag() throws JspException {

        // 添加JQuery javaScript脚本
        addComponentScript(createComponentScript());
        return EVAL_PAGE;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.jquery.web.servlet.tags.AbstractJQueryTag#createComponentScript()
     */
    @SuppressWarnings("unchecked")
    @Override
    protected String createComponentScript() throws JspException {
        List<GridColumnInfo> gridColumnInfoList = (List<GridColumnInfo>) this.pageContext.getAttribute(GRID_COLUMN_PAGE_ATTRIBUTE);

        if (gridColumnInfoList == null || gridColumnInfoList.size() == 0) {
            return "";
        }
        StringBuffer _sbuf = new StringBuffer();
        _sbuf.append(" $('#" + resolveId() + "').datagrid({");

        _sbuf.append(" title: '" + getTitle() + "',");
        _sbuf.append(" width: " + getWidth() + ",");
        _sbuf.append(" height: " + getHeight() + ",");
        _sbuf.append(" url: '" + SysWebConstant.WEB_PROJECT_NAME + "/" + getDataUrl() + "',");
        _sbuf.append(" columns: [[");
        // 添加具体gridColumn脚本
        _sbuf.append(createComponentScript(gridColumnInfoList));
        _sbuf.append(" ]],");
        _sbuf.append(" rownumbers: " + isRowNumbers() + ",");
        _sbuf.append(" singleSelect: " + isSingleSelect() + ",");
        _sbuf.append(" pageSize: " + getPageSize() + ",");
        _sbuf.append(" pagination: " + isPagination() + ",");
        _sbuf.append(" striped: " + isStriped() + ",");
        _sbuf.append(" queryParams: " + getQueryParams());
        _sbuf.append(" });");

        // 清理gridColumn信息
        cleanGridColumnInfo();
        return _sbuf.toString();
    }

    /**
     * 添加具体gridColumn脚本
     * 
     * @param gridColumnInfoList
     * @return
     */
    private String createComponentScript(List<GridColumnInfo> gridColumnInfoList) {
        StringBuffer _sbuf = new StringBuffer();
        for (Iterator<GridColumnInfo> iterator = gridColumnInfoList.iterator(); iterator.hasNext();) {

            GridColumnInfo gridColumn = (GridColumnInfo) iterator.next();
            if (gridColumn.getVisible()) {
                _sbuf.append(" {field: '" + gridColumn.getField() + "' ");
                if (StringUtils.hasText(gridColumn.getTitle())) {
                    _sbuf.append(" ,title: '" + gridColumn.getTitle() + "' ");
                }
                if (StringUtils.hasText(gridColumn.getWidth())) {
                    _sbuf.append(" ,width: " + gridColumn.getWidth() + " ");
                }
                if (StringUtils.hasText(gridColumn.getAlign())) {
                    _sbuf.append(" ,align: '" + gridColumn.getAlign() + "' ");
                }
                _sbuf.append("}, ");
            }
        }
        // 去掉末尾的逗号
        return _sbuf.substring(0, _sbuf.length() - 2);
    }

    /**
     * 清理gridColumn信息
     */
    private void cleanGridColumnInfo() {
        this.pageContext.removeAttribute(GRID_COLUMN_PAGE_ATTRIBUTE);
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public String getDataUrl() {
        return dataUrl;
    }

    public void setDataUrl(String dataUrl) {
        this.dataUrl = dataUrl;
    }

    public boolean isRowNumbers() {
        return rowNumbers;
    }

    public void setRowNumbers(boolean rowNumbers) {
        this.rowNumbers = rowNumbers;
    }

    public boolean isSingleSelect() {
        return singleSelect;
    }

    public void setSingleSelect(boolean singleSelect) {
        this.singleSelect = singleSelect;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public boolean isPagination() {
        return pagination;
    }

    public void setPagination(boolean pagination) {
        this.pagination = pagination;
    }

    public String getQueryParams() {
        return queryParams;
    }

    public void setQueryParams(String queryParams) {
        this.queryParams = queryParams;
    }

    public boolean isStriped() {
        return striped;
    }

    public void setStriped(boolean striped) {
        this.striped = striped;
    }
}
