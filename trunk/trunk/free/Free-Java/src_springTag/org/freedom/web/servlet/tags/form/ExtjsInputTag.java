/*
 * Copyright 2008 by hebei, All rights reserved.
 */
package org.freedom.web.servlet.tags.form;

import javax.servlet.jsp.JspException;

import org.apache.commons.lang.StringUtils;
import org.springframework.web.servlet.tags.form.TagWriter;

/**
 * Extjs普通输入框
 * 
 * @author 何贝
 * @since JDK1.5
 */
public class ExtjsInputTag extends AbstractExtjsTag {

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
        super.writeDefaultAttributes(tagWriter);
        tagWriter.endTag();

        // 添加Extjs脚本
        saveComponentScript(createComponentScript());
        return SKIP_BODY;
    }

    @Override
    protected String createComponentScript() throws JspException {
        StringBuffer _sbuf = new StringBuffer();

        _sbuf.append(" var " + resolveId() + "ExtText = new Ext.form.TextField({ ");
        _sbuf.append(" id: '" + resolveId() + "', ");
        // 默认属性生成
        createComponentCommonAttributeScript(_sbuf);
        _sbuf.append(" renderTo: '" + resolveId() + "Div' ");
        _sbuf.append(" }); ");

        return _sbuf.toString();
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
