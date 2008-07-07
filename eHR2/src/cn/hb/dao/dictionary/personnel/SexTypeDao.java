package cn.hb.dao.dictionary.personnel;

import java.util.List;

import org.springframework.stereotype.Component;

import cn.hb.core.dao.impl.HibernateDaoImpl;
import cn.hb.entity.dictionary.personnel.SexType;

/**
 * 性别类型Dao
 */
@Component("sexTypeDao")
public class SexTypeDao extends HibernateDaoImpl<SexType> {
    /**
     * 根据性别类型ID取得性别类型信息
     * 
     * @param sexTypeID 性别类型ID
     * @return 性别类型信息
     */
    @SuppressWarnings("unchecked")
    public SexType getSexTypeByID(String sexTypeID) {

        List<SexType> resultList = getHibernateTemplate().findByNamedQuery("SexType.getSexTypeByID",
                getNaturalId(sexTypeID));
        if (resultList.size() > 0) {
            return resultList.get(0);
        }
        return null;
    }

    private String[] getNaturalId(String sexTypeID) {
        String[] result = new String[2];
        int length = sexTypeID.length() / 2;
        result[0] = sexTypeID.substring(0, length);
        result[1] = sexTypeID.substring(length);
        return result;
    }
}
