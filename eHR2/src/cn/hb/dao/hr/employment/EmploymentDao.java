package cn.hb.dao.hr.employment;

import org.springframework.stereotype.Component;
import cn.hb.core.dao.impl.HibernateDaoImpl;
import cn.hb.entity.hr.employment.Employment;

/**
 * 雇佣Dao
 */
@Component("employmentDao")
public class EmploymentDao extends HibernateDaoImpl<Employment> {
}
