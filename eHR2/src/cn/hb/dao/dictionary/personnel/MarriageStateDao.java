package cn.hb.dao.dictionary.personnel;

import java.util.List;

import org.springframework.stereotype.Component;
import cn.hb.core.dao.impl.HibernateDaoImpl;
import cn.hb.entity.dictionary.personnel.MarriageState;

/**
 * 婚姻状况Dao
 */
@Component("marriageStateDao")
public class MarriageStateDao extends HibernateDaoImpl<MarriageState> {
    /**
     * 根据婚姻状况ID取得婚姻状况信息
     * 
     * @param marriageStateID 婚姻状况ID
     * @return 婚姻状况信息
     */
    @SuppressWarnings("unchecked")
    public MarriageState getMarriageStateByID(String marriageStateID) {

        List<MarriageState> resultList = getHibernateTemplate().findByNamedQuery("MarriageState.getMarriageStateByID",
                getNaturalId(marriageStateID));
        if (resultList.size() > 0) {
            return resultList.get(0);
        }
        return null;
    }

    private String[] getNaturalId(String marriageStateID) {
        String[] result = new String[2];
        int length = marriageStateID.length() / 2;
        result[0] = marriageStateID.substring(0, length);
        result[1] = marriageStateID.substring(length);
        return result;
    }
}
