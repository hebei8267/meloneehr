package cn.hb.core.bean;

import java.sql.Timestamp;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Version;

@MappedSuperclass
public abstract class AbstractEntityBean extends BaseBean {
    /** Hibernate_ID */
    private Integer hID;
    /** CreateDate_Timestamp */
    private Timestamp createDate;
    /** Create_User_ID */
    private String createUserId;
    /** Update_Timestamp */
    private Timestamp updateDate;
    /** Update_User_ID */
    private String updateUserId;
    /** Hibernate_Version */
    private Integer version;

    @Id
    @Column(name = "H_ID", nullable = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer getHID() {
        return hID;
    }

    public void setHID(Integer hid) {
        this.hID = hid;
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
