/*
 * Copyright 2008 by hebei, All rights reserved.
 */
package org.freedom.entity.common;

import javax.persistence.Basic;
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

import org.freedom.entity.AbstractEntityBean;

import org.hibernate.annotations.NaturalId;

/**
 * 登录用户
 * 
 * @author 何贝
 * @since JDK1.5
 */
@Entity
@Table(name = "W_USER")
@NamedQueries( { @NamedQuery(name = "User.getUserByID", query = "select obj from User obj where obj.id = ? ") })
public class User extends AbstractEntityBean {

    private static final long serialVersionUID = -4154347867887707861L;

    public User() {
    }

    /** 编号 */
    @NaturalId
    @Column(name = "USER_ID", nullable = false, length = 20)
    private String id;

    /** 名称 */
    @Basic
    @Column(name = "USER_NAME", nullable = false, length = 20)
    private String name;

    /** 密码 */
    @Basic
    @Column(name = "PASS_WORD", length = 20)
    private String password;

    /** 第一次登录标记 */
    @Basic
    @Column(name = "FIRST_LOGIN_FLAG", nullable = false)
    private Boolean firstLoginFlag = true;

    /** 登录用户角色 */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ROLE_H_ID")
    private Role role;

    /** 登录用户角色ID */
    @Basic
    @Column(name = "ROLE_ID", nullable = false, length = 20)
    private String roleID;

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
     * 取得密码
     * 
     * @return 密码
     */
    public String getPassword() {
        return password;
    }

    /**
     * 设置密码
     * 
     * @param password 密码
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * 取得第一次登录标记
     * 
     * @return 第一次登录标记
     */
    public Boolean getFirstLoginFlag() {
        return firstLoginFlag;
    }

    /**
     * 设置第一次登录标记
     * 
     * @param firstLoginFlag 第一次登录标记
     */
    public void setFirstLoginFlag(Boolean firstLoginFlag) {
        this.firstLoginFlag = firstLoginFlag;
    }

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
     * @see java.lang.Object#equals(Object)
     */
    @Override
    public boolean equals(Object object) {
        if (!(object instanceof User)) {
            return false;
        }
        User rhs = (User) object;
        return new EqualsBuilder().append(this.id, rhs.id).isEquals();
    }

    /**
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        return new HashCodeBuilder(-1023797237, 1200053429).append(this.id).toHashCode();
    }

}
