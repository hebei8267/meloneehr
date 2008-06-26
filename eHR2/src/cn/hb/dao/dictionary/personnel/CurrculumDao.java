package cn.hb.dao.dictionary.personnel;

import org.springframework.stereotype.Component;
import cn.hb.core.dao.impl.HibernateDaoImpl;
import cn.hb.entity.dictionary.personnel.Currculum;

/**
 * 学历信息Dao
 */
@Component("currculumDao")
public class CurrculumDao extends HibernateDaoImpl<Currculum> {
}
