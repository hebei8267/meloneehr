/*
 * Copyright 2008 by hebei, All rights reserved.
 */
package org.freedom.entity;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.hibernate.annotations.NaturalId;

/**
 * 所有定数表类的基类--使用同一个表结构
 * 
 * @author 何贝
 * @since JDK1.5
 */
@Entity
@Table(name = "M_DICTIONARY")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "ENTITY_TYPE", discriminatorType = DiscriminatorType.STRING, length = 50)
public class MasterEntityBean extends AbstractEntityBean {

    private static final long serialVersionUID = 5975649509846798796L;

    /** 编号 */
    @NaturalId
    @Column(name = "MASTER_ID", nullable = false, length = 10)
    private String masterID;

    /** 编号 */
    @NaturalId
    @Column(name = "SLAVE_ID", nullable = false, length = 10)
    private String slaveID;

    /** 名称 */
    @Basic
    @Column(name = "NAME", nullable = false, length = 20)
    private String name;

    /** 详细描述 */
    @Basic
    @Column(name = "DESCRIPTION")
    private String detail;

    /**
     * 取得编号
     * 
     * @return 编号
     */
    public String getMasterID() {
        return masterID;
    }

    /**
     * 设置编号
     * 
     * @param masterID 编号
     */
    public void setMasterID(String masterID) {
        this.masterID = masterID;
    }

    /**
     * 取得编号
     * 
     * @return 编号
     */
    public String getSlaveID() {
        return slaveID;
    }

    /**
     * 设置编号
     * 
     * @param slaveID 编号
     */
    public void setSlaveID(String slaveID) {
        this.slaveID = slaveID;
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
     * 取得详细描述
     * 
     * @return 详细描述
     */
    public String getDetail() {
        return detail;
    }

    /**
     * 设置详细描述
     * 
     * @param detail 详细描述
     */
    public void setDetail(String detail) {
        this.detail = detail;
    }

    /**
     * @see java.lang.Object#equals(Object)
     */
    @Override
    public boolean equals(Object object) {
        if (!(object instanceof MasterEntityBean)) {
            return false;
        }
        MasterEntityBean rhs = (MasterEntityBean) object;
        return new EqualsBuilder().append(this.slaveID, rhs.slaveID).append(this.masterID, rhs.masterID).isEquals();
    }

    /**
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        return new HashCodeBuilder(-457225123, -775102081).append(this.slaveID).append(this.masterID).toHashCode();
    }
}
