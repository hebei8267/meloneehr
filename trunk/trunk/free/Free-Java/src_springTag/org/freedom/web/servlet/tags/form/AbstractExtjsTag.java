/*
 * Copyright 2008 by hebei, All rights reserved.
 */
package org.freedom.web.servlet.tags.form;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;

import org.apache.commons.lang.StringUtils;
import org.springframework.web.servlet.tags.form.AbstractDataBoundFormElementTag;
import org.springframework.web.servlet.tags.form.TagWriter;

/**
 * @author 何贝
 * @since JDK1.5
 */
public abstract class AbstractExtjsTag extends AbstractDataBoundFormElementTag {
    public static final String EXTJS_COMPONENT_SCRIPT_NAME = "EXTJS_COMPONENT_SCRIPT_NAME";
    public static final String ALLOW_BLANK_ATTRIBUTE = "allowBlank";
    public static final String EMPTY_TEXT_ATTRIBUTE = "emptyText";
    public static final String WIDTH_ATTRIBUTE = "width";
    public static final String MAX_LENGTH_ATTRIBUTE = "maxLength";
    public static final String MIN_LENGTH_ATTRIBUTE = "minLength";
    public static final String VALIDATOR_ATTRIBUTE = "validator";
    public static final String READONLY_ATTRIBUTE = "readOnly";
    public static final String DISABLED_ATTRIBUTE = "disabled";
    private String allowBlank;
    private String emptyText;
    private String width;
    private String maxLength;
    private String minLength;
    private String validator;
    private String readOnly;
    private String disabled;

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
        String _value = getDisplayString(getBoundValue(), getPropertyEditor());
        if (StringUtils.isNotEmpty(_value)) {
            _sbuf.append("      value: '" + _value + "', ");
        }

        _sbuf.append(getOptionalAttributeScript(ALLOW_BLANK_ATTRIBUTE, getAllowBlank(), false));
        _sbuf.append(getOptionalAttributeScript(EMPTY_TEXT_ATTRIBUTE, getEmptyText(), true));
        _sbuf.append(getOptionalAttributeScript(WIDTH_ATTRIBUTE, getWidth(), false));
        _sbuf.append(getOptionalAttributeScript(MAX_LENGTH_ATTRIBUTE, getMaxLength(), false));
        _sbuf.append(getOptionalAttributeScript(MIN_LENGTH_ATTRIBUTE, getMinLength(), false));
        _sbuf.append(getOptionalAttributeScript(VALIDATOR_ATTRIBUTE, getValidator(), false));
        _sbuf.append(getOptionalAttributeScript(READONLY_ATTRIBUTE, getReadOnly(), false));
        _sbuf.append(getOptionalAttributeScript(DISABLED_ATTRIBUTE, getDisabled(), false));
    }

    /**
     * 生成Ext Component javascript脚本
     * 
     * @return
     * @throws JspException
     */
    protected abstract String createComponentScript() throws JspException;

    public String getAllowBlank() {
        return allowBlank;
    }

    public void setAllowBlank(String allowBlank) {
        this.allowBlank = allowBlank;
    }

    public String getEmptyText() {
        return emptyText;
    }

    public void setEmptyText(String emptyText) {
        this.emptyText = emptyText;
    }

    public String getWidth() {
        if (StringUtils.isEmpty(width)) {
            return "155";
        }
        return width;
    }

    public void setWidth(String width) {
        this.width = width;
    }

    public String getMaxLength() {
        return maxLength;
    }

    public void setMaxLength(String maxLength) {
        this.maxLength = maxLength;
    }

    public String getMinLength() {
        return minLength;
    }

    public void setMinLength(String minLength) {
        this.minLength = minLength;
    }

    public String getValidator() {
        return validator;
    }

    public void setValidator(String validator) {
        this.validator = validator;
    }

    public String getReadOnly() {
        return readOnly;
    }

    public void setReadOnly(String readOnly) {
        this.readOnly = readOnly;
    }

    public String getDisabled() {
        return disabled;
    }

    public void setDisabled(String disabled) {
        this.disabled = disabled;
    }
}
