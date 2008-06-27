package cn.hb.dao.dictionary.employment;

import org.springframework.stereotype.Component;
import cn.hb.core.dao.impl.HibernateDaoImpl;
import cn.hb.entity.dictionary.employment.EmployType;

/**
 * 雇佣类型Dao
 */
@Component("employTypeDao")
public class EmployTypeDao extends HibernateDaoImpl<EmployType> {
}
