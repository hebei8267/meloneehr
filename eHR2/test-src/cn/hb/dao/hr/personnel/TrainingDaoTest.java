package cn.hb.dao.hr.personnel;

import cn.hb.core.test.dao.HibernateDaoTestCase;
import cn.hb.entity.hr.personnel.Training;
import cn.hb.dao.hr.personnel.TrainingDao;

/**
 * 培训信息 DaoTest
 */
public class TrainingDaoTest extends HibernateDaoTestCase {
	private TrainingDao trainingDao;

	public TrainingDao getTrainingDao() {
		return trainingDao;
	}

	public void setTrainingDao(TrainingDao trainingDao) {
		this.trainingDao = trainingDao;
	}

	public void testCase() {
		Training training = new Training();
	}

}
