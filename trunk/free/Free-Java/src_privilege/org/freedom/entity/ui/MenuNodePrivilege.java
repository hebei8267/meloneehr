/*
 * Copyright 2008 by hebei, All rights reserved.
 */
package org.freedom.entity.ui;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.freedom.core.entity.AbstractEntityBean;
import org.freedom.entity.privilege.Role;
import org.hibernate.annotations.NaturalId;

/**
 * 角色可访问菜单树结点权限
 * 
 * @author 何贝
 * @since JDK1.5
 */
@Entity
@Table(name = "W_MENU_NODE_PRIVILEGE")
@NamedQueries( {
        @NamedQuery(name = "MenuNodePrivilege.getMenuNodePrivilegeListByUserID", query = "select pObj.menuNodeID from MenuNodePrivilege pObj, User uObj where uObj.id = ? and uObj.roleID = pObj.roleID "),
        @NamedQuery(name = "MenuNodePrivilege.getMenuNodePrivilegeByRoleIDAndMenuNodeID", query = "select pObj from MenuNodePrivilege pObj where pObj.roleID = ? and pObj.menuNodeID = ? "),
        @NamedQuery(name = "MenuNodePrivilege.delMenuNodePrivilegeByMenuNodeID", query = "delete MenuNodePrivilege pObj where pObj.menuNodeID = ? ") })
public class MenuNodePrivilege extends AbstractEntityBean {

    private static final long serialVersionUID = -350595423265400452L;

    /** 登录用户角色 */
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ROLE_H_ID")
    private Role role;

    /** 登录用户角色ID */
    @NaturalId
    @Column(name = "ROLE_ID", length = 20)
    private String roleID;

    /** 菜单树结点 */
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "NODE_H_ID")
    private MenuNode menuNode;

    /** 菜单树结点ID */
    @NaturalId
    @Column(name = "NODE_ID", length = 20)
    private String menuNodeID;

    /**
     * 取得登录用户角色
     * 
     * @return 登录用户角色
     */
    public Role getRole() {
        return role;
    }

    /**
     * 设置登录用户角色
     * 
     * @param role 登录用户角色
     */
    public void setRole(Role role) {
        if (role != null) {
            this.roleID = role.getId();
        }
        this.role = role;
    }

    /**
     * 取得登录用户角色ID
     * 
     * @return 登录用户角色ID
     */
    public String getRoleID() {
        return roleID;
    }

    /**
     * 设置登录用户角色ID
     * 
     * @param roleID 登录用户角色ID
     */
    protected void setRoleID(String roleID) {
        this.roleID = roleID;
    }

    /**
     * 取得菜单树结点
     * 
     * @return 菜单树结点
     */
    public MenuNode getMenuNode() {
        return menuNode;
    }

    /**
     * 设置菜单树结点
     * 
     * @param menuNode 菜单树结点
     */
    public void setMenuNode(MenuNode menuNode) {
        if (menuNode != null) {
            this.menuNodeID = menuNode.getId();
        }
        this.menuNode = menuNode;
    }

    /**
     * 取得菜单树结点ID
     * 
     * @return 菜单树结点ID
     */
    public String getMenuNodeID() {
        return menuNodeID;
    }

    /**
     * 设置菜单树结点ID
     * 
     * @param menuNodeID 菜单树结点ID
     */
    protected void setMenuNodeID(String menuNodeID) {
        this.menuNodeID = menuNodeID;
    }

    /**
     * @see java.lang.Object#equals(Object)
     */
    public boolean equals(Object object) {
        if (!(object instanceof MenuNodePrivilege)) {
            return false;
        }
        MenuNodePrivilege rhs = (MenuNodePrivilege) object;
        return new EqualsBuilder().append(this.menuNodeID, rhs.menuNodeID).append(this.roleID, rhs.roleID).isEquals();
    }

    /**
     * @see java.lang.Object#hashCode()
     */
    public int hashCode() {
        return new HashCodeBuilder(2041415427, 1675002413).append(this.menuNodeID).append(this.roleID).toHashCode();
    }
}
