package com.jquery.web.servlet.tags;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;

import org.springframework.web.servlet.tags.form.AbstractDataBoundFormElementTag;
import org.springframework.web.servlet.tags.form.TagWriter;

@SuppressWarnings("serial")
public abstract class AbstractJQueryTag extends AbstractDataBoundFormElementTag {
    public static final String JQUERY_COMPONENT_SCRIPT_NAME = "JQUERY_COMPONENT_SCRIPT_NAME";

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.springframework.web.servlet.tags.form.AbstractFormTag#writeTagContent
     * (org.springframework.web.servlet.tags.form.TagWriter)
     */
    @Override
    protected int writeTagContent(TagWriter arg0) throws JspException {

        return 0;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.springframework.web.servlet.tags.form.AbstractDataBoundFormElementTag
     * #
     * writeDefaultAttributes(org.springframework.web.servlet.tags.form.TagWriter
     * )
     */
    @Override
    protected void writeDefaultAttributes(TagWriter tagWriter) throws JspException {
//        writeOptionalAttribute(tagWriter, "id", resolveId() + "Div");
//        writeOptionalAttribute(tagWriter, "name", getName() + "Div");
    }

    /**
     * 添加JQuery javaScript脚本
     * 
     * @param _scriptStr
     */
    @SuppressWarnings("unchecked")
    protected void addComponentScript(String _scriptStr) {
        List<String> scriptList = (List<String>) this.pageContext.getAttribute(JQUERY_COMPONENT_SCRIPT_NAME);
        if (scriptList == null) {
            scriptList = new ArrayList<String>();
            this.pageContext.setAttribute(JQUERY_COMPONENT_SCRIPT_NAME, scriptList, PageContext.PAGE_SCOPE);
        }

        scriptList.add(_scriptStr);
    }

    /**
     * 生成JQuery Component javascript脚本
     * 
     * @return
     * @throws JspException
     */
    protected abstract String createComponentScript() throws JspException;
}
