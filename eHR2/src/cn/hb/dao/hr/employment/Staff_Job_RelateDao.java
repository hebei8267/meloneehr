package cn.hb.dao.hr.employment;

import org.springframework.stereotype.Component;
import cn.hb.core.dao.impl.HibernateDaoImpl;
import cn.hb.entity.hr.employment.Staff_Job_Relate;

/**
 * 员工-职位Dao
 */
@Component("staff_Job_RelateDao")
public class Staff_Job_RelateDao extends HibernateDaoImpl<Staff_Job_Relate> {
}
