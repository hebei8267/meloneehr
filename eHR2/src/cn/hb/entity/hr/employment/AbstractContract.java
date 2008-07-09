package cn.hb.entity.hr.employment;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

import cn.hb.core.bean.AbstractEntityBean;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.hibernate.annotations.NaturalId;

/**
 * @author kaka
 * 
 * 合同
 */
@MappedSuperclass
public abstract class AbstractContract extends AbstractEntityBean {

    public AbstractContract() {
    }

    /** 编号 */
    private String id;

    /** 生效时间 */
    private String startDate;

    /** 失效时间 */
    private String endDate;

    /**
     * 取得编号
     * 
     * @return 编号
     */
    @NaturalId
    @Column(name = "CONTRACT_ID", nullable = false, length = 20)
    public String getId() {
        return id;
    }

    /**
     * 取得生效时间
     * 
     * @return 生效时间
     */
    @NaturalId
    @Column(name = "START_DATE", nullable = false, length = 8)
    public String getStartDate() {
        return startDate;
    }

    /**
     * 取得失效时间
     * 
     * @return 失效时间
     */
    @Basic
    @Column(name = "END_DATE", nullable = false, length = 8)
    public String getEndDate() {
        return endDate;
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
     * 设置生效时间
     * 
     * @param startDate 生效时间
     */
    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    /**
     * 设置失效时间
     * 
     * @param endDate 失效时间
     */
    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    /**
     * @see java.lang.Object#equals(Object)
     */
    @Override
    public boolean equals(Object object) {
        if (!(object instanceof AbstractContract)) {
            return false;
        }
        AbstractContract rhs = (AbstractContract) object;
        return new EqualsBuilder().append(this.id, rhs.id).isEquals();
    }

    /**
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        return new HashCodeBuilder(-1135512825, 772307165).append(this.id).toHashCode();
    }

}
