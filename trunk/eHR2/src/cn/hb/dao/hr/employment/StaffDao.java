package cn.hb.dao.hr.employment;

import org.springframework.stereotype.Component;
import cn.hb.core.dao.impl.HibernateDaoImpl;
import cn.hb.entity.hr.employment.Staff;

/**
 * 员工Dao
 */
@Component("staffDao")
public class StaffDao extends HibernateDaoImpl<Staff> {
}
