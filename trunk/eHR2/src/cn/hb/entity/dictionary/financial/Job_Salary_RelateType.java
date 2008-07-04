package cn.hb.entity.dictionary.financial;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import cn.hb.entity.dictionary.MasterEntityBean;

/**
 * @author kaka
 * 
 * 职位-薪酬福利关联类型(排斥/非排斥-关联)
 */
@Entity
@DiscriminatorValue(value = "Job_Salary_RelateType")
public class Job_Salary_RelateType extends MasterEntityBean {

    private static final long serialVersionUID = -4245036351235403763L;

    public final static String MASTER_ID = "0001";

    public Job_Salary_RelateType() {

    }
}
