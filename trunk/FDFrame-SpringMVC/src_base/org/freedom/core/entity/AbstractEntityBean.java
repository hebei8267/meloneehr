/*
 * Copyright 2008 by hebei, All rights reserved.
 */
package org.freedom.core.entity;

import java.sql.Timestamp;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Version;

import org.freedom.core.bean.BaseBean;

/**
 * 公共Entity对象基类
 * 
 * @author 何贝
 * @since JDK1.5
 */
@MappedSuperclass
public abstract class AbstractEntityBean extends BaseBean {

    private static final long serialVersionUID = 648082607776880707L;
    /** Hibernate_ID */
    protected Integer hid;
    /** CreateDate_Timestamp */
    protected Timestamp createDate;
    /** Create_User_ID */
    protected String createUserId;
    /** Update_Timestamp */
    protected Timestamp updateDate;
    /** Update_User_ID */
    protected String updateUserId;
    /** Hibernate_Version */
    protected Integer version;

    @Id
    @Column(name = "H_ID", nullable = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer getHid() {
        return hid;
    }

    public void setHid(Integer hid) {
        this.hid = hid;
    }

    @Basic
    @Column(name = "CREATE_DATE", nullable = false, updatable = false)
    public Timestamp getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Timestamp createDate) {
        this.createDate = createDate;
    }

    @Basic
    @Column(name = "CREATE_USER_ID", nullable = false, length = 20, updatable = false)
    public String getCreateUserId() {
        return createUserId;
    }

    public void setCreateUserId(String createUserId) {
        this.createUserId = createUserId;
    }

    @Basic
    @Column(name = "UPDATE_USER_ID", nullable = false, length = 20)
    public String getUpdateUserId() {
        return updateUserId;
    }

    public void setUpdateUserId(String updateUserId) {
        this.updateUserId = updateUserId;
    }

    @Basic
    @Column(name = "UPDATE_DATE", nullable = false)
    public Timestamp getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Timestamp updateDate) {
        this.updateDate = updateDate;
    }

    @Version
    @Column(name = "VERSION", nullable = false)
    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public abstract int hashCode();

    public abstract boolean equals(Object obj);

    public AbstractEntityBean() {

    }
}
