package com.jquery.web.servlet.tags.tree;

import javax.servlet.jsp.JspException;

import org.springframework.util.Assert;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.tags.form.TagWriter;

import com.jquery.core.constant.SysWebConstant;
import com.jquery.web.servlet.tags.AbstractJQueryTag;

@SuppressWarnings("serial")
public class JQueryEasyUITreeTag extends AbstractJQueryTag {
    /** 数据URL */
    protected String dataUrl;

    /** 节点选择事件js函数 */
    protected String clickFn;

    @Override
    protected int writeTagContent(TagWriter tagWriter) throws JspException {

        tagWriter.startTag("ul");
        writeDefaultAttributes(tagWriter);
        tagWriter.endTag();

        // 添加JQuery javaScript脚本
        addComponentScript(createComponentScript());
        return SKIP_BODY;

    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.jquery.web.servlet.tags.AbstractJQueryTag#writeDefaultAttributes(
     * org.springframework.web.servlet.tags.form.TagWriter)
     */
    @Override
    protected void writeDefaultAttributes(TagWriter tagWriter) throws JspException {
        writeOptionalAttribute(tagWriter, "id", resolveId());
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.jquery.web.servlet.tags.AbstractJQueryTag#createComponentScript()
     */
    @Override
    protected String createComponentScript() throws JspException {
        StringBuffer _sbuf = new StringBuffer();

        _sbuf.append(" $('#" + resolveId() + "').tree({");
        _sbuf.append(" url: '" + SysWebConstant.WEB_PROJECT_NAME + "/" + getDataUrl() + "',");

        // 树节点选中事件
        if (StringUtils.hasText(clickFn)) {
            _sbuf.append(" onClick: " + getClickFn() + ", ");
        }
        _sbuf.append(" animate: true");
        _sbuf.append(" });");

        return _sbuf.toString();
    }

    public String getDataUrl() {
        Assert.hasText(dataUrl, "'dataUrl' must not be empty");
        return dataUrl;
    }

    public void setDataUrl(String dataUrl) {
        this.dataUrl = dataUrl;
    }

    public String getClickFn() {
        return clickFn;
    }

    public void setClickFn(String clickFn) {
        this.clickFn = clickFn;
    }
}
