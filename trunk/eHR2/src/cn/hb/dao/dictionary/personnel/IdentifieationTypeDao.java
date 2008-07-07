package cn.hb.dao.dictionary.personnel;

import java.util.List;

import org.springframework.stereotype.Component;
import cn.hb.core.dao.impl.HibernateDaoImpl;
import cn.hb.entity.dictionary.personnel.IdentifieationType;

/**
 * 身份标识类型Dao
 */
@Component("identifieationTypeDao")
public class IdentifieationTypeDao extends HibernateDaoImpl<IdentifieationType> {
    /**
     * 根据身份标识类型ID取得身份标识类型信息
     * 
     * @param identifieationTypeID 身份标识类型ID
     * @return 身份标识类型信息
     */
    @SuppressWarnings("unchecked")
    public IdentifieationType getIdentifieationTypeByID(String identifieationTypeID) {

        List<IdentifieationType> resultList = getHibernateTemplate().findByNamedQuery(
                "IdentifieationType.getIdentifieationTypeByID", getNaturalId(identifieationTypeID));
        if (resultList.size() > 0) {
            return resultList.get(0);
        }
        return null;
    }

    private String[] getNaturalId(String identifieationTypeID) {
        String[] result = new String[2];
        int length = identifieationTypeID.length() / 2;
        result[0] = identifieationTypeID.substring(0, length);
        result[1] = identifieationTypeID.substring(length);
        return result;
    }
}
