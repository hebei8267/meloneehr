package cn.hb.dao.common;

import java.util.List;

import org.springframework.stereotype.Component;
import cn.hb.core.dao.impl.HibernateDaoImpl;
import cn.hb.entity.common.Country;

/**
 * 国家Dao
 */
@Component("countryDao")
public class CountryDao extends HibernateDaoImpl<Country> {

    /**
     * 取得国家信息列表
     * 
     * @return
     */
    public List<Country> getCountryInfoList() {
        return getAll();
    }

    /**
     * 根据国家ID取得国家信息
     * 
     * @param countryID 国家信息ID
     * @return Country 国家信息
     */
    @SuppressWarnings("unchecked")
    public Country getCountryByID(String countryID) {
        List<Country> resultList = getHibernateTemplate().findByNamedQuery("Country.getCountryByID", countryID);
        if (resultList.size() > 0) {
            return resultList.get(0);
        }
        return null;
    }

    /**
     * 取得当前最大国家信息ID
     * 
     * @return
     */
    public String getMaxCountryID() {
        return formatMaxID(_getMaxCountryID());
    }

    /**
     * 取得当前最大国家信息ID
     * 
     * @return
     */
    @SuppressWarnings("unchecked")
    protected String _getMaxCountryID() {
        List<String> resultList = getHibernateTemplate().find("Country.getMaxCountryID");
        if (resultList.size() > 0) {
            return resultList.get(0);
        }
        return DEFAULT_MAX_ID;
    }
}