package cn.hb.dao.dictionary.communal;

import org.springframework.stereotype.Component;
import cn.hb.core.dao.impl.HibernateDaoImpl;
import cn.hb.entity.dictionary.communal.Nation;

/**
 * 民族Dao
 */
@Component("nationDao")
public class NationDao extends HibernateDaoImpl<Nation> {
}
