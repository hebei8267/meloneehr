package com.jquery.web.servlet.tags.from;

import javax.servlet.jsp.JspException;

import org.springframework.web.servlet.tags.form.TagWriter;

import com.jquery.web.servlet.tags.AbstractJQueryTag;

@SuppressWarnings("serial")
public class JQueryUICalendarTag extends AbstractJQueryTag {

    private boolean changeMonth = false;
    private boolean changeYear = false;

    @Override
    protected int writeTagContent(TagWriter tagWriter) throws JspException {

        tagWriter.startTag("input");
        writeDefaultAttributes(tagWriter);
        tagWriter.writeAttribute("type", "text");
        writeValue(tagWriter);
        tagWriter.endTag();

        // 添加JQuery javaScript脚本
        addComponentScript(createComponentScript());
        return SKIP_BODY;

    }

    @Override
    protected void writeDefaultAttributes(TagWriter tagWriter) throws JspException {
        writeOptionalAttribute(tagWriter, "id", resolveId());
        writeOptionalAttribute(tagWriter, "name", getName());
    };

    protected void writeValue(TagWriter tagWriter) throws JspException {
        tagWriter.writeAttribute("value", getDisplayString(getBoundValue(), getPropertyEditor()));
    }

    @Override
    protected String createComponentScript() throws JspException {
        StringBuffer _sbuf = new StringBuffer();

        _sbuf.append(" $('#" + resolveId() + "').datepicker({");
        _sbuf.append(" changeMonth: " + getChangeMonth() + ", ");
        _sbuf.append(" changeYear: " + getChangeYear() + " ");
        _sbuf.append(" });");

        return _sbuf.toString();
    }

    public boolean getChangeMonth() {
        return changeMonth;
    }

    public void setChangeMonth(boolean changeMonth) {
        this.changeMonth = changeMonth;
    }

    public boolean getChangeYear() {
        return changeYear;
    }

    public void setChangeYear(boolean changeYear) {
        this.changeYear = changeYear;
    }

}
