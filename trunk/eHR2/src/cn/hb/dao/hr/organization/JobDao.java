package cn.hb.dao.hr.organization;

import org.springframework.stereotype.Component;
import cn.hb.core.dao.impl.HibernateDaoImpl;
import cn.hb.entity.hr.organization.Job;

/**
 * 职位Dao
 */
@Component("jobDao")
public class JobDao extends HibernateDaoImpl<Job> {
}
