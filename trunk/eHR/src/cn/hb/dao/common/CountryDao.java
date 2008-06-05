package cn.hb.dao.common;

import org.springframework.stereotype.Component;
import cn.hb.core.dao.impl.HibernateDaoImpl;
import cn.hb.entity.common.Country;

/**
 * 国家Dao
 */
@Component("countryDao")
public class CountryDao extends HibernateDaoImpl<Country> {
}
