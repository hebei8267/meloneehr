package org.freedom.dao.organization;

import java.sql.SQLException;
import java.util.List;

import org.freedom.core.dao.impl.HibernateDaoImpl;
import org.freedom.entity.organization.Organization;
import org.freedom.view.SysConstant;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.stereotype.Component;

/**
 * 组织Dao
 */
@Component("organizationDao")
public class OrganizationDao extends HibernateDaoImpl<Organization> {

    /**
     * 根据组织ID取得组织信息
     * 
     * @param organizationID 组织ID
     * @return 组织信息
     */
    @SuppressWarnings("unchecked")
    public Organization getOrganizationByID(final String organizationID) {
        List<Organization> resultList = (List<Organization>) getHibernateTemplate().execute(new HibernateCallback() {
            public Object doInHibernate(Session session) throws HibernateException, SQLException {
                StringBuffer sql = new StringBuffer(" select obj from Organization obj where obj.id = '");
                sql.append(organizationID);
                sql.append("' and obj.startDate <= ");
                sql.append(SysConstant.DB_SYS_DATE_YMD);
                sql.append(" and obj.endDate >= ");
                sql.append(SysConstant.DB_SYS_DATE_YMD);
                Query query = session.createQuery(sql.toString());

                return query.list();
            }
        });

        if (resultList.size() > 0) {
            return resultList.get(0);
        }
        return null;
    }
}
