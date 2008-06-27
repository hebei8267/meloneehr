package cn.hb.dao.dictionary.financial;

import org.springframework.stereotype.Component;
import cn.hb.core.dao.impl.HibernateDaoImpl;
import cn.hb.entity.dictionary.financial.SalaryType;

/**
 * 薪水类型Dao
 */
@Component("salaryTypeDao")
public class SalaryTypeDao extends HibernateDaoImpl<SalaryType> {
}
