/*
 * Copyright 2009 by hebei, All rights reserved.
 */
package org.freedom.web.servlet.tags.form;

import javax.servlet.jsp.JspException;

import org.apache.commons.lang.StringUtils;
import org.springframework.web.servlet.tags.form.TagWriter;

/**
 * @author 何贝
 * @since JDK1.5
 */
public class ExtjsTextAreaTag extends AbstractExtjsInputTag {

    /**
     * 
     */
    private static final long serialVersionUID = -7575776274386241317L;

    @Override
    protected String createComponentScript() throws JspException {
        StringBuffer _sbuf = new StringBuffer();

        _sbuf.append(" var " + resolveId() + "ExtTextArea = new Ext.form.TextArea({ ");
        _sbuf.append(" id: '" + resolveId() + "', ");
        // 默认属性生成
        createComponentCommonAttributeScript(_sbuf);
        _sbuf.append(getOptionalAttributeScript(HEIGHT_ATTRIBUTE, getHeight(), true));
        _sbuf.append(" renderTo: '" + resolveId() + "Div' ");
        _sbuf.append(" }); ");

        return _sbuf.toString();
    }

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
    public String getHeight() {
        if (StringUtils.isEmpty(height)) {
            return "100";
        }
        return height;
    }

}
