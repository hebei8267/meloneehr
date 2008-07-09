package cn.hb.dao.hr.employment;

import org.springframework.stereotype.Component;
import cn.hb.core.dao.impl.HibernateDaoImpl;
import cn.hb.entity.hr.employment.LaborContract;

/**
 * 劳动合同Dao
 */
@Component("laborContractDao")
public class LaborContractDao extends HibernateDaoImpl<LaborContract> {
}
