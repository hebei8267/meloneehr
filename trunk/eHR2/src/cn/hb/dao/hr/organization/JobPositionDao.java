package cn.hb.dao.hr.organization;

import java.util.List;

import org.springframework.stereotype.Component;
import cn.hb.core.dao.impl.HibernateDaoImpl;
import cn.hb.entity.hr.organization.JobPosition;

/**
 * 职务Dao
 */
@Component("jobPositionDao")
public class JobPositionDao extends HibernateDaoImpl<JobPosition> {
    /**
     * 根据职务ID取得职务信息
     * 
     * @param jobPositionID 职务ID
     * @return 职务信息
     */
    @SuppressWarnings("unchecked")
    public JobPosition getJobPositionByID(String jobPositionID) {
        List<JobPosition> resultList = getHibernateTemplate().findByNamedQuery("JobPosition.getJobPositionByID",
                jobPositionID);
        if (resultList.size() > 0) {
            return resultList.get(0);
        }
        return null;
    }
}
