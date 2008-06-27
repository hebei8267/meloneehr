package cn.hb.dao.dictionary.employment;

import org.springframework.stereotype.Component;
import cn.hb.core.dao.impl.HibernateDaoImpl;
import cn.hb.entity.dictionary.employment.EmployeeWorkState;

/**
 * 员工当前工作状态Dao
 */
@Component("employeeWorkStateDao")
public class EmployeeWorkStateDao extends HibernateDaoImpl<EmployeeWorkState> {
}
