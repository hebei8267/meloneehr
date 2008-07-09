package cn.hb.dao.dictionary.organization;

import java.util.List;

import org.springframework.stereotype.Component;
import cn.hb.core.dao.impl.HibernateDaoImpl;
import cn.hb.entity.dictionary.organization.Organization_JobPosition_RelateType;

/**
 * 组织-职务关联类型(排斥_非排斥-关联)Dao
 */
@Component("organization_JobPosition_RelateTypeDao")
public class Organization_JobPosition_RelateTypeDao extends HibernateDaoImpl<Organization_JobPosition_RelateType> {
    /**
     * 根据组织-职务关联类型(排斥_非排斥-关联)ID取得组织-职务关联类型(排斥_非排斥-关联)信息
     * 
     * @param organization_JobPosition_RelateTypeID 组织-职务关联类型(排斥_非排斥-关联)ID
     * @return 组织-职务关联类型(排斥_非排斥-关联)信息
     */
    @SuppressWarnings("unchecked")
    public Organization_JobPosition_RelateType getOrganization_JobPosition_RelateTypeByID(
            String organization_JobPosition_RelateTypeID) {

        List<Organization_JobPosition_RelateType> resultList = getHibernateTemplate().findByNamedQuery(
                "Organization_JobPosition_RelateType.getOrganization_JobPosition_RelateTypeByID",
                getNaturalId(organization_JobPosition_RelateTypeID));
        if (resultList.size() > 0) {
            return resultList.get(0);
        }
        return null;
    }

    private String[] getNaturalId(String organization_JobPosition_RelateTypeID) {
        String[] result = new String[2];
        int length = organization_JobPosition_RelateTypeID.length() / 2;
        result[0] = organization_JobPosition_RelateTypeID.substring(0, length);
        result[1] = organization_JobPosition_RelateTypeID.substring(length);
        return result;
    }
}
