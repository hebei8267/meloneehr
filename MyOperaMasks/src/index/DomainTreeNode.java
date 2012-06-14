package index;

import java.util.LinkedList;
import java.util.List;

public class DomainTreeNode {
    private String userData;
    private String text;
    private String url;
    private boolean expended;
    private DomainTreeNode parent;
    private List<DomainTreeNode> children;

    public DomainTreeNode() {

    }

    public DomainTreeNode(String userData, String text) {
        this.userData = userData;
        this.text = text;
    }

    public DomainTreeNode(String userData, String text, String url) {
        this.userData = userData;
        this.text = text;
        this.url = url;
    }

    public DomainTreeNode(String userData, String text, String url, boolean expended, DomainTreeNode parent, List<DomainTreeNode> children) {
        this.userData = userData;
        this.text = text;
        this.url = url;
        this.expended = expended;
        this.parent = parent;
        this.children = children;
    }

    public void add(DomainTreeNode node) {
        node.setParent(this);
        if (this.children == null) {
            this.children = new LinkedList<DomainTreeNode>();
        }
        this.children.add(node);
    }

    public String getUserData() {
        return userData;
    }

    public void setUserData(String userData) {
        this.userData = userData;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public DomainTreeNode getParent() {
        return parent;
    }

    public void setParent(DomainTreeNode parent) {
        this.parent = parent;
    }

    public List<DomainTreeNode> getChildren() {
        return children;
    }

    public void setChildren(List<DomainTreeNode> children) {
        this.children = children;
    }

    public boolean isLeaf() {
        if (this == null) {
            return false;
        } else if (this.children == null || this.children.size() == 0) {
            return true;
        } else {
            return false;
        }
    }

    public boolean isExpended() {
        return expended;
    }

    public void setExpended(boolean expended) {
        this.expended = expended;
    }
}
