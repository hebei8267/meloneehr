package cn.hb.entity.dictionary.financial;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

import cn.hb.entity.dictionary.MasterEntityBean;

/**
 * @author kaka
 * 
 * 职位-薪酬福利关联类型(排斥/非排斥-关联)
 */
@Entity
@DiscriminatorValue(value = "Job_Salary_RelateType")
@NamedQueries( { @NamedQuery(name = "Job_Salary_RelateType.getJob_Salary_RelateTypeByID", query = "select obj from Job_Salary_RelateType obj where obj.masterID = ? And obj.slaveID = ? ") })
public class Job_Salary_RelateType extends MasterEntityBean {

    private static final long serialVersionUID = -4245036351235403763L;

    public final static String MASTER_ID = "0001";

    public Job_Salary_RelateType() {

    }
}
