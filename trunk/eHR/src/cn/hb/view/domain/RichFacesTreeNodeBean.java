package cn.hb.view.domain;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.richfaces.model.TreeNode;

/**
 * 2008/01/24
 * 
 * @author 何 貝
 * 
 * UI树节点
 */
public class RichFacesTreeNodeBean implements TreeNode<RichFacesTreeNodeBean> {
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
    /** 父节点 */
    private TreeNode<RichFacesTreeNodeBean> parent;
    /** 子节点Map */
    private Map<Object, TreeNode<RichFacesTreeNodeBean>> childrenMap = new LinkedHashMap<Object, TreeNode<RichFacesTreeNodeBean>>();

    public RichFacesTreeNodeBean() {

    }

    public RichFacesTreeNodeBean(String nodeID, String nodeTxt, String nodeType) {
        this.nodeID = nodeID;
        this.nodeTxt = nodeTxt;
        this.type = nodeType;
    }

    public RichFacesTreeNodeBean(String nodeID, String nodeTxt, String nodeType, String actionContent) {
        this.nodeID = nodeID;
        this.nodeTxt = nodeTxt;
        this.type = nodeType;
        this.actionContent = actionContent;
    }

    public TreeNode<RichFacesTreeNodeBean> getChild(Object identifier) {
        return (TreeNode<RichFacesTreeNodeBean>) childrenMap.get(identifier);
    }

    public Iterator<Entry<Object, TreeNode<RichFacesTreeNodeBean>>> getChildren() {
        return childrenMap.entrySet().iterator();
    }

    public RichFacesTreeNodeBean getData() {
        return this;
    }

    public TreeNode<RichFacesTreeNodeBean> getParent() {
        return parent;
    }

    public boolean isLeaf() {
        return childrenMap.isEmpty();
    }

    public void removeChild(Object identifier) {
        TreeNode<RichFacesTreeNodeBean> treeNode = (TreeNode<RichFacesTreeNodeBean>) childrenMap.remove(identifier);
        if (treeNode != null) {
            treeNode.setParent(null);
        }
    }

    public String getNodeID() {
        return nodeID;
    }

    public void setNodeID(String nodeID) {
        this.nodeID = nodeID;
    }

    public String getNodeTxt() {
        return nodeTxt;
    }

    public void setNodeTxt(String nodeTxt) {
        this.nodeTxt = nodeTxt;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getActionContent() {
        return actionContent;
    }

    public void setActionContent(String actionContent) {
        this.actionContent = actionContent;
    }

    @Override
    public void addChild(Object identifier, TreeNode<RichFacesTreeNodeBean> child) {
        child.setParent(this);
        childrenMap.put(identifier, child);
    }

    @Override
    public void setData(RichFacesTreeNodeBean data) {
        // empty imp
    }

    @Override
    public void setParent(TreeNode<RichFacesTreeNodeBean> parent) {
        this.parent = (TreeNode<RichFacesTreeNodeBean>) parent;
    }
}
