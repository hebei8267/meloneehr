package org.springframework.web.servlet.tags.form;

import java.io.IOException;
import java.io.Writer;
import java.util.Stack;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

public class TagWriter {
    /**
     * @author hebei
     * 
     *         添加JQuery javaScript脚本
     * 
     * @param javascript javascript脚本
     * @throws JspException
     */
    protected void writerExtjsScript(String javascript) throws JspException {
        this.writer.append("<script type='text/javascript'> $().ready(function(){ " + javascript + " });</script>");
    }

    private static final class SafeWriter {

        public SafeWriter append(String value) throws JspException {
            try {
                getWriterToUse().write(String.valueOf(value));
                return this;
            } catch (IOException ex) {
                throw new JspException("Unable to write to JspWriter", ex);
            }
        }

        private Writer getWriterToUse() {
            return ((Writer) (pageContext == null ? writer : pageContext.getOut()));
        }

        private PageContext pageContext;
        private Writer writer;

        public SafeWriter(PageContext pageContext) {
            this.pageContext = pageContext;
        }

        public SafeWriter(Writer writer) {
            this.writer = writer;
        }
    }

    private static class TagStateEntry {

        public String getTagName() {
            return tagName;
        }

        public void markAsBlockTag() {
            blockTag = true;
        }

        public boolean isBlockTag() {
            return blockTag;
        }

        private final String tagName;
        private boolean blockTag;

        public TagStateEntry(String tagName) {
            this.tagName = tagName;
        }
    }

    @SuppressWarnings("unchecked")
    public TagWriter(PageContext pageContext) {
        tagState = new Stack();
        Assert.notNull(pageContext, "PageContext must not be null");
        writer = new SafeWriter(pageContext);
    }

    @SuppressWarnings("unchecked")
    public TagWriter(Writer writer) {
        tagState = new Stack();
        Assert.notNull(writer, "Writer must not be null");
        this.writer = new SafeWriter(writer);
    }

    public void startTag(String tagName) throws JspException {
        if (inTag()) {
            closeTagAndMarkAsBlock();
        }
        push(tagName);
        writer.append("<").append(tagName);
    }

    public void writeAttribute(String attributeName, String attributeValue) throws JspException {
        if (currentState().isBlockTag()) {
            throw new IllegalStateException("Cannot write attributes after opening tag is closed.");
        } else {
            writer.append(" ").append(attributeName).append("=\"").append(attributeValue).append("\"");
            return;
        }
    }

    public void writeOptionalAttributeValue(String attributeName, String attributeValue) throws JspException {
        if (StringUtils.hasText(attributeValue)) {
            writeAttribute(attributeName, attributeValue);
        }
    }

    public void appendValue(String value) throws JspException {
        if (!inTag()) {
            throw new IllegalStateException("Cannot write tag value. No open tag available.");
        } else {
            closeTagAndMarkAsBlock();
            writer.append(value);
            return;
        }
    }

    public void forceBlock() throws JspException {
        if (currentState().isBlockTag()) {
            return;
        } else {
            closeTagAndMarkAsBlock();
            return;
        }
    }

    public void endTag() throws JspException {
        endTag(false);
    }

    public void endTag(boolean enforceClosingTag) throws JspException {
        if (!inTag())
            throw new IllegalStateException("Cannot write end of tag. No open tag available.");
        boolean renderClosingTag = true;
        if (!currentState().isBlockTag())
            if (enforceClosingTag) {
                writer.append(">");
            } else {
                writer.append("/>");
                renderClosingTag = false;
            }
        if (renderClosingTag)
            writer.append("</").append(currentState().getTagName()).append(">");
        tagState.pop();
    }

    @SuppressWarnings("unchecked")
    private void push(String tagName) {
        tagState.push(new TagStateEntry(tagName));
    }

    private void closeTagAndMarkAsBlock() throws JspException {
        if (!currentState().isBlockTag()) {
            currentState().markAsBlockTag();
            writer.append(">");
        }
    }

    private boolean inTag() {
        return tagState.size() > 0;
    }

    private TagStateEntry currentState() {
        return (TagStateEntry) tagState.peek();
    }

    private final SafeWriter writer;
    @SuppressWarnings("unchecked")
    private final Stack tagState;
}