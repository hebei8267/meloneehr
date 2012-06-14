/*
 * Copyright 2008 by hebei, All rights reserved.
 */
package org.freedom.web.servlet.tags.form;

import javax.servlet.jsp.JspException;

import org.apache.commons.lang.StringUtils;
import org.springframework.web.servlet.tags.form.TagWriter;

/**
 * @author 何贝
 * @since JDK1.5
 */
public class ExtjsCalendarTag extends AbstractExtjsInputTag {

    private static final long serialVersionUID = 2368410327414870091L;
    public static final String FORMAT_ATTRIBUTE = "format";
    protected String format;

    /*
     * (non-Javadoc)
     * 
     * @see org.freedom.web.servlet.tags.form.AbstractExtjsTag#createComponentScript()
     */
    @Override
    protected String createComponentScript() throws JspException {
        StringBuffer _sbuf = new StringBuffer();

        _sbuf.append(" var " + resolveId() + "ExtCalendar = new Ext.form.DateField({ ");
        _sbuf.append(" id: '" + resolveId() + "', ");
        // 默认属性生成
        createComponentCommonAttributeScript(_sbuf);
        _sbuf.append(getOptionalAttributeScript(FORMAT_ATTRIBUTE, getFormat(), true));
        _sbuf.append(" renderTo: '" + resolveId() + "Div' ");
        _sbuf.append(" }); ");

        return _sbuf.toString();
    }

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

    public String getFormat() {
        if (StringUtils.isEmpty(format)) {
            return "Y-m-d";
        }
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

}
