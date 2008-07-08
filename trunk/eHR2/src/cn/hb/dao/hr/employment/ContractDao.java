package cn.hb.dao.hr.employment;

import org.springframework.stereotype.Component;
import cn.hb.core.dao.impl.HibernateDaoImpl;
import cn.hb.entity.hr.employment.Contract;

/**
 * 合同Dao
 */
@Component("contractDao")
public class ContractDao extends HibernateDaoImpl<Contract> {
}
