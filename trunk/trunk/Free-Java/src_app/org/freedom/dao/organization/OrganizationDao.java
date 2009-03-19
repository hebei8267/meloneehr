package org.freedom.dao.organization;

import org.freedom.core.orm.hibernate.BaseHibernateDao;
import org.freedom.entity.organization.Organization;
import org.freedom.sys.SysConstant;
import org.springframework.stereotype.Repository;

/**
 * 组织Dao
 */
@Repository("organizationDao")
public class OrganizationDao extends BaseHibernateDao<Organization> {

    /**
     * 根据组织ID取得组织信息
     * 
     * @param organizationID 组织ID
     * @return 组织信息
     */
    public Organization getOrganizationByID(final String organizationID) {

        StringBuffer sql = new StringBuffer(" select obj from Organization obj where obj.id = ? ");
        sql.append(" and obj.startDate <= ");
        sql.append(SysConstant.DB_SYS_DATE_YMD);
        sql.append(" and obj.endDate >= ");
        sql.append(SysConstant.DB_SYS_DATE_YMD);

        return (Organization) findUnique(sql.toString(), organizationID);
    }
}
