package cn.hb.dao.hr.personnel;

import java.util.List;

import org.springframework.stereotype.Component;
import cn.hb.core.dao.impl.HibernateDaoImpl;
import cn.hb.entity.hr.personnel.Person;

/**
 * 个人基本信息Dao
 */
@Component("personDao")
public class PersonDao extends HibernateDaoImpl<Person> {
    /**
     * 根据个人基本ID取得个人基本信息
     * 
     * @param personID 个人基本ID
     * @return 个人基本信息
     */
    @SuppressWarnings("unchecked")
    public Person getPersonByID(String personID) {
        List<Person> resultList = getHibernateTemplate().findByNamedQuery("Person.getPersonByID", personID);
        if (resultList.size() > 0) {
            return resultList.get(0);
        }
        return null;
    }
}
