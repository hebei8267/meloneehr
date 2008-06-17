package cn.hb.services.dictionary.hr.organization.impl;

import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import cn.hb.dao.hr.organization.OrganizationTypeDao;
import cn.hb.entity.hr.organization.OrganizationType;
import cn.hb.services.dictionary.hr.organization.IDDOrganizationService;

/**
 * @author kaka
 * 
 */
@Component("ddOrganizationService")
@Scope("prototype")
public class DDOrganizationServiceImpl implements IDDOrganizationService {
    // ---------------------------------------------------------------------------
    // 接口实现
    // ---------------------------------------------------------------------------
    @Override
    public Integer addOrganizationTypeInfo_Service(OrganizationType objInfo) {
        if (organizationTypeDao.existLikenessOrganizationType(objInfo.getName()) != null) {
            // 存在类似的国家
            return 1;
        } else {
            objInfo.setId(organizationTypeDao.getMaxOrganizationTypeID());

            organizationTypeDao.save(objInfo);

            return 0;
        }
    }

    @Override
    public Integer delOrganizationTypeInfo_Service(String countryID) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<OrganizationType> getOrganizationTypeInfoList_Service() {
        return organizationTypeDao.getAll();
    }

    @Override
    public Integer updateOrganizationTypeInfo_Service(OrganizationType objInfo) {
        // TODO Auto-generated method stub
        return null;
    }

    // ---------------------------------------------------------------------------
    // DAO
    // ---------------------------------------------------------------------------
    private OrganizationTypeDao organizationTypeDao = null;

    public OrganizationTypeDao getOrganizationTypeDao() {
        return organizationTypeDao;
    }

    public void setOrganizationTypeDao(OrganizationTypeDao organizationTypeDao) {
        this.organizationTypeDao = organizationTypeDao;
    }
}
