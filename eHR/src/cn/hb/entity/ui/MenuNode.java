package cn.hb.entity.ui;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.IndexColumn;
import org.hibernate.annotations.NaturalId;

import cn.hb.core.bean.AbstractEntityBean;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 * @author kaka
 * 
 * 菜单树结点
 */
@Entity
@Table(name = "W_UI_MENU_NODE")
@NamedQueries( { @NamedQuery(name = "MenuNode.getMenuNodeByID", query = "select obj from MenuNode obj where obj.id = ? ") })
public class MenuNode extends AbstractEntityBean {

    private static final long serialVersionUID = -7186864941977613879L;

    public MenuNode() {
    }

    /** 节点编号 */
    private String id;

    /** 节点描述 */
    private String nodeTxt;

    /** 节点类型 */
    private String nodeType;

    /** 默认权限 "true"无访问限制 "false"有访问限制 */
    private Boolean defaultPermit = Boolean.TRUE;

    /** 页面迁移内容 */
    private String actionContent;

    /** Index */
    private Integer index;

    /** 子节点 */
    private List<MenuNode> subNodeList = new ArrayList<MenuNode>();

    /** 父节点 */
    private MenuNode parentNode;

    /** 父节点ID */
    private String parentNodeID;

    /**
     * 取得节点编号
     * 
     * @return 节点编号
     */
    @NaturalId
    @Column(name = "NODE_ID", nullable = false, length = 20)
    public String getId() {
        return id;
    }

    /**
     * 取得节点描述
     * 
     * @return 节点描述
     */
    @Basic
    @Column(name = "NODE_TEXT", nullable = false)
    public String getNodeTxt() {
        return nodeTxt;
    }

    /**
     * 取得节点类型
     * 
     * @return 节点类型
     */
    @Basic
    @Column(name = "NODE_TYPE", nullable = false, length = 20)
    public String getNodeType() {
        return nodeType;
    }

    /**
     * 取得默认权限
     * 
     * @return 默认权限
     */
    @Basic
    @Column(name = "DEFAULT_PERMIT", nullable = false)
    public Boolean getDefaultPermit() {
        return defaultPermit;
    }

    /**
     * 页面迁移内容
     * 
     * @return 页面迁移内容
     */
    @Basic
    @Column(name = "ACTION_CONTENT", nullable = false)
    public String getActionContent() {
        return actionContent;
    }

    @Basic
    @Column(name = "_INDEX")
    public Integer getIndex() {
        return index;
    }

    @OneToMany(mappedBy = "parentNode", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @IndexColumn(name = "_INDEX", base = 1)
    public List<MenuNode> getSubNodeList() {
        return subNodeList;
    }

    @ManyToOne
    @JoinColumn(name = "PARENT_NODE_H_ID")
    public MenuNode getParentNode() {
        return parentNode;
    }

    @Basic
    @Column(name = "PARENT_NODE_ID", length = 20)
    public String getParentNodeID() {
        return parentNodeID;
    }

    /**
     * 设置节点编号
     * 
     * @param id 节点编号
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * 设置节点描述
     * 
     * @param nodeTxt 节点描述
     */
    public void setNodeTxt(String nodeTxt) {
        this.nodeTxt = nodeTxt;
    }

    /**
     * 设置节点类型
     * 
     * @param nodeType 节点类型
     */
    public void setNodeType(String nodeType) {
        this.nodeType = nodeType;
    }

    /**
     * 设置默认权限
     * 
     * @param defaultPermit 默认权限
     */
    public void setDefaultPermit(Boolean defaultPermit) {
        this.defaultPermit = defaultPermit;
    }

    /**
     * 页面迁移内容
     * 
     * @return 页面迁移内容
     */
    public void setActionContent(String actionContent) {
        this.actionContent = actionContent;
    }

    protected void setIndex(Integer index) {
        this.index = index;
    }

    public void addSubNode(MenuNode menuNode) {
        int index = 1;
        for (MenuNode _menuNode : subNodeList) {
            if (_menuNode.getIndex() != null) {
                _menuNode.setIndex(index);
                index++;
            }
        }

        menuNode.setIndex(index);
        this.subNodeList.add(menuNode);
    }

    public void setSubNodeList(List<MenuNode> subNodeList) {
        this.subNodeList = subNodeList;
    }

    public void setParentNode(MenuNode parentNode) {
        if (parentNode != null) {
            this.parentNodeID = parentNode.getId();
        }
        this.parentNode = parentNode;
    }

    protected void setParentNodeID(String parentNodeID) {
        this.parentNodeID = parentNodeID;
    }

    /**
     * @see java.lang.Object#equals(Object)
     */
    @Override
    public boolean equals(Object object) {
        if (!(object instanceof MenuNode)) {
            return false;
        }
        MenuNode rhs = (MenuNode) object;
        return new EqualsBuilder().append(this.id, rhs.id).isEquals();
    }

    /**
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        return new HashCodeBuilder(586758675, 673926573).append(this.id).toHashCode();
    }
}