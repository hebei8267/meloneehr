/*
 * Copyright 2008 by hebei, All rights reserved.
 */
package org.freedom.web.servlet.tags.form;

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

    /**
     * 生成属性
     * 
     * @param attributeName
     * @param value
     * @param isStringType
     * @throws JspException
     */
    protected String getOptionalAttributeScript(String attributeName, String value, boolean isStringType)
            throws JspException {

        if (value != null) {
            if (false == isStringType) {// type int,boolean
                return attributeName + ": " + getDisplayString(evaluate(attributeName, value)) + ", ";
            } else {// type string
                return attributeName + ": '" + getDisplayString(evaluate(attributeName, value)) + "', ";
            }
        }
        return null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.springframework.web.servlet.tags.form.AbstractHtmlElementTag#writeDefaultAttributes(org.springframework.web.servlet.tags.form.TagWriter)
     */
    @Override
    protected void writeDefaultAttributes(TagWriter tagWriter) throws JspException {
        writeOptionalAttribute(tagWriter, "id", resolveId() + "Div");
        writeOptionalAttribute(tagWriter, "name", getName());
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
            this.pageContext.setAttribute(EXTJS_COMPONENT_SCRIPT_NAME, scriptList, PageContext.REQUEST_SCOPE);
        }

        scriptList.add(_scriptStr);
    }

    /**
     * 生成Ext Component javascript脚本
     * 
     * @return
     * @throws JspException
     */
    protected abstract String createComponentScript() throws JspException;
}
