package cn.hb.dao.hr.organization;

import org.springframework.stereotype.Component;
import cn.hb.core.dao.impl.HibernateDaoImpl;
import cn.hb.entity.hr.organization.JobPosition;

/**
 * 职务Dao
 */
@Component("jobPositionDao")
public class JobPositionDao extends HibernateDaoImpl<JobPosition> {
}
