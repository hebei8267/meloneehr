/*
 * Copyright 2008 by hebei, All rights reserved.
 */
package org.freedom.entity;

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
    @Id
    @Column(name = "H_ID", nullable = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Integer hid;

    /** CreateDate_Timestamp */
    @Basic
    @Column(name = "CREATE_DATE", nullable = false, updatable = false)
    protected Timestamp createDate;

    /** Create_User_ID */
    @Basic
    @Column(name = "CREATE_USER_ID", nullable = false, length = 20, updatable = false)
    protected String createUserId;

    /** Update_Timestamp */
    @Basic
    @Column(name = "UPDATE_DATE", nullable = false, updatable = true)
    protected Timestamp updateDate;

    /** Update_User_ID */
    @Basic
    @Column(name = "UPDATE_USER_ID", nullable = false, length = 20, updatable = true)
    protected String updateUserId;

    /** Hibernate_Version */
    @Version
    @Column(name = "VERSION", nullable = false)
    protected Integer version;

    public Integer getHid() {
        return hid;
    }

    protected void setHid(Integer hid) {
        this.hid = hid;
    }

    public Timestamp getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Timestamp createDate) {
        this.createDate = createDate;
    }

    public String getCreateUserId() {
        return createUserId;
    }

    public void setCreateUserId(String createUserId) {
        this.createUserId = createUserId;
    }

    public String getUpdateUserId() {
        return updateUserId;
    }

    public void setUpdateUserId(String updateUserId) {
        this.updateUserId = updateUserId;
    }

    public Timestamp getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Timestamp updateDate) {
        this.updateDate = updateDate;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    @Override
    public abstract int hashCode();

    @Override
    public abstract boolean equals(Object obj);

    public AbstractEntityBean() {

    }
}
