package cn.hb.dao.dictionary.organization;

import java.util.List;

import org.springframework.stereotype.Component;
import cn.hb.core.dao.impl.HibernateDaoImpl;
import cn.hb.entity.dictionary.organization.JobPositionType;

/**
 * 职种(职务类型)Dao
 */
@Component("jobPositionTypeDao")
public class JobPositionTypeDao extends HibernateDaoImpl<JobPositionType> {

    /**
     * 根据职种(职务类型)ID取得职种(职务类型)信息
     * 
     * @param jobPositionTypeID 职种(职务类型)ID
     * @return 职种(职务类型)
     */
    @SuppressWarnings("unchecked")
    public JobPositionType getJobPositionTypeByID(String jobPositionTypeID) {
        List<JobPositionType> resultList = getHibernateTemplate().findByNamedQuery(
                "JobPositionType.getJobPositionTypeByID", jobPositionTypeID);
        if (resultList.size() > 0) {
            return resultList.get(0);
        }
        return null;
    }
}
