/*
 * Copyright 2008 by hebei, All rights reserved.
 */
package org.freedom.web.servlet.tags.form;

import javax.servlet.jsp.JspException;

import org.apache.commons.lang.StringUtils;
import org.freedom.web.servlet.tags.AbstractExtjsTag;

/**
 * @author 何贝
 * @since JDK1.5
 */
public abstract class AbstractExtjsInputTag extends AbstractExtjsTag {

    public static final String ALLOW_BLANK_ATTRIBUTE = "allowBlank";
    public static final String EMPTY_TEXT_ATTRIBUTE = "emptyText";
    public static final String MAX_LENGTH_ATTRIBUTE = "maxLength";
    public static final String MIN_LENGTH_ATTRIBUTE = "minLength";
    public static final String VALIDATOR_ATTRIBUTE = "validator";

    protected String allowBlank;
    protected String emptyText;
    protected String maxLength;
    protected String minLength;
    protected String validator;

    /**
     * 默认属性生成
     * 
     * @param _sbuf
     * @throws JspException
     */
    @Override
    protected void createComponentCommonAttributeScript(StringBuffer _sbuf) throws JspException {
        String _value = getDisplayString(getBoundValue(), getPropertyEditor());
        if (StringUtils.isNotEmpty(_value)) {
            _sbuf.append("      value: '" + _value + "', ");
        }

        _sbuf.append(getOptionalAttributeScript(ALLOW_BLANK_ATTRIBUTE, getAllowBlank(), false));
        _sbuf.append(getOptionalAttributeScript(EMPTY_TEXT_ATTRIBUTE, getEmptyText(), true));
        _sbuf.append(getOptionalAttributeScript(MAX_LENGTH_ATTRIBUTE, getMaxLength(), false));
        _sbuf.append(getOptionalAttributeScript(MIN_LENGTH_ATTRIBUTE, getMinLength(), false));
        _sbuf.append(getOptionalAttributeScript(VALIDATOR_ATTRIBUTE, getValidator(), false));

        super.createComponentCommonAttributeScript(_sbuf);
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

    @Override
    public String getWidth() {
        if (StringUtils.isEmpty(width)) {
            return "155";
        }
        return width;
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

}
