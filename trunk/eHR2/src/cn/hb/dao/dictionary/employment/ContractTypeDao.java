package cn.hb.dao.dictionary.employment;

import org.springframework.stereotype.Component;
import cn.hb.core.dao.impl.HibernateDaoImpl;
import cn.hb.entity.dictionary.employment.ContractType;

/**
 * 合同类型Dao
 */
@Component("contractTypeDao")
public class ContractTypeDao extends HibernateDaoImpl<ContractType> {
}
