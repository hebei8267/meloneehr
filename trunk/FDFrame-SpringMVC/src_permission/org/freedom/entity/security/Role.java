/*
 * Copyright 2008 by hebei, All rights reserved.
 */
package org.freedom.entity.security;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.freedom.core.entity.AbstractEntityBean;
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
@Table(name = "W_LOGIN_USER_ROLE")
@NamedQueries( {
        @NamedQuery(name = "Role.getRoleByID", query = "select obj from Role obj where obj.id = ? "),
        @NamedQuery(name = "Role.getRoleListByMenuNodeID", query = "select robj from Role robj, MenuNodePermit pObj where robj.id = pObj.roleID and pObj.menuNodeID = ? ") })
public class Role extends AbstractEntityBean {

    private static final long serialVersionUID = 2814135309469292776L;
    /** 系统管理员ID */
    public static String ADMIN_ROLE_ID = "00000001";
    /** 编号 */
    private String id;

    /** 名称 */
    private String name;

    /** 登录用户Set */
    private Set<User> loginUserSet = new HashSet<User>();

    /**
     * 取得编号
     * 
     * @return 编号
     */
    @NaturalId
    @Column(name = "ROLE_ID", nullable = false, length = 20)
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
    @Basic
    @Column(name = "ROLE_NAME", nullable = false, length = 20)
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
     * 取得登录用户Set
     * 
     * @return 登录用户Set
     */
    @OneToMany(mappedBy = "role", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
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
