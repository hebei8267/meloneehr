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
    public Integer delOrganizationTypeInfo_Service(String orgTypeID) {
        OrganizationType dbObjInfo = organizationTypeDao.getOrganizationTypeByID(orgTypeID);
        if (dbObjInfo == null) {
            return 1;
        } else {
            organizationTypeDao.remove(dbObjInfo);
            return 0;
        }
    }

    @Override
    public List<OrganizationType> getOrganizationTypeInfoList_Service() {
        return organizationTypeDao.getAll();
    }

    @Override
    public Integer updateOrganizationTypeInfo_Service(OrganizationType objInfo) {
        OrganizationType dbObjInfo = organizationTypeDao.getOrganizationTypeByID(objInfo.getId());

        if (dbObjInfo == null) {
            return 1;
        }
        List<OrganizationType> likenessList = organizationTypeDao.existLikenessOrganizationType(objInfo.getName());
        if (likenessList != null) {
            for (OrganizationType organizationType : likenessList) {
                if (!organizationType.getId().equals(objInfo.getId())) {
                    // 存在类似的国家
                    return 2;
                }
            }
        }

        dbObjInfo.setName(objInfo.getName());
        dbObjInfo.setDescription(objInfo.getDescription());

        organizationTypeDao.save(dbObjInfo);
        return 0;
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
