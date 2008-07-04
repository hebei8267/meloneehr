package cn.hb.dao.hr.personnel;

import org.springframework.stereotype.Component;
import cn.hb.core.dao.impl.HibernateDaoImpl;
import cn.hb.entity.hr.personnel.Training;

/**
 * 培训信息Dao
 */
@Component("trainingDao")
public class TrainingDao extends HibernateDaoImpl<Training> {
}
