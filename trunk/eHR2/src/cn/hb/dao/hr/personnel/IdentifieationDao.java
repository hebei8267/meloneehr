package cn.hb.dao.hr.personnel;

import java.util.List;

import org.springframework.stereotype.Component;
import cn.hb.core.dao.impl.HibernateDaoImpl;
import cn.hb.entity.hr.personnel.Identifieation;

/**
 * 身份标识Dao
 */
@Component("identifieationDao")
public class IdentifieationDao extends HibernateDaoImpl<Identifieation> {
    /**
     * 根据身份标识ID取得身份标识信息
     * 
     * @param identifieationID 身份标识ID
     * @return 身份标识信息
     */
    @SuppressWarnings("unchecked")
    public Identifieation getIdentifieationByID(String identifieationID) {
        List<Identifieation> resultList = getHibernateTemplate().findByNamedQuery(
                "Identifieation.getIdentifieationByID", identifieationID);
        if (resultList.size() > 0) {
            return resultList.get(0);
        }
        return null;
    }
}
