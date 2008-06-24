package cn.hb.dao.dictionary.communal;

import java.util.List;

import org.springframework.stereotype.Component;
import cn.hb.core.dao.impl.HibernateDaoImpl;
import cn.hb.entity.dictionary.communal.Nativeplace;

/**
 * 籍贯Dao
 */
@Component("nativeplaceDao")
public class NativeplaceDao extends HibernateDaoImpl<Nativeplace> {
    /**
     * 根据籍贯ID取得籍贯信息
     * 
     * @param nativeplaceID 籍贯信息ID
     * @return 籍贯信息
     */
    @SuppressWarnings("unchecked")
    public Nativeplace getNativeplaceByID(String nativeplaceID) {
        List<Nativeplace> resultList = getHibernateTemplate().findByNamedQuery("Nativeplace.getNativeplaceByID",
                nativeplaceID);
        if (resultList.size() > 0) {
            return resultList.get(0);
        }
        return null;
    }
}
