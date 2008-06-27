package cn.hb.dao.dictionary.employment;

import org.springframework.stereotype.Component;
import cn.hb.core.dao.impl.HibernateDaoImpl;
import cn.hb.entity.dictionary.employment.HoldingJobType;

/**
 * 任职类型Dao
 */
@Component("holdingJobTypeDao")
public class HoldingJobTypeDao extends HibernateDaoImpl<HoldingJobType> {
}
