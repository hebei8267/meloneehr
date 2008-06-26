package cn.hb.dao.dictionary.personnel;

import org.springframework.stereotype.Component;
import cn.hb.core.dao.impl.HibernateDaoImpl;
import cn.hb.entity.dictionary.personnel.IdentifieationType;

/**
 * 身份标识类型Dao
 */
@Component("identifieationTypeDao")
public class IdentifieationTypeDao extends HibernateDaoImpl<IdentifieationType> {
}
