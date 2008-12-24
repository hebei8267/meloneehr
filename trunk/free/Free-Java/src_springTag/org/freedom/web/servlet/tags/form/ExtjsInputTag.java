/*
 * Copyright 2008 by hebei, All rights reserved.
 */
package org.freedom.web.servlet.tags.form;

import javax.servlet.jsp.JspException;

import org.springframework.web.servlet.tags.form.TagWriter;

/**
 * Extjs普通输入框
 * 
 * @author 何贝
 * @since JDK1.5
 */
public class ExtjsInputTag extends AbstractExtjsTag {

    private static final long serialVersionUID = 744707304978648098L;

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

}
