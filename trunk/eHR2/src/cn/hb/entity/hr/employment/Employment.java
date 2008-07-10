package cn.hb.entity.hr.employment;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import cn.hb.core.bean.AbstractEntityBean;
import cn.hb.entity.dictionary.employment.EmployType;
import cn.hb.entity.hr.personnel.Person;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.hibernate.annotations.NaturalId;

/**
 * @author kaka
 * 
 * 雇佣
 */
@Entity
@Table(name = "W_EMPLOYMENT")
public class Employment extends AbstractEntityBean {

    private static final long serialVersionUID = 8269881616830421917L;

    public Employment() {
    }

    /** 个人编号 */
    private String personID;

    /** 员工编号 */
    private String employeeID;

    /** 个人基本信息 */
    private Person person;

    /** 员工 */
    private Employee employee;

    /** 雇佣类型ID */
    private String employTypeID;

    /** 雇佣类型 */
    private EmployType employType;

    /**
     * 取得个人编号
     * 
     * @return 个人编号
     */
    @NaturalId
    @Column(name = "PERSON_ID", nullable = false, length = 20)
    public String getPersonID() {
        return personID;
    }

    /**
     * 取得员工编号
     * 
     * @return 员工编号
     */
    @NaturalId
    @Column(name = "EMPLOYEE_ID", nullable = false, length = 20)
    public String getEmployeeID() {
        return employeeID;
    }

    /**
     * 取得个人基本信息
     * 
     * @return 个人基本信息
     */
    @ManyToOne
    @JoinColumn(name = "PERSON_H_ID")
    public Person getPerson() {
        return person;
    }

    /**
     * 取得员工
     * 
     * @return 员工
     */
    @ManyToOne
    @JoinColumn(name = "EMPLOYEE_H_ID")
    public Employee getEmployee() {
        return employee;
    }

    /**
     * 取得雇佣类型ID
     * 
     * @return 雇佣类型ID
     */
    @Basic
    @Column(name = "EMPLOY_TYPE_ID", length = 20)
    public String getEmployTypeID() {
        return employTypeID;
    }

    /**
     * 取得雇佣类型
     * 
     * @return 雇佣类型
     */
    @ManyToOne
    @JoinColumn(name = "EMPLOY_TYPE_H_ID")
    public EmployType getEmployType() {
        return employType;
    }

    /**
     * 设置个人编号
     * 
     * @param personID 个人编号
     */
    public void setPersonID(String personID) {
        this.personID = personID;
    }

    /**
     * 设置员工编号
     * 
     * @param employeeID 员工编号
     */
    public void setEmployeeID(String employeeID) {
        this.employeeID = employeeID;
    }

    /**
     * 设置个人基本信息
     * 
     * @param person 个人基本信息
     */
    public void setPerson(Person person) {
        this.person = person;
    }

    /**
     * 设置员工
     * 
     * @param employee 员工
     */
    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    /**
     * 设置雇佣类型ID
     * 
     * @param employTypeID 雇佣类型ID
     */
    public void setEmployTypeID(String employTypeID) {
        this.employTypeID = employTypeID;
    }

    /**
     * 设置雇佣类型
     * 
     * @param employType 雇佣类型
     */
    public void setEmployType(EmployType employType) {
        this.employType = employType;
    }

    /**
     * @see java.lang.Object#equals(Object)
     */
    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Employment)) {
            return false;
        }
        Employment rhs = (Employment) object;
        return new EqualsBuilder().append(this.employeeID, rhs.employeeID).append(this.personID, rhs.personID)
                .isEquals();
    }

    /**
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        return new HashCodeBuilder(467674797, 1711816965).append(this.employeeID).append(this.personID).toHashCode();
    }

}
