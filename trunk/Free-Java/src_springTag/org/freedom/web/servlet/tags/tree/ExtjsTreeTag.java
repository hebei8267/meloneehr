/*
 * Copyright 2009 by hebei, All rights reserved.
 */
package org.freedom.web.servlet.tags.tree;

import javax.servlet.jsp.JspException;

import org.apache.commons.lang.StringUtils;
import org.freedom.sys.SysConstant;
import org.freedom.web.servlet.tags.AbstractExtjsTag;
import org.springframework.util.Assert;
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
    public static final String BASE_PARAMS_ATTRIBUTE = "baseParams";
    public static final String IS_CHECK_TREE_ATTRIBUTE = "isCheckTree";

    public static final String CLICK_EVENT = "click";

    protected String title;
    protected String useArrows = "true";
    protected String rootVisible = "true";
    protected String rootNodeId;
    protected String dataUrl;
    protected String rootIcon = "images/root.gif";
    protected String allExpand;
    protected String baseParams;
    protected String isCheckTree = "false";
    /**
     * 节点级联模式
     * 
     * 'cascade'和'parentCascade'和'childCascade'
     * 
     * 默认--'cascade'
     */
    protected String checkModel = "cascade";
    /**
     * 节点选中模式 默认--所有树结点都可选
     */
    protected String onlyLeafCheckable = "false";

    /**
     * 节点选择事件js函数
     */
    protected String clickFn;

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
     * 生产树结构对象
     * 
     * @param _sbuf
     * @throws JspException
     */
    private void createExtTreePanel(StringBuffer _sbuf) throws JspException {
        _sbuf.append(" var " + resolveId() + "ExtTree = new Ext.tree.TreePanel({ ");
        _sbuf.append(" el: '" + resolveId() + "Div', ");
        _sbuf.append(" id: '" + resolveId() + "', ");
        _sbuf.append(getOptionalAttributeScript(TITLE_ATTRIBUTE, getTitle(), true));
        _sbuf.append(getOptionalAttributeScript(USEARROWS_ATTRIBUTE, getUseArrows(), false));
        _sbuf.append(getOptionalAttributeScript(ROOT_VISIBLE_ATTRIBUTE, getRootVisible(), false));
        _sbuf.append(getOptionalAttributeScript(WIDTH_ATTRIBUTE, getWidth(), false));
        _sbuf.append(getOptionalAttributeScript(HEIGHT_ATTRIBUTE, getHeight(), false));
        // checkBoxTree特有参数
        if (StringUtils.isNotBlank(isCheckTree) && Boolean.TRUE.toString().equals(isCheckTree)) {
            _sbuf.append(" checkModel: '" + getCheckModel() + "', ");
            _sbuf.append(" onlyLeafCheckable: " + getOnlyLeafCheckable() + ", ");
        }
        _sbuf.append(" animate: true, ");
        _sbuf.append(" enableDD: false, ");
        _sbuf.append(" containerScroll: true, ");
        _sbuf.append(" bodyBorder: false, ");
        _sbuf.append(" autoScroll: true ");
        _sbuf.append(" }); ");
    }

    /**
     * 生成根节点
     * 
     * @param _sbuf
     * @throws JspException
     */
    private void createExtTreeRootNode(StringBuffer _sbuf) throws JspException {
        _sbuf.append(" var " + resolveId() + "ExtTreeRoot = new Ext.tree.AsyncTreeNode({ ");
        _sbuf.append(" draggable: false, ");
        _sbuf.append(" id: '" + getRootNodeId() + "', ");
        _sbuf.append(" text: '树根节点', ");
        _sbuf.append(" icon: '" + SysConstant.WEB_PROJECT_NAME + "/" + getRootIcon() + "', ");
        _sbuf.append(" loader: new Ext.tree.TreeLoader({ ");
        _sbuf.append(" dataUrl:'" + SysConstant.WEB_PROJECT_NAME + "/" + getDataUrl() + "', ");
        _sbuf.append(" requestMethod : 'post', ");
        // 树节点载入参数对象
        if (StringUtils.isNotBlank(baseParams)) {
            _sbuf.append(" baseParams : " + getBaseParams() + ", ");
        }
        // checkBoxTree参数
        if (StringUtils.isNotBlank(isCheckTree) && Boolean.TRUE.toString().equals(isCheckTree)) {
            _sbuf.append(" baseAttrs: { uiProvider: Ext.ux.TreeCheckNodeUI }, ");
        }
        _sbuf.append(" listeners : { ");
        _sbuf.append(" loadexception : defaultAjaxRequestFailure ");
        _sbuf.append(" } ");
        _sbuf.append(" }) ");
        _sbuf.append(" }); ");
    }

    /**
     * @return
     * @throws JspException
     */
    @Override
    protected String createComponentScript() throws JspException {
        StringBuffer _sbuf = new StringBuffer();
        // 生产树结构对象
        createExtTreePanel(_sbuf);
        // 生成根节点
        createExtTreeRootNode(_sbuf);

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
        if (StringUtils.isNotBlank(clickFn)) {
            _sbuf.append(" " + resolveId() + "ExtTree.on('click'," + getClickFn() + "); ");
        }
        return _sbuf.toString();
    }

    @Override
    public String getWidth() {
        Assert.hasText(dataUrl, "'width' must not be empty");
        return width;
    }

    @Override
    public String getHeight() {
        Assert.hasText(dataUrl, "'height' must not be empty");
        return height;
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
        Assert.hasText(dataUrl, "'rootNodeId' must not be empty");
        return rootNodeId;
    }

    public void setRootNodeId(String rootNodeId) {
        this.rootNodeId = rootNodeId;
    }

    public String getDataUrl() {
        Assert.hasText(dataUrl, "'dataUrl' must not be empty");
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

    public String getClickFn() {
        return clickFn;
    }

    public void setClickFn(String clickFn) {
        this.clickFn = clickFn;
    }

    public String getBaseParams() {
        return baseParams;
    }

    public void setBaseParams(String baseParams) {
        this.baseParams = baseParams;
    }

    public String getIsCheckTree() {
        return isCheckTree;
    }

    public void setIsCheckTree(String isCheckTree) {
        this.isCheckTree = isCheckTree;
    }

    public String getCheckModel() {
        return checkModel;
    }

    public void setCheckModel(String checkModel) {
        this.checkModel = checkModel;
    }

    public String getOnlyLeafCheckable() {
        return onlyLeafCheckable;
    }

    public void setOnlyLeafCheckable(String onlyLeafCheckable) {
        this.onlyLeafCheckable = onlyLeafCheckable;
    }

}
