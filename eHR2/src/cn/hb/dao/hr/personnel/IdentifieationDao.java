package cn.hb.dao.hr.personnel;

import org.springframework.stereotype.Component;
import cn.hb.core.dao.impl.HibernateDaoImpl;
import cn.hb.entity.hr.personnel.Identifieation;

/**
 * 身份标识Dao
 */
@Component("identifieationDao")
public class IdentifieationDao extends HibernateDaoImpl<Identifieation> {
}
