/*
 * Copyright 2008 by hebei, All rights reserved.
 */
package org.freedom.web.servlet.tags.form;

import javax.servlet.jsp.JspException;

import org.springframework.web.servlet.tags.form.TagWriter;

/**
 * Extjs密码框
 * 
 * @author 何贝
 * @since JDK1.5
 */
public class ExtjsPasswordInputTag extends ExtjsInputTag {

    private static final long serialVersionUID = -2522481235678968266L;

    /*
     * (non-Javadoc)
     * 
     * @see org.freedom.web.servlet.tags.form.ExtjsInputTag#writeTagContent(org.springframework.web.servlet.tags.form.TagWriter)
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

    /**
     * 生成Ext TextField javascript脚本
     * 
     * @return
     * @throws JspException
     */
    @Override
    protected String createComponentScript() throws JspException {
        StringBuffer _sbuf = new StringBuffer();

        _sbuf.append(" var " + resolveId() + "ExtPwd = new Ext.form.TextField({ ");
        _sbuf.append(" id: '" + resolveId() + "', ");
        _sbuf.append(" inputType: 'password', ");
        // 默认属性生成
        createComponentCommonAttributeScript(_sbuf);
        _sbuf.append(" renderTo: '" + resolveId() + "Div' ");
        _sbuf.append(" }); ");

        return _sbuf.toString();
    }

}
