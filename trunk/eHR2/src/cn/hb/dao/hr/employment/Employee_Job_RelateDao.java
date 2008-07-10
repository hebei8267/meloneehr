package cn.hb.dao.hr.employment;

import org.springframework.stereotype.Component;
import cn.hb.core.dao.impl.HibernateDaoImpl;
import cn.hb.entity.hr.employment.Employee_Job_Relate;

/**
 * 员工-职位Dao
 */
@Component("employee_Job_RelateDao")
public class Employee_Job_RelateDao extends HibernateDaoImpl<Employee_Job_Relate> {
}
