/*
 * Copyright 2008 by hebei, All rights reserved.
 */
package org.freedom.web.servlet.tags.form;

import javax.servlet.jsp.JspException;

import org.apache.commons.lang.StringUtils;
import org.springframework.web.servlet.tags.form.AbstractHtmlInputElementTag;
import org.springframework.web.servlet.tags.form.TagWriter;

/**
 * Extjs普通输入框
 * 
 * @author 何贝
 * @since JDK1.5
 */
public class ExtjsInputTag extends AbstractHtmlInputElementTag {

    private static final long serialVersionUID = 744707304978648098L;

    public static final String ALLOW_BLANK_ATTRIBUTE = "allowBlank";
    public static final String EMPTY_TEXT_ATTRIBUTE = "emptyText";
    public static final String WIDTH_ATTRIBUTE = "width";
    public static final String MAX_LENGTH_ATTRIBUTE = "maxLength";
    public static final String MIN_LENGTH_ATTRIBUTE = "minLength";
    public static final String VALIDATOR_ATTRIBUTE = "validator";
    public static final String READONLY_ATTRIBUTE = "readOnly";
    private String allowBlank;
    private String emptyText;
    private String width;
    private String maxLength;
    private String minLength;
    private String validator;
    private String readOnly;

    /*
     * (non-Javadoc)
     * 
     * @see org.springframework.web.servlet.tags.form.AbstractFormTag#writeTagContent(org.springframework.web.servlet.tags.form.TagWriter)
     */
    @Override
    protected int writeTagContent(TagWriter tagWriter) throws JspException {
        tagWriter.startTag("div");
        writeDefaultAttributes(tagWriter);
        tagWriter.endTag();
        // 添加Extjs脚本
        writerScript(tagWriter);
        return SKIP_BODY;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.springframework.web.servlet.tags.form.AbstractHtmlElementTag#writeDefaultAttributes(org.springframework.web.servlet.tags.form.TagWriter)
     */
    protected void writeDefaultAttributes(TagWriter tagWriter) throws JspException {
        writeOptionalAttribute(tagWriter, "id", resolveId() + "Div");
    }

    /**
     * 生成Ext TextField javascript脚本
     * 
     * @return
     * @throws JspException
     */
    protected String createExtjsTextFieldJavascript() throws JspException {
        StringBuffer _sbuf = new StringBuffer();

        _sbuf.append(" var " + resolveId() + "ExtText = new Ext.form.TextField({ ");
        _sbuf.append("      id: '" + resolveId() + "', ");
        // 默认属性生成
        writeCommonAttribute(_sbuf);
        _sbuf.append("      renderTo: '" + resolveId() + "Div' ");
        _sbuf.append(" }); ");

        return _sbuf.toString();
    }

    /**
     * 默认属性生成
     * 
     * @param _sbuf
     * @throws JspException
     */
    protected void writeCommonAttribute(StringBuffer _sbuf) throws JspException {
        String _value = getDisplayString(getBoundValue(), getPropertyEditor());
        if (StringUtils.isNotEmpty(_value)) {
            _sbuf.append("      value: '" + _value + "', ");
        }

        writeOptionalAttribute(_sbuf, ALLOW_BLANK_ATTRIBUTE, getAllowBlank(), false);
        writeOptionalAttribute(_sbuf, EMPTY_TEXT_ATTRIBUTE, getEmptyText(), true);
        writeOptionalAttribute(_sbuf, WIDTH_ATTRIBUTE, getWidth(), false);
        writeOptionalAttribute(_sbuf, MAX_LENGTH_ATTRIBUTE, getMaxLength(), false);
        writeOptionalAttribute(_sbuf, MIN_LENGTH_ATTRIBUTE, getMinLength(), false);
        writeOptionalAttribute(_sbuf, VALIDATOR_ATTRIBUTE, getValidator(), false);
        writeOptionalAttribute(_sbuf, READONLY_ATTRIBUTE, getReadOnly(), false);
    }

    /**
     * 生成属性
     * 
     * @param _sbuf
     * @param attributeName
     * @param value
     * @param isStringType
     * @throws JspException
     */
    private void writeOptionalAttribute(StringBuffer _sbuf, String attributeName, String value, boolean isStringType)
            throws JspException {

        if (value != null) {
            if (false == isStringType) {// type int,boolean
                _sbuf.append(attributeName + ": " + getDisplayString(evaluate(attributeName, value)) + ", ");
            } else {// type string
                _sbuf.append(attributeName + ": '" + getDisplayString(evaluate(attributeName, value)) + "', ");
            }
        }

    }

    /**
     * 添加Extjs脚本
     * 
     * @param tagWriter
     * @throws JspException
     */
    protected void writerScript(TagWriter tagWriter) throws JspException {
        tagWriter.writerJavascript(createExtjsTextFieldJavascript());
    }

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
}
