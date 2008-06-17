package cn.hb.entity.hr.personnel;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.hibernate.annotations.NaturalId;

import cn.hb.core.bean.AbstractEntityBean;

/**
 * @author kaka
 * 
 * 证件类型
 */
@Entity
@Table(name = "M_CARD_KIND")
public class CardKind extends AbstractEntityBean {

    private static final long serialVersionUID = -635217804064150072L;

    public CardKind() {
    }

    /** 编号 */
    private String masterID;

    /** 编号 */
    private String slaveID;

    /** 名称 */
    private String name;

    /** 详细描述 */
    private String description;

    /**
     * 取得编号
     * 
     * @return 编号
     */
    @NaturalId
    @Column(name = "CARD_KIND_MASTER_ID", nullable = false, length = 20)
    public String getMasterID() {
        return masterID;
    }

    @NaturalId
    @Column(name = "CARD_KIND_STATE_SLAVE_ID", nullable = false, length = 20)
    public String getSlaveID() {
        return slaveID;
    }

    /**
     * 取得名称
     * 
     * @return 名称
     */
    @Basic
    @Column(name = "NAME", nullable = false, length = 20, unique = true)
    public String getName() {
        return name;
    }

    /**
     * 取得详细描述
     * 
     * @return 详细描述
     */
    @Basic
    @Column(name = "DESCRIPTION")
    public String getDescription() {
        return description;
    }

    /**
     * 设置编号
     * 
     * @param id 编号
     */
    public void setMasterID(String masterID) {
        this.masterID = masterID;
    }

    public void setSlaveID(String slaveID) {
        this.slaveID = slaveID;
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
     * 设置详细描述
     * 
     * @param description 详细描述
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @see java.lang.Object#equals(Object)
     */
    @Override
    public boolean equals(Object object) {
        if (!(object instanceof CardKind)) {
            return false;
        }
        CardKind rhs = (CardKind) object;
        return new EqualsBuilder().append(this.masterID, rhs.masterID).append(this.slaveID, rhs.slaveID).isEquals();
    }

    /**
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        return new HashCodeBuilder(1251339031, 1116860751).append(this.masterID).append(this.slaveID).toHashCode();
    }

}
