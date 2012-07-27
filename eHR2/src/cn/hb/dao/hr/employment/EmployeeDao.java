package cn.hb.dao.hr.employment;

import org.springframework.stereotype.Component;
import cn.hb.core.dao.impl.HibernateDaoImpl;
import cn.hb.entity.hr.employment.Employee;

/**
 * 员工Dao
 */
@Component("employeeDao")
public class EmployeeDao extends HibernateDaoImpl<Employee> {
}