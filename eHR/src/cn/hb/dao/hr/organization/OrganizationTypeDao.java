package cn.hb.dao.hr.organization;

import static cn.hb.constant.Constant.DEFAULT_ID;

import java.util.List;

import org.springframework.stereotype.Component;
import cn.hb.core.dao.impl.HibernateDaoImpl;
import cn.hb.entity.hr.organization.OrganizationType;

/**
 * 组织类型Dao
 */
@Component("organizationTypeDao")
public class OrganizationTypeDao extends HibernateDaoImpl<OrganizationType> {
    /**
     * 根据组织类型ID取得组织类型信息
     * 
     * @param organizationTypeID 组织类型信息ID
     * @return OrganizationType 组织类型信息
     */
    @SuppressWarnings("unchecked")
    public OrganizationType getOrganizationTypeByID(String organizationTypeID) {
        List<OrganizationType> resultList = getHibernateTemplate().findByNamedQuery(
                "OrganizationType.getOrganizationTypeByID", organizationTypeID);
        if (resultList.size() > 0) {
            return resultList.get(0);
        }
        return null;
    }

    /**
     * 是否存在类似的组织类型（组织类型名称一样）
     * 
     * @param name
     * @return true-存在 false-不存在
     */
    @SuppressWarnings("unchecked")
    public List<OrganizationType> existLikenessOrganizationType(String name) {
        List<OrganizationType> resultList = getHibernateTemplate().findByNamedQuery(
                "OrganizationType.existLikenessOrganizationType", name);
        if (resultList.size() > 0) {
            return resultList;
        }
        return null;
    }

    /**
     * 取得当前最大组织类型信息ID
     * 
     * @return
     */
    public String getMaxOrganizationTypeID() {
        return formatMaxID(_getMaxOrganizationTypeID());
    }

    /**
     * 取得当前最大组织类型信息ID
     * 
     * @return
     */
    @SuppressWarnings("unchecked")
    private String _getMaxOrganizationTypeID() {
        List<String> resultList = getHibernateTemplate().findByNamedQuery("OrganizationType.getMaxOrganizationTypeID");
        if (resultList.size() > 0) {
            return resultList.get(0);
        }
        return DEFAULT_ID;
    }
}
