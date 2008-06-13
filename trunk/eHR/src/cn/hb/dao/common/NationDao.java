package cn.hb.dao.common;

import java.util.List;

import org.springframework.stereotype.Component;
import cn.hb.core.dao.impl.HibernateDaoImpl;
import cn.hb.entity.common.Nation;
import static cn.hb.constant.Constant.DEFAULT_ID;

/**
 * 民族Dao
 */
@Component("nationDao")
public class NationDao extends HibernateDaoImpl<Nation> {
    /**
     * 取得民族信息列表
     * 
     * @return
     */
    public List<Nation> getNationInfoList() {
        return getAll();
    }

    /**
     * 根据民族ID取得民族信息
     * 
     * @param nationID 民族信息ID
     * @return Nation 民族信息
     */
    @SuppressWarnings("unchecked")
    public Nation getNationByID(String nationID) {
        List<Nation> resultList = getHibernateTemplate().findByNamedQuery("Nation.getNationByID", nationID);
        if (resultList.size() > 0) {
            return resultList.get(0);
        }
        return null;
    }

    /**
     * 是否存在类似的民族（民族名称一样）
     * 
     * @param name
     * @return true-存在 false-不存在
     */
    @SuppressWarnings("unchecked")
    public List<Nation> existLikenessNation(String name) {
        List<Nation> resultList = getHibernateTemplate().findByNamedQuery("Nation.existLikenessNation", name);
        if (resultList.size() > 0) {
            return resultList;
        }
        return null;
    }

    /**
     * 取得当前最大民族信息ID
     * 
     * @return
     */
    public String getMaxNationID() {
        return formatMaxID(_getMaxNationID());
    }

    /**
     * 取得当前最大民族信息ID
     * 
     * @return
     */
    @SuppressWarnings("unchecked")
    protected String _getMaxNationID() {
        List<String> resultList = getHibernateTemplate().findByNamedQuery("Nation.getMaxNationID");
        if (resultList.size() > 0) {
            return resultList.get(0);
        }
        return DEFAULT_ID;
    }
}
