package cn.hb.dao.dictionary.personnel;

import java.util.List;

import org.springframework.stereotype.Component;
import cn.hb.core.dao.impl.HibernateDaoImpl;
import cn.hb.entity.dictionary.personnel.Currculum;

/**
 * 学历信息Dao
 */
@Component("currculumDao")
public class CurrculumDao extends HibernateDaoImpl<Currculum> {
    /**
     * 根据婚姻状况ID取得学历信息
     * 
     * @param currculumID 婚姻状况ID
     * @return 学历信息
     */
    @SuppressWarnings("unchecked")
    public Currculum getCurrculumByID(String currculumID) {

        List<Currculum> resultList = getHibernateTemplate().findByNamedQuery("Currculum.getCurrculumByID", currculumID);
        if (resultList.size() > 0) {
            return resultList.get(0);
        }
        return null;
    }

}
