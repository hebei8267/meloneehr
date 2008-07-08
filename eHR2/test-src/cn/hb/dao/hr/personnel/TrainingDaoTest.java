package cn.hb.dao.hr.personnel;

import static constant.TestConstant.ROOT_PATH;

import java.io.IOException;
import java.util.List;

import cn.hb.core.test.dao.HibernateDaoTestCase;
import cn.hb.entity.hr.personnel.Person;
import cn.hb.entity.hr.personnel.Training;
import cn.hb.dao.hr.personnel.TrainingDao;
import file.CSVUtils;

/**
 * 培训信息 DaoTest
 */
public class TrainingDaoTest extends HibernateDaoTestCase {
    private TrainingDao trainingDao;
    private PersonDao personDao;

    public TrainingDao getTrainingDao() {
        return trainingDao;
    }

    public void setTrainingDao(TrainingDao trainingDao) {
        this.trainingDao = trainingDao;
    }

    public PersonDao getPersonDao() {
        return personDao;
    }

    public void setPersonDao(PersonDao personDao) {
        this.personDao = personDao;
    }

    public void testCase() throws IOException {
        List<List<String>> csvFileContent = CSVUtils.readCSVFile(ROOT_PATH + "Training.csv");

        for (List<String> fileLine : csvFileContent) {
            Training training = new Training();

            for (int i = 0; i < fileLine.size(); i++) {
                String value = fileLine.get(i);
                if (i == 0) {
                    training.setId(value);
                } else if (i == 1) {
                    training.setTrainingName(value);
                } else if (i == 2) {
                    training.setStartDate(value);
                } else if (i == 3) {
                    training.setEndDate(value);
                } else if (i == 4) {
                    training.setTrainingSite(value);

                } else if (i == 5) {
                    training.setTrainingOrgName(value);
                } else if (i == 6) {

                    Person person = personDao.getPersonByID(value);

                    person.addTraining(training);
                    training.setPerson(person);
                }
            }

            trainingDao.save(training);
        }
    }

}
