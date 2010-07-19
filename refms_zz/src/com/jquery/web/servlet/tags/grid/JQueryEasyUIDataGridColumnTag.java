package com.jquery.web.servlet.tags.grid;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;

import org.springframework.beans.BeanUtils;
import org.springframework.util.Assert;
import org.springframework.web.servlet.tags.form.AbstractFormTag;
import org.springframework.web.servlet.tags.form.TagWriter;

public class JQueryEasyUIDataGridColumnTag extends AbstractFormTag {

    private static final long serialVersionUID = -7446151589490969078L;

    private String field;
    private String width;
    private String title;
    private boolean visible = true;
    private String align = "";

    public String getField() {
        Assert.hasText(field, "'field' must not be empty");
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public String getTitle() {
        Assert.hasText(field, "'title' must not be empty");
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
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
        BeanUtils.copyProperties(this, _copy);
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
        List<GridColumnInfo> gridColumnInfoList = (List<GridColumnInfo>) this.pageContext.getAttribute(JQueryEasyUIDataGridTag.GRID_COLUMN_PAGE_ATTRIBUTE);
        if (gridColumnInfoList == null) {
            gridColumnInfoList = new ArrayList<GridColumnInfo>();
            this.pageContext.setAttribute(JQueryEasyUIDataGridTag.GRID_COLUMN_PAGE_ATTRIBUTE, gridColumnInfoList, PageContext.PAGE_SCOPE);
        }
        gridColumnInfoList.add(copyGridColumnInfo());
    }

    @Override
    protected int writeTagContent(TagWriter tagWriter) throws JspException {
        return SKIP_BODY;
    }
}
