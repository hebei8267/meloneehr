package cn.hb.dao.dictionary.financial;

import org.springframework.stereotype.Component;
import cn.hb.core.dao.impl.HibernateDaoImpl;
import cn.hb.entity.dictionary.financial.Job_Salary_RelateType;

/**
 * 职位-薪酬福利关联类型(排斥_非排斥-关联)Dao
 */
@Component("job_Salary_RelateTypeDao")
public class Job_Salary_RelateTypeDao extends HibernateDaoImpl<Job_Salary_RelateType> {
}
