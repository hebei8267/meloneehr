package com.ppiaobuy.modules.hibernate.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.Version;


/**
 * 含审计信息的Entity基类.
 * 
 * @author calvin
 */
@MappedSuperclass
public abstract class AuditableEntity extends IdEntity {

    protected Date createTime;
    protected String createBy;
    protected Date lastModifyTime;
    protected String lastModifyBy;
    private Integer version;

    /**
     * 创建时间.
     */
    // 本属性只在save时有效,update时无效.
    @Column(updatable = false)
    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 创建的操作员的登录名.
     */
    @Column(updatable = false)
    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    /**
     * 最后修改时间.
     */
    // 本属性只在update时有效,save时无效.
    @Column(insertable = false)
    public Date getLastModifyTime() {
        return lastModifyTime;
    }

    public void setLastModifyTime(Date lastModifyTime) {
        this.lastModifyTime = lastModifyTime;
    }

    /**
     * 最后修改的操作员的登录名.
     */
    @Column(insertable = false)
    public String getLastModifyBy() {
        return lastModifyBy;
    }

    public void setLastModifyBy(String lastModifyBy) {
        this.lastModifyBy = lastModifyBy;
    }

    // Hibernate自动维护的Version字段
    @Version
    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }
}
