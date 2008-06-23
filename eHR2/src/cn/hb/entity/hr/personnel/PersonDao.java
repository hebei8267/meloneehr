package cn.hb.dao.hr.personnel;

import org.springframework.stereotype.Component;
import cn.hb.core.dao.impl.HibernateDaoImpl;
import cn.hb.entity.hr.personnel.Person;

/**
 * 个人基本信息Dao
 */
@Component("personDao")
public class PersonDao extends HibernateDaoImpl<Person> {
}
