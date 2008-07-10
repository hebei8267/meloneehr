package cn.hb.entity.hr.employment;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import cn.hb.core.bean.AbstractEntityBean;
import cn.hb.entity.dictionary.employment.ContractType;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.hibernate.annotations.NaturalId;

/**
 * @author kaka
 * 
 * 合同
 */
@Entity
@Table(name = "W_CONTRACT")
public class Contract extends AbstractEntityBean {

    private static final long serialVersionUID = -2484021397979308839L;

    public Contract() {
    }

    /** 编号 */
    private String id;

    /** 生效时间 */
    private String startDate;

    /** 失效时间 */
    private String endDate;

    /** 合同类型ID */
    private String contractTypeID;

    /** 合同类型 */
    private ContractType contractType;

    /** 雇佣ID */
    private String employmentID;

    /** 雇佣 */
    private Employment employment;

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
     * 取得合同类型ID
     * 
     * @return 合同类型ID
     */
    @Basic
    @Column(name = "CONTRACT_TYPE_ID", length = 20)
    public String getContractTypeID() {
        return contractTypeID;
    }

    /**
     * 取得合同类型
     * 
     * @return 合同类型
     */
    @ManyToOne
    @JoinColumn(name = "CONTRACT_TYPE_H_ID")
    public ContractType getContractType() {
        return contractType;
    }

    /**
     * 取得雇佣ID
     * 
     * @return 雇佣ID
     */
    @Basic
    @Column(name = "EMPLOYMENT_ID", length = 20)
    public String getEmploymentID() {
        return employmentID;
    }

    /**
     * 取得雇佣
     * 
     * @return 雇佣
     */
    @ManyToOne
    @JoinColumn(name = "EMPLOYMENT_H_ID")
    public Employment getEmployment() {
        return employment;
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
     * 设置合同类型ID
     * 
     * @param contractTypeID 合同类型ID
     */
    public void setContractTypeID(String contractTypeID) {
        this.contractTypeID = contractTypeID;
    }

    /**
     * 设置合同类型
     * 
     * @param contractType 合同类型
     */
    public void setContractType(ContractType contractType) {
        this.contractType = contractType;
    }

    /**
     * 设置雇佣ID
     * 
     * @param employmentID 雇佣ID
     */
    public void setEmploymentID(String employmentID) {
        this.employmentID = employmentID;
    }

    /**
     * 设置雇佣
     * 
     * @param employment 雇佣
     */
    public void setEmployment(Employment employment) {
        this.employment = employment;
    }

    /**
     * @see java.lang.Object#equals(Object)
     */
    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Contract)) {
            return false;
        }
        Contract rhs = (Contract) object;
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
