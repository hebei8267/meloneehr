package cn.hb.dao.dictionary.communal;

import org.springframework.stereotype.Component;
import cn.hb.core.dao.impl.HibernateDaoImpl;
import cn.hb.entity.dictionary.communal.Country;

/**
 * 国家Dao
 */
@Component("countryDao")
public class CountryDao extends HibernateDaoImpl<Country> {
}
