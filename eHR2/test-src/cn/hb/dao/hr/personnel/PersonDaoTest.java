package cn.hb.dao.hr.personnel;

import static constant.TestConstant.ROOT_PATH;

import java.io.IOException;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import cn.hb.core.test.dao.HibernateDaoTestCase;
import cn.hb.entity.dictionary.communal.Country;
import cn.hb.entity.dictionary.communal.Nation;
import cn.hb.entity.dictionary.communal.Nativeplace;
import cn.hb.entity.dictionary.personnel.Currculum;
import cn.hb.entity.dictionary.personnel.MarriageState;
import cn.hb.entity.dictionary.personnel.SexType;
import cn.hb.entity.hr.personnel.Identifieation;
import cn.hb.entity.hr.personnel.Person;
import cn.hb.dao.dictionary.communal.CountryDao;
import cn.hb.dao.dictionary.communal.NationDao;
import cn.hb.dao.dictionary.communal.NativeplaceDao;
import cn.hb.dao.dictionary.personnel.CurrculumDao;
import cn.hb.dao.dictionary.personnel.MarriageStateDao;
import cn.hb.dao.dictionary.personnel.SexTypeDao;
import cn.hb.dao.hr.personnel.PersonDao;
import file.CSVUtils;

/**
 * 个人基本信息 DaoTest
 */
public class PersonDaoTest extends HibernateDaoTestCase {
    private PersonDao personDao;
    private SexTypeDao sexTypeDao;
    private MarriageStateDao marriageStateDao;
    private CurrculumDao currculumDao;
    private CountryDao countryDao;
    private NationDao nationDao;
    private NativeplaceDao nativeplaceDao;
    private IdentifieationDao identifieationDao;

    public PersonDao getPersonDao() {
        return personDao;
    }

    public void setPersonDao(PersonDao personDao) {
        this.personDao = personDao;
    }

    public SexTypeDao getSexTypeDao() {
        return sexTypeDao;
    }

    public void setSexTypeDao(SexTypeDao sexTypeDao) {
        this.sexTypeDao = sexTypeDao;
    }

    public MarriageStateDao getMarriageStateDao() {
        return marriageStateDao;
    }

    public CurrculumDao getCurrculumDao() {
        return currculumDao;
    }

    public void setMarriageStateDao(MarriageStateDao marriageStateDao) {
        this.marriageStateDao = marriageStateDao;
    }

    public void setCurrculumDao(CurrculumDao currculumDao) {
        this.currculumDao = currculumDao;
    }

    public CountryDao getCountryDao() {
        return countryDao;
    }

    public NationDao getNationDao() {
        return nationDao;
    }

    public NativeplaceDao getNativeplaceDao() {
        return nativeplaceDao;
    }

    public IdentifieationDao getIdentifieationDao() {
        return identifieationDao;
    }

    public void setCountryDao(CountryDao countryDao) {
        this.countryDao = countryDao;
    }

    public void setNationDao(NationDao nationDao) {
        this.nationDao = nationDao;
    }

    public void setNativeplaceDao(NativeplaceDao nativeplaceDao) {
        this.nativeplaceDao = nativeplaceDao;
    }

    public void setIdentifieationDao(IdentifieationDao identifieationDao) {
        this.identifieationDao = identifieationDao;
    }

    public void testCase() throws IOException {
        List<List<String>> csvFileContent = CSVUtils.readCSVFile(ROOT_PATH + "Person.csv");

        boolean firstFlag = true;

        for (List<String> fileLine : csvFileContent) {
            if (firstFlag) {
                firstFlag = false;
                continue;
            }

            Person person = new Person();

            for (int i = 0; i < fileLine.size(); i++) {
                String value = fileLine.get(i);
                if (i == 0) {
                    person.setId(value);
                } else if (i == 1) {
                    person.setName1(value);
                } else if (i == 2) {
                    person.setName2(value);
                } else if (i == 3) {
                    if (!StringUtils.isEmpty(value)) {
                        SexType sexType = sexTypeDao.getSexTypeByID(value);

                        person.setSex(sexType);
                    }
                } else if (i == 4) {
                    person.setBirthdate(value);
                } else if (i == 5) {
                    if (!StringUtils.isEmpty(value)) {
                        MarriageState marriageState = marriageStateDao.getMarriageStateByID(value);

                        person.setMarriageState(marriageState);
                    }
                } else if (i == 6) {
                    if (!StringUtils.isEmpty(value)) {
                        Currculum currculum = currculumDao.getCurrculumByID(value);

                        person.setLastCurrculum(currculum);
                    }
                } else if (i == 7) {
                    if (!StringUtils.isEmpty(value)) {
                        Country country = countryDao.getCountryByID(value);
                        person.setCountry(country);
                    }

                } else if (i == 8) {
                    if (!StringUtils.isEmpty(value)) {
                        Nation nation = nationDao.getNationByID(value);
                        person.setNation(nation);
                    }

                } else if (i == 9) {
                    if (!StringUtils.isEmpty(value)) {
                        Nativeplace nativeplace = nativeplaceDao.getNativeplaceByID(value);

                        person.setNativeplace(nativeplace);
                    }

                } else if (i == 10) {
                    if (!StringUtils.isEmpty(value)) {
                        Identifieation identifieation = identifieationDao.getIdentifieationByID(value);
                        person.setIdentifieation(identifieation);
                    }
                }
            }
            personDao.save(person);
        }

    }

}
