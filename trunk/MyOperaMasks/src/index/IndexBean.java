package index;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.faces.event.PhaseEvent;

import org.operamasks.faces.annotation.Action;
import org.operamasks.faces.annotation.AfterPhase;
import org.operamasks.faces.annotation.BeforePhase;
import org.operamasks.faces.annotation.Bind;
import org.operamasks.faces.annotation.ManagedBean;
import org.operamasks.faces.annotation.ManagedBeanScope;
import org.operamasks.faces.component.html.impl.UIIFrame;
import org.operamasks.faces.component.layout.impl.UIPanel;
import org.operamasks.faces.component.tree.base.TreeDataProvider;
import org.operamasks.faces.component.tree.impl.UITree;
import org.operamasks.faces.component.tree.impl.UITreeNode;

@ManagedBean(name = "IndexBean", scope = ManagedBeanScope.REQUEST)
public class IndexBean {
    public IndexBean() {
        System.out.println("IndexBean");
        // if (menuTreeDataProvider == null) {
        // System.out.println("%%%%%% menuTreeDataProvider ");
        // menuTreeDataProvider = new menuTreeDataProvider();
        // }
    }

    @Bind
    private UITree menuTree;
    @Bind
    private UIPanel workPanel;
    @Bind
    private UIIFrame workFrame;

    @Bind(id = "menuTree")
    private TreeDataProvider menuTreeDataProvider = new menuTreeDataProvider();

    @Action
    public void menuTree_onselect() {
        System.out.println("menuTree_onselect");
        UITreeNode node = menuTree.getEventNode();
        workPanel.setTitle(node.getText() + "  TEST");
        workFrame.load(node.getHrefTarget());

        System.out.println("######## " + node.getHrefTarget());
    }

    @AfterPhase
    public void afterPhase(PhaseEvent event) {
        System.out.println(event.getPhaseId().toString() + "阶段结束");
    }

    @BeforePhase
    public void beforePhase(PhaseEvent event) {
        System.out.println("----@BeforePhase---"+event.getPhaseId()+"--------");
        System.out.println(event.getPhaseId().toString() + "阶段开始");
    }

    private Map<String, DomainTreeNode> treeData;

    class menuTreeDataProvider implements TreeDataProvider {

        public menuTreeDataProvider() {
            if (treeData == null) {
                treeData = new TreeDataLoader().getTreeData();
                System.out.println("######## menuTreeDataProvider");
            } else {
                System.out.println("######## menuTreeDataProvider no init");
            }
        }

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

        public String getHref(Object userData) {
            return null;
        }

        public String getHrefTarget(Object userData) {
            return treeData.get(userData.toString()).getUrl();
        }

        public String getIcon(Object userData) {
            return "";
        }

        public String getText(Object userData) {
            return treeData.get(userData.toString()).getText();
        }

        public boolean isCascade(Object userData) {
            return false;
        }

        public Boolean isChecked(Object userData) {
            return null;
        }

        public boolean isExpanded(Object userData) {
            return treeData.get(userData.toString()).isExpended();
        }
    }
}
