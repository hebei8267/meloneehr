package cn.hb.dao.hr.personnel;

import org.springframework.stereotype.Component;
import cn.hb.core.dao.impl.HibernateDaoImpl;
import cn.hb.entity.hr.personnel.ContactAddress;

/**
 * 联系地址Dao
 */
@Component("contactAddressDao")
public class ContactAddressDao extends HibernateDaoImpl<ContactAddress> {
}
