/*
 * Copyright 2009 by hebei, All rights reserved.
 */
package org.freedom.web.servlet.tags.tree;

import javax.servlet.jsp.JspException;

import org.apache.commons.lang.StringUtils;
import org.freedom.view.SysConstant;
import org.freedom.web.servlet.tags.AbstractExtjsTag;
import org.springframework.web.servlet.tags.form.TagWriter;

/**
 * 普通树结构
 * 
 * @author 何贝
 * @since JDK1.5
 */
public class ExtjsTreeTag extends AbstractExtjsTag {

    /**
     * 
     */
    private static final long serialVersionUID = 7967695851078766673L;

    public static final String TITLE_ATTRIBUTE = "title";
    public static final String USEARROWS_ATTRIBUTE = "useArrows";
    public static final String ROOT_VISIBLE_ATTRIBUTE = "rootVisible";
    public static final String ROOT_NODE_ID_ATTRIBUTE = "rootNodeId";
    public static final String DATA_URL_ATTRIBUTE = "dataUrl";
    public static final String ROOT_ICON_ATTRIBUTE = "rootIcon";
    public static final String ALL_EXPAND_ATTRIBUTE = "allExpand";

    public static final String CLICK_EVENT = "click";

    protected String title;
    protected String useArrows = "true";
    protected String rootVisible = "true";
    protected String rootNodeId;
    protected String dataUrl;
    protected String rootIcon = "images/root.gif";
    protected String allExpand;

    protected String click;

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

    /**
     * @return
     * @throws JspException
     */
    @Override
    protected String createComponentScript() throws JspException {
        StringBuffer _sbuf = new StringBuffer();
        // 生产树结构对象
        _sbuf.append(" var " + resolveId() + "ExtTree = new Ext.tree.TreePanel({ ");
        _sbuf.append(" el: '" + resolveId() + "Div', ");
        _sbuf.append(" id: '" + resolveId() + "', ");
        _sbuf.append(getOptionalAttributeScript(TITLE_ATTRIBUTE, getTitle(), true));
        _sbuf.append(getOptionalAttributeScript(USEARROWS_ATTRIBUTE, getUseArrows(), false));
        _sbuf.append(getOptionalAttributeScript(ROOT_VISIBLE_ATTRIBUTE, getRootVisible(), false));
        _sbuf.append(getOptionalAttributeScript(WIDTH_ATTRIBUTE, getWidth(), false));
        _sbuf.append(getOptionalAttributeScript(HEIGHT_ATTRIBUTE, getHeight(), false));
        _sbuf.append(" animate: true, ");
        _sbuf.append(" enableDD: false, ");
        _sbuf.append(" containerScroll: true, ");
        _sbuf.append(" bodyBorder: false, ");
        _sbuf.append(" autoScroll: true ");
        _sbuf.append(" }); ");
        // 生成根节点
        _sbuf.append(" var " + resolveId() + "ExtTreeRoot = new Ext.tree.AsyncTreeNode({ ");
        _sbuf.append(" draggable: false, ");
        _sbuf.append(" id: '" + getRootNodeId() + "', ");
        _sbuf.append(" text: '树根节点', ");
        _sbuf.append(" icon: '" + SysConstant.WEB_PROJECT_NAME + "/" + getRootIcon() + "', ");
        _sbuf.append(" loader: new Ext.tree.TreeLoader({ ");
        _sbuf.append(" dataUrl:'" + SysConstant.WEB_PROJECT_NAME + "/" + getDataUrl() + "', ");
        _sbuf.append(" requestMethod : 'post', ");
        _sbuf.append(" listeners : { ");
        _sbuf.append(" loadexception : defaultAjaxRequestFailure ");
        _sbuf.append(" } ");
        _sbuf.append(" }) ");
        _sbuf.append(" }); ");
        // 渲染树结构
        _sbuf.append(" " + resolveId() + "ExtTree.setRootNode(" + resolveId() + "ExtTreeRoot); ");
        _sbuf.append(" " + resolveId() + "ExtTree.render(); ");
        // 根节点展开
        _sbuf.append(" " + resolveId() + "ExtTreeRoot.expand(); ");
        // 树节点全部展开
        if (Boolean.TRUE.toString().equals(getAllExpand())) {
            _sbuf.append(" " + resolveId() + "ExtTree.expandAll(); ");
        }
        // 树节点选中事件
        if (StringUtils.isNotBlank(click)) {
            _sbuf.append(" " + resolveId() + "ExtTree.on('click'," + getClick() + "); ");
        }
        return _sbuf.toString();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUseArrows() {
        return useArrows;
    }

    public void setUseArrows(String useArrows) {
        this.useArrows = useArrows;
    }

    public String getRootVisible() {
        return rootVisible;
    }

    public void setRootVisible(String rootVisible) {
        this.rootVisible = rootVisible;
    }

    public String getRootNodeId() {
        return rootNodeId;
    }

    public void setRootNodeId(String rootNodeId) {
        this.rootNodeId = rootNodeId;
    }

    public String getDataUrl() {
        return dataUrl;
    }

    public void setDataUrl(String dataUrl) {
        this.dataUrl = dataUrl;
    }

    public String getRootIcon() {
        return rootIcon;
    }

    public void setRootIcon(String rootIcon) {
        this.rootIcon = rootIcon;
    }

    public String getAllExpand() {
        return allExpand;
    }

    public void setAllExpand(String allExpand) {
        this.allExpand = allExpand;
    }

    public String getClick() {
        return click;
    }

    public void setClick(String click) {
        this.click = click;
    }

}
