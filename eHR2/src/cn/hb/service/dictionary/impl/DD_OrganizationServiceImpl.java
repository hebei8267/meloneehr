package cn.hb.service.dictionary.impl;

import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import cn.hb.entity.dictionary.organization.JobPositionType;
import cn.hb.entity.dictionary.organization.OrganizationType;
import cn.hb.entity.dictionary.organization.Organization_JobPosition_RelateType;
import cn.hb.service.dictionary.IDD_OrganizationService;
import cn.hb.service.vo.dictionary.organization.JobPositionType_JsonBean;
import cn.hb.service.vo.dictionary.organization.JobPositionType_TreeNode;

/**
 * @author kaka
 * 
 * 数据字典-组织服务
 */
@Component("DD_OrganizationService")
@Scope("prototype")
public class DD_OrganizationServiceImpl implements IDD_OrganizationService {

    public Integer addJobPositionTypeInfo_Service(JobPositionType obj, String jobPositionTypeID) {
        // TODO Auto-generated method stub
        return null;
    }

    public Integer addOrganizationTypeInfo_Service(OrganizationType obj) {
        // TODO Auto-generated method stub
        return null;
    }

    public Integer addOrganization_JobPosition_RelateTypeInfo_Service(Organization_JobPosition_RelateType obj) {
        // TODO Auto-generated method stub
        return null;
    }

    public Integer delJobPositionTypeInfo_Service(String jobPositionTypeID, String jobPositionTypeID2) {
        // TODO Auto-generated method stub
        return null;
    }

    public Integer delOrganizationTypeInfo_Service(String organizationTypeID) {
        // TODO Auto-generated method stub
        return null;
    }

    public Integer delOrganization_JobPosition_RelateTypeInfo_Service(String orgJobRelateTypeID) {
        // TODO Auto-generated method stub
        return null;
    }

    public List<JobPositionType_JsonBean> getJobPositionTypeInfoList_Service() {
        // TODO Auto-generated method stub
        return null;
    }

    public JobPositionType_TreeNode getJobPositionTypeInfoTree_Service() {
        // TODO Auto-generated method stub
        return null;
    }

    public List<OrganizationType> getOrganizationTypeInfoList_Service() {
        // TODO Auto-generated method stub
        return null;
    }

    public List<Organization_JobPosition_RelateType> getOrganization_JobPosition_RelateTypeInfoList_Service() {
        // TODO Auto-generated method stub
        return null;
    }

    public Integer updateJobPositionTypeInfo_Service(JobPositionType obj, String jobPositionTypeID) {
        // TODO Auto-generated method stub
        return null;
    }

    public Integer updateOrganizationTypeInfo_Service(OrganizationType obj) {
        // TODO Auto-generated method stub
        return null;
    }

    public Integer updateOrganization_JobPosition_RelateTypeInfo_Service(Organization_JobPosition_RelateType obj) {
        // TODO Auto-generated method stub
        return null;
    }

}
