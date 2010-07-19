package com.jquery.core.modules.tree;


/**
 * CheckBox节点其他属性
 * 
 * @since JDK1.5
 */
public class CheckBoxTreeNodeAttributes extends DefaultTreeNodeAttributes {

    /** 节点checkbox的名字,kType为false时无效果 */
    protected String ckName = "_noCkName";

    /** 节点checkbox是否选中,默认不选中,ckType为false时无效果 */
    protected boolean ckChecked = false;

    public CheckBoxTreeNodeAttributes() {

    }

    public CheckBoxTreeNodeAttributes(String url, String ckName, boolean ckChecked) {
        this.url = url;
        this.ckName = ckName;
        this.ckChecked = ckChecked;
    }

    public CheckBoxTreeNodeAttributes(String parentNodeID, String url, String ckName, boolean ckChecked) {
        this.parentNodeID = parentNodeID;
        this.url = url;
        this.ckName = ckName;
        this.ckChecked = ckChecked;
    }

    public String getCkName() {
        return ckName;
    }

    public void setCkName(String ckName) {
        this.ckName = ckName;
    }

    public boolean isCkChecked() {
        return ckChecked;
    }

    public void setCkChecked(boolean ckChecked) {
        this.ckChecked = ckChecked;
    }
}
