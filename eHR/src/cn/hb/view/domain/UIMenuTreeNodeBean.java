package cn.hb.view.domain;

/**
 * 2008/01/24
 * 
 * @author 何 貝
 * 
 * UI树节点
 */
public class UIMenuTreeNodeBean extends AbstractUITreeNodeBean<UIMenuTreeNodeBean> {
    /***/
    private static final long serialVersionUID = 2755754613221738481L;
    /** 节点 ID */
    private String nodeID;
    /** 节点内容 */
    private String nodeTxt;
    /** Menu Node Action Content */
    private String actionContent;
    /** 节点类型 */
    private String type;

    public UIMenuTreeNodeBean() {

    }

    public UIMenuTreeNodeBean(String nodeID, String nodeTxt, String nodeType) {
        this.nodeID = nodeID;
        this.nodeTxt = nodeTxt;
        this.type = nodeType;
    }

    public UIMenuTreeNodeBean(String nodeID, String nodeTxt, String nodeType, String actionContent) {
        this.nodeID = nodeID;
        this.nodeTxt = nodeTxt;
        this.type = nodeType;
        this.actionContent = actionContent;
    }

    public String getNodeID() {
        return nodeID;
    }

    public String getNodeTxt() {
        return nodeTxt;
    }

    public String getActionContent() {
        return actionContent;
    }

    public String getType() {
        return type;
    }

    public void setNodeID(String nodeID) {
        this.nodeID = nodeID;
    }

    public void setNodeTxt(String nodeTxt) {
        this.nodeTxt = nodeTxt;
    }

    public void setActionContent(String actionContent) {
        this.actionContent = actionContent;
    }

    public void setType(String type) {
        this.type = type;
    }

}
