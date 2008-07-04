package cn.hb.dao.hr.personnel;

import org.springframework.stereotype.Component;
import cn.hb.core.dao.impl.HibernateDaoImpl;
import cn.hb.entity.hr.personnel.Education;

/**
 * 教育信息Dao
 */
@Component("educationDao")
public class EducationDao extends HibernateDaoImpl<Education> {
}
