/*
 * Copyright 2009 by hebei, All rights reserved.
 */
package org.freedom.web.servlet.tags;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;

import org.springframework.web.servlet.tags.form.AbstractDataBoundFormElementTag;
import org.springframework.web.servlet.tags.form.TagWriter;

/**
 * @author 何贝
 * @since JDK1.5
 */
public abstract class AbstractExtjsTag extends AbstractDataBoundFormElementTag {
    public static final String EXTJS_COMPONENT_SCRIPT_NAME = "EXTJS_COMPONENT_SCRIPT_NAME";
    public static final String WIDTH_ATTRIBUTE = "width";
    public static final String HEIGHT_ATTRIBUTE = "height";
    public static final String DISABLED_ATTRIBUTE = "disabled";
    public static final String READONLY_ATTRIBUTE = "readOnly";

    protected String width;
    protected String height;
    protected String disabled;
    protected String readOnly;

    /*
     * (non-Javadoc)
     * 
     * @see org.springframework.web.servlet.tags.form.AbstractHtmlElementTag#writeDefaultAttributes(org.springframework.web.servlet.tags.form.TagWriter)
     */
    @Override
    protected void writeDefaultAttributes(TagWriter tagWriter) throws JspException {
        writeOptionalAttribute(tagWriter, "id", resolveId() + "Div");
        writeOptionalAttribute(tagWriter, "name", getName() + "Div");
    }

    /**
     * 生成属性
     * 
     * @param attributeName
     * @param value
     * @param isStringType
     * @throws JspException
     */
    protected String getOptionalAttributeScript(String attributeName, String value, boolean isStringType) throws JspException {

        if (value != null) {
            if (false == isStringType) {// type int,boolean
                // 不生成disabled状态,因为表单提交时disabled的元素不提交,改用只读和其样式表替代
                if (DISABLED_ATTRIBUTE.equals(attributeName)) {
                    return READONLY_ATTRIBUTE + ": true, ctCls: 'readonly', ";
                } else {
                    return attributeName + ": " + getDisplayString(evaluate(attributeName, value)) + ", ";
                }
            } else {// type string
                return attributeName + ": '" + getDisplayString(evaluate(attributeName, value)) + "', ";
            }
        }
        return "";
    }

    /**
     * 添加Extjs脚本
     * 
     * @param _scriptStr
     */
    @SuppressWarnings("unchecked")
    protected void saveComponentScript(String _scriptStr) {
        List<String> scriptList = (List<String>) this.pageContext.getAttribute(EXTJS_COMPONENT_SCRIPT_NAME);
        if (scriptList == null) {
            scriptList = new ArrayList<String>();
            this.pageContext.setAttribute(EXTJS_COMPONENT_SCRIPT_NAME, scriptList, PageContext.PAGE_SCOPE);
        }

        scriptList.add(_scriptStr);
    }

    /**
     * 默认属性生成
     * 
     * @param _sbuf
     * @throws JspException
     */
    protected void createComponentCommonAttributeScript(StringBuffer _sbuf) throws JspException {
        _sbuf.append(getOptionalAttributeScript(WIDTH_ATTRIBUTE, getWidth(), false));
        _sbuf.append(getOptionalAttributeScript(DISABLED_ATTRIBUTE, getDisabled(), false));
        _sbuf.append(getOptionalAttributeScript(READONLY_ATTRIBUTE, getReadOnly(), false));
    }

    /**
     * 生成Ext Component javascript脚本
     * 
     * @return
     * @throws JspException
     */
    protected abstract String createComponentScript() throws JspException;

    public String getWidth() {
        return width;
    }

    public void setWidth(String width) {
        this.width = width;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getDisabled() {
        return disabled;
    }

    public void setDisabled(String disabled) {
        this.disabled = disabled;
    }

    public String getReadOnly() {
        return readOnly;
    }

    public void setReadOnly(String readOnly) {
        this.readOnly = readOnly;
    }
}
