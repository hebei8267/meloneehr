package cn.hb.dao.dictionary.personnel;

import java.util.List;

import org.springframework.stereotype.Component;
import cn.hb.core.dao.impl.HibernateDaoImpl;
import cn.hb.entity.dictionary.personnel.EducateSpecialty;

/**
 * 教育专业Dao
 */
@Component("educateSpecialtyDao")
public class EducateSpecialtyDao extends HibernateDaoImpl<EducateSpecialty> {
    /**
     * 根据教育专业ID取得教育专业信息
     * 
     * @param educateSpecialtyID 教育专业ID
     * @return 教育专业
     */
    @SuppressWarnings("unchecked")
    public EducateSpecialty getEducateSpecialtyByID(String educateSpecialtyID) {
        List<EducateSpecialty> resultList = getHibernateTemplate().findByNamedQuery(
                "EducateSpecialty.getEducateSpecialtyByID", educateSpecialtyID);
        if (resultList.size() > 0) {
            return resultList.get(0);
        }
        return null;
    }
}
