package cn.hb.dao.dictionary.personnel;

import org.springframework.stereotype.Component;
import cn.hb.core.dao.impl.HibernateDaoImpl;
import cn.hb.entity.dictionary.personnel.MarriageState;

/**
 * 婚姻状况Dao
 */
@Component("marriageStateDao")
public class MarriageStateDao extends HibernateDaoImpl<MarriageState> {
}
