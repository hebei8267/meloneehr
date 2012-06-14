package index;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class TreeDataLoader {
    private Map<String, DomainTreeNode> treeData;

    public TreeDataLoader() {
        System.out.println("###### TreeDataLoader ");
        init();
    }

    private void init() {
        treeData = new HashMap<String, DomainTreeNode>();
        String path = TreeDataLoader.class.getClassLoader().getResource("Tree.xml").getFile();
        Document treeDocument = XMLHelper.read(path);
        Node root = treeDocument.getFirstChild();
        initTreeNodeDomain(root);
    }

    private DomainTreeNode initTreeNodeDomain(Node node) {

        if (node == null) {
            return null;
        }
        DomainTreeNode treeNode = new DomainTreeNode();

        NodeList children = node.getChildNodes();
        List<DomainTreeNode> childNodes = new LinkedList<DomainTreeNode>();

        for (int i = 0; i < children.getLength(); i++) {
            Node child = children.item(i);
            if ("userData".equals(child.getNodeName())) {
                treeNode.setUserData(child.getTextContent().trim());
            } else if ("text".equals(child.getNodeName())) {
                treeNode.setText(child.getTextContent().trim());
            } else if ("url".equals(child.getNodeName())) {
                treeNode.setUrl(child.getTextContent().trim());
            } else if ("expended".equals(child.getNodeName())) {
                treeNode.setExpended(Boolean.valueOf(child.getTextContent().trim()));
            } else if ("DomainTreeNode".equals(child.getNodeName())) {
                DomainTreeNode childNode = initTreeNodeDomain(child);
                childNode.setParent(treeNode);
                childNodes.add(childNode);
            }
        }
        treeNode.setChildren(childNodes);

        if (treeNode.getUserData() == null || treeNode.getUserData().length() == 0) {
            treeNode.setUserData(UUID.randomUUID().toString());
        }
        treeData.put(treeNode.getUserData(), treeNode);

        return treeNode;
    }

    public Map<String, DomainTreeNode> getTreeData() {
        return treeData;
    }

    // ----------------------------------------------------------------------------------
    public Object[] getChildren(Object userData) {
        Set<String> keySet = treeData.keySet();
        List<String> children = new LinkedList<String>();
        if (userData == null) {
            for (String key : keySet) {
                DomainTreeNode treeNode = treeData.get(key);
                if (treeNode.getParent() == null) {
                    children.add(treeNode.getUserData());
                }
            }
        } else {
            DomainTreeNode rcdemoTreeNode = treeData.get(userData.toString());
            if (rcdemoTreeNode != null && !rcdemoTreeNode.isLeaf()) {
                List<DomainTreeNode> childrenNodes = rcdemoTreeNode.getChildren();
                for (DomainTreeNode node : childrenNodes) {
                    children.add(node.getUserData());
                }
            }
        }
        return children.toArray();
    }

    public DomainTreeNode getTreeNode(Object userData) {
        return treeData.get(userData.toString());
    }

    public String getText(Object userData) {
        if (userData == null) {
            return null;
        }
        return treeData.get(userData.toString()).getText();
    }

    public boolean isExpended(Object userData) {
        if (userData == null) {
            return false;
        }
        return treeData.get(userData.toString()).isExpended();
    }
}
