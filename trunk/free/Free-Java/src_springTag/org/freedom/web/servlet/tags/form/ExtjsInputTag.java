/*
 * Copyright 2008 by hebei, All rights reserved.
 */
package org.freedom.web.servlet.tags.form;

import javax.servlet.jsp.JspException;

import org.springframework.web.servlet.tags.form.AbstractHtmlInputElementTag;
import org.springframework.web.servlet.tags.form.TagWriter;

/**
 * @author 何贝
 * @since JDK1.5
 */
public class ExtjsInputTag extends AbstractHtmlInputElementTag {

    private static final long serialVersionUID = 744707304978648098L;

    @Override
    protected int writeTagContent(TagWriter tagWriter) throws JspException {
        tagWriter.startTag("div");

        writeDefaultAttributes(tagWriter);

        tagWriter.endTag();
        //
        writerScript(tagWriter);
        return SKIP_BODY;
    }

    protected void writeDefaultAttributes(TagWriter tagWriter) throws JspException {
        writeOptionalAttribute(tagWriter, "id", resolveId() + "Div");
    }

    /**
     * 生成ExtjsTextField javascript脚本
     * 
     * @return
     * @throws JspException
     */
    private String createExtjsTextFieldJavascript() throws JspException {
        StringBuffer _sbuf = new StringBuffer();

        _sbuf.append(" var " + resolveId() + "ExtText = new Ext.form.TextField({ ");
        _sbuf.append("      id: '" + resolveId() + "', ");
        _sbuf.append("      renderTo: '" + resolveId() + "Div' ");
        _sbuf.append(" }); ");

        return _sbuf.toString();
    }

    /**
     * 添加Extjs脚本
     * 
     * @param tagWriter
     * @throws JspException
     */
    protected void writerScript(TagWriter tagWriter) throws JspException {
        tagWriter.writerJavascript(createExtjsTextFieldJavascript());
        // tagWriter.writeAttribute("value", getDisplayString(getBoundValue(),
        // getPropertyEditor()));
    }
}
