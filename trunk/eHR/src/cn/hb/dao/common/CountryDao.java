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
}