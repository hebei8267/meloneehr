package cn.hb.entity.hr.employment;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import cn.hb.core.bean.AbstractEntityBean;
import cn.hb.entity.dictionary.employment.EmployeeWorkState;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.hibernate.annotations.NaturalId;

/**
 * @author kaka
 * 
 * 员工
 */
@Entity
@Table(name = "W_EMPLOYEE")
public class Employee extends AbstractEntityBean {

    private static final long serialVersionUID = 737389932588189392L;

    public Employee() {
    }

    /** 编号 */
    private String id;

    /** 员工当前工作状态 */
    private String employeeWorkStateID;

    /** 员工当前工作状态 */
    private EmployeeWorkState employeeWorkState;

    /**
     * 取得编号
     * 
     * @return 编号
     */
    @NaturalId
    @Column(name = "EMPLOYEE_ID", nullable = false, length = 20)
    public String getId() {
        return id;
    }

    /**
     * 取得员工当前工作状态
     * 
     * @return 员工当前工作状态
     */
    @Basic
    @Column(name = "EMPLOYEE_WORK_STATE_ID", length = 20)
    public String getEmployeeWorkStateID() {
        return employeeWorkStateID;
    }

    /**
     * 取得员工当前工作状态
     * 
     * @return 员工当前工作状态
     */
    @ManyToOne
    @JoinColumn(name = "EMPLOYEE_WORK_STATE_H_ID")
    public EmployeeWorkState getEmployeeWorkState() {
        return employeeWorkState;
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
     * 设置员工当前工作状态
     * 
     * @param employeeWorkStateID 员工当前工作状态
     */
    public void setEmployeeWorkStateID(String employeeWorkStateID) {
        this.employeeWorkStateID = employeeWorkStateID;
    }

    /**
     * 设置员工当前工作状态
     * 
     * @param employeeWorkState 员工当前工作状态
     */
    public void setEmployeeWorkState(EmployeeWorkState employeeWorkState) {
        this.employeeWorkState = employeeWorkState;
    }

    /**
     * @see java.lang.Object#equals(Object)
     */
    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Employee)) {
            return false;
        }
        Employee rhs = (Employee) object;
        return new EqualsBuilder().append(this.id, rhs.id).isEquals();
    }

    /**
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        return new HashCodeBuilder(-123116371, 936439947).append(this.id).toHashCode();
    }

}
