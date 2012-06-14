/*
 * Copyright 2008 by hebei, All rights reserved.
 */
package org.freedom.web.servlet.tags.form;

import java.util.Collection;
import java.util.Map;

import javax.servlet.jsp.JspException;

import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.servlet.support.BindStatus;
import org.springframework.web.servlet.tags.form.OptionTag;
import org.springframework.web.servlet.tags.form.OptionWriter;
import org.springframework.web.servlet.tags.form.SelectTag;
import org.springframework.web.servlet.tags.form.TagWriter;

/**
 * Extjs下拉输入框
 * 
 * @author 何贝
 * @since JDK1.5
 */
public class ExtjsComboBoxTag extends AbstractExtjsInputTag {

    private static final long serialVersionUID = 1386239435396539631L;
    public static final String LIST_VALUE_PAGE_ATTRIBUTE = SelectTag.LIST_VALUE_PAGE_ATTRIBUTE;
    private static final Object EMPTY = new Object();
    private Object items;
    private String itemValue;
    private String itemLabel;
    private Object multiple = Boolean.FALSE;
    private TagWriter tagWriter;

    public void setItems(Object items) {
        this.items = (items != null ? items : EMPTY);
    }

    protected Object getItems() {
        return this.items;
    }

    public void setItemValue(String itemValue) {
        this.itemValue = itemValue;
    }

    protected String getItemValue() {
        return this.itemValue;
    }

    public void setItemLabel(String itemLabel) {
        this.itemLabel = itemLabel;
    }

    protected String getItemLabel() {
        return this.itemLabel;
    }

    public void setMultiple(Object multiple) {
        this.multiple = multiple;
    }

    protected Object getMultiple() {
        return this.multiple;
    }

    @Override
    protected int writeTagContent(TagWriter tagWriter) throws JspException {
        tagWriter.startTag("select");
        writeDefaultAttributes(tagWriter);
        if (isMultiple()) {
            tagWriter.writeAttribute("multiple", "multiple");
        }

        Object items = getItems();
        if (items != null) {
            // Items specified, but might still be empty...
            if (items != EMPTY) {
                Object itemsObject = (items instanceof String ? evaluate("items", (String) items) : items);
                if (itemsObject != null) {
                    String valueProperty = (getItemValue() != null ? ObjectUtils.getDisplayString(evaluate("itemValue",
                            getItemValue())) : null);
                    String labelProperty = (getItemLabel() != null ? ObjectUtils.getDisplayString(evaluate("itemLabel",
                            getItemLabel())) : null);
                    OptionWriter optionWriter = new OptionWriter(itemsObject, getBindStatus(), valueProperty, labelProperty,
                            isHtmlEscape());
                    optionWriter.writeOptions(tagWriter);
                }
            }
            tagWriter.endTag(true);
            writeHiddenTagIfNecessary(tagWriter);
            return SKIP_BODY;
        } else {
            // Using nested <form:option/> tags, so just expose the value in the
            // PageContext...
            tagWriter.forceBlock();
            this.tagWriter = tagWriter;
            this.pageContext.setAttribute(LIST_VALUE_PAGE_ATTRIBUTE, getBindStatus());
            return EVAL_BODY_INCLUDE;
        }
    }

    /**
     * If using a multi-select, a hidden element is needed to make sure all
     * items are correctly unselected on the server-side in response to a
     * <code>null</code> post.
     */
    private void writeHiddenTagIfNecessary(TagWriter tagWriter) throws JspException {
        if (isMultiple()) {
            tagWriter.startTag("input");
            tagWriter.writeAttribute("type", "hidden");
            tagWriter.writeAttribute("name", WebDataBinder.DEFAULT_FIELD_MARKER_PREFIX + getName());
            tagWriter.writeAttribute("value", "1");
            tagWriter.endTag();
        }
    }

    private boolean isMultiple() throws JspException {
        Object multiple = getMultiple();
        if (Boolean.TRUE.equals(multiple) || "true".equals(multiple) || "multiple".equals(multiple)) {
            return true;
        } else if (this.multiple instanceof String) {
            Object evaluatedValue = evaluate("multiple", multiple);
            return Boolean.TRUE.equals(evaluatedValue);
        }
        return forceMultiple();
    }

    /**
     * Returns '<code>true</code>' if the bound value requires the resultant '<code>select</code>'
     * tag to be multi-select.
     */
    @SuppressWarnings("unchecked")
    private boolean forceMultiple() throws JspException {
        BindStatus bindStatus = getBindStatus();
        Class valueType = bindStatus.getValueType();
        if (valueType != null && typeRequiresMultiple(valueType)) {
            return true;
        } else if (bindStatus.getEditor() != null) {
            Object editorValue = bindStatus.getEditor().getValue();
            if (editorValue != null && typeRequiresMultiple(editorValue.getClass())) {
                return true;
            }
        }
        return false;
    }

    /**
     * Returns '<code>true</code>' for arrays,
     * {@link Collection Collections} and {@link Map Maps}.
     */
    @SuppressWarnings("unchecked")
    private static boolean typeRequiresMultiple(Class type) {
        return (type.isArray() || Collection.class.isAssignableFrom(type) || Map.class.isAssignableFrom(type));
    }

    /**
     * Closes any block tag that might have been opened when using nested
     * {@link OptionTag options}.
     */
    @Override
    public int doEndTag() throws JspException {
        if (this.tagWriter != null) {
            this.tagWriter.endTag();
            writeHiddenTagIfNecessary(tagWriter);
        }

        // hebei ignore
        // 添加Extjs脚本
        saveComponentScript(createComponentScript());
        return EVAL_PAGE;
    }

    /**
     * Clears the {@link TagWriter} that might have been left over when using
     * nested {@link OptionTag options}.
     */
    @Override
    public void doFinally() {
        super.doFinally();
        this.tagWriter = null;
        this.pageContext.removeAttribute(LIST_VALUE_PAGE_ATTRIBUTE);

    }

    // ---------------------------------------------------------------------------------------
    // 以上代码基本拷贝自org.springframework.web.servlet.tags.form.SelectTag
    // 下面代码为extjs扩展代码
    // ---------------------------------------------------------------------------------------
    @Override
    protected String createComponentScript() throws JspException {

        StringBuffer _sbuf = new StringBuffer();

        _sbuf.append(" var " + resolveId() + "ExtComboBox = new Ext.form.ComboBox({ ");
        _sbuf.append(" id: '" + resolveId() + "', ");
        _sbuf.append(" triggerAction: 'all', ");
        // 默认属性生成
        createComponentCommonAttributeScript(_sbuf);
        _sbuf.append(getOptionalAttributeScript(EDIT_ABLE_ATTRIBUTE, getEditable(), false));
        _sbuf.append(" transform: '" + resolveId() + "' ");
        _sbuf.append(" }); ");

        return _sbuf.toString();

    }

    @Override
    protected void writeDefaultAttributes(TagWriter tagWriter) throws JspException {
        writeOptionalAttribute(tagWriter, "id", resolveId());
        writeOptionalAttribute(tagWriter, "name", getName());
    }

    public static final String EDIT_ABLE_ATTRIBUTE = "editable";
    protected String editable = "false";

    public String getEditable() {
        return editable;
    }

    public void setEditable(String editable) {
        this.editable = editable;
    }
}
