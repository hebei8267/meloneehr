/*
 * Copyright 2008 by hebei, All rights reserved.
 */
package org.freedom.entity.privilege;

import java.util.HashSet;
import java.util.Set;

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

import org.freedom.core.entity.AbstractEntityBean;
import org.freedom.entity.common.User;
import org.hibernate.annotations.NaturalId;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 * 登录用户角色
 * 
 * @author 何贝
 * @since JDK1.5
 */
@Entity
@Table(name = "W_USER_ROLE")
@NamedQueries( {
        @NamedQuery(name = "Role.getRoleByID", query = "select obj from Role obj where obj.id = ? "),
        @NamedQuery(name = "Role.getMaxID", query = "select max(obj.id) from Role obj "),
        @NamedQuery(name = "Role.getRole4UserCount", query = "select r from Role r, User u where r.id = u.roleID and r.id in (?) ") })
public class Role extends AbstractEntityBean {

    private static final long serialVersionUID = 2814135309469292776L;
    /** 角色树更节点ID */
    public static String ROLE_TREE_ROOT_ID = "00000001";
    /** 系统管理员ID */
    public static String ADMIN_ROLE_ID = "00000002";

    /** 编号 */
    @NaturalId
    @Column(name = "ROLE_ID", nullable = false, length = 20)
    private String id;

    /** 名称 */
    @Basic
    @Column(name = "ROLE_NAME", nullable = false, length = 20)
    private String name;

    /** 描述 */
    @Basic
    @Column(name = "DESCRIPTION")
    private String detail;

    /** 登录用户Set */
    @OneToMany(mappedBy = "role", fetch = FetchType.LAZY)
    private Set<User> loginUserSet = new HashSet<User>();

    /** 子角色Set */
    @OneToMany(mappedBy = "parentRole", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Role> subRoleSet = new HashSet<Role>();

    /** 父角色 */
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "PARENT_ROLE_H_ID")
    private Role parentRole;

    /** 父角色ID */
    @Basic
    @Column(name = "PARENT_ROLE_ID", length = 20)
    private String parentRoleID;

    /**
     * 取得编号
     * 
     * @return 编号
     */
    public String getId() {
        return id;
    }

    /**
     * 设置编号
     * 
     * @param id 编号
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * 取得名称
     * 
     * @return 名称
     */
    public String getName() {
        return name;
    }

    /**
     * 设置名称
     * 
     * @param name 名称
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 取得描述
     * 
     * @return 描述
     */
    public String getDetail() {
        return detail;
    }

    /**
     * 设置描述
     * 
     * @param detail 描述
     */
    public void setDetail(String detail) {
        this.detail = detail;
    }

    /**
     * 取得登录用户Set
     * 
     * @return 登录用户Set
     */
    public Set<User> getLoginUserSet() {
        return loginUserSet;
    }

    /**
     * 设置登录用户Set
     * 
     * @param loginUserSet 登录用户Set
     */
    public void setLoginUserSet(Set<User> loginUserSet) {
        this.loginUserSet = loginUserSet;
    }

    /**
     * 取得子角色Set
     * 
     * @return 子角色Set
     */
    public Set<Role> getSubRoleSet() {
        return subRoleSet;
    }

    /**
     * 设置子角色Set
     * 
     * @param subRoleSet 子角色Set
     */
    public void setSubRoleSet(Set<Role> subRoleSet) {
        this.subRoleSet = subRoleSet;
    }

    /**
     * 取得父角色
     * 
     * @return 父角色
     */
    public Role getParentRole() {
        return parentRole;
    }

    /**
     * 设置父角色
     * 
     * @param parentRole 父角色
     */
    public void setParentRole(Role parentRole) {
        if (parentRole != null) {
            this.parentRoleID = parentRole.getId();
        }
        this.parentRole = parentRole;
    }

    /**
     * 取得父角色ID
     * 
     * @return 父角色ID
     */
    public String getParentRoleID() {
        return parentRoleID;
    }

    /**
     * 设置父角色ID
     * 
     * @param parentRoleID 父角色ID
     */
    protected void setParentRoleID(String parentRoleID) {
        this.parentRoleID = parentRoleID;
    }

    /**
     * @see java.lang.Object#equals(Object)
     */
    public boolean equals(Object object) {
        if (!(object instanceof Role)) {
            return false;
        }
        Role rhs = (Role) object;
        return new EqualsBuilder().append(this.id, rhs.id).isEquals();
    }

    /**
     * @see java.lang.Object#hashCode()
     */
    public int hashCode() {
        return new HashCodeBuilder(-971876911, -916451299).append(this.id).toHashCode();
    }

}
