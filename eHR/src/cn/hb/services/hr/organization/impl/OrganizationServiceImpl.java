package cn.hb.services.hr.organization.impl;

import static cn.hb.constant.Constant.DEFAULT_ID;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import cn.hb.dao.hr.organization.OrganizationDao;
import cn.hb.entity.hr.organization.Organization;
import cn.hb.services.hr.organization.IOrganizationService;
import cn.hb.view.domain.json.OrganizationJsonTreeNodeBean;
import cn.hb.view.domain.ui.UIDefaultTreeNodeBean;

/**
 * @author kaka
 * 
 */
@Component("organizationService")
@Scope("prototype")
public class OrganizationServiceImpl implements IOrganizationService {

    // ---------------------------------------------------------------------------
    // 接口实现
    // ---------------------------------------------------------------------------
    @Override
    public Integer addOrganizationInfo_Service(Organization objInfo, String pOrganizationID) {

        objInfo.setId(organizationDao.getMaxOrganizationID());

        Organization parentOrganization = organizationDao.getOrganizationByID(pOrganizationID);

        parentOrganization.addSubOrganization(objInfo);

        objInfo.setParentOrganization(parentOrganization);

        organizationDao.save(objInfo);
        return 0;
    }

    @Override
    public Integer delOrganizationInfo_Service(String organizationID, String pOrganizationID) {
        Organization dbObjInfo = organizationDao.getOrganizationByID(organizationID);
        if (dbObjInfo == null) {
            return 1;
        } else {
            Organization pOrganization = organizationDao.getOrganizationByID(pOrganizationID);
            pOrganization.removeSubOrganization(dbObjInfo);

            dbObjInfo.setParentOrganization(null);

            organizationDao.remove(dbObjInfo);
            return 0;
        }
    }

    @Override
    public Object[] getOrganizationInfoTree_Service() {
        Organization organization = organizationDao.getOrganizationByID(DEFAULT_ID);

        if (organization != null) { // 空信息根结点
            Object[] resultObj = new Object[2];

            UIDefaultTreeNodeBean rootNode = new UIDefaultTreeNodeBean(organization.getId(), organization.getName());

            buildSubOrganizationTree(rootNode, organization.getSubOrganizationList());
            // 树根节点对象
            resultObj[0] = rootNode;

            List<OrganizationJsonTreeNodeBean> nodeDataList = new ArrayList<OrganizationJsonTreeNodeBean>();
            OrganizationJsonTreeNodeBean jsonBean = new OrganizationJsonTreeNodeBean(null, null, organization);
            nodeDataList.add(jsonBean);

            getSubOrganizationTreeNodeData(nodeDataList, jsonBean, organization.getSubOrganizationList());
            // 树所有节点对象数据列表
            resultObj[1] = nodeDataList;

            return resultObj;
        }
        return null;
    }

    private void getSubOrganizationTreeNodeData(List<OrganizationJsonTreeNodeBean> nodeDataList,
            OrganizationJsonTreeNodeBean parentNode, List<Organization> subOrganizationList) {
        if (subOrganizationList != null && !subOrganizationList.isEmpty()) {

            for (Organization organization : subOrganizationList) {
                OrganizationJsonTreeNodeBean jsonBean = new OrganizationJsonTreeNodeBean(parentNode.getId(), parentNode
                        .getName(), organization);
                nodeDataList.add(jsonBean);
                getSubOrganizationTreeNodeData(nodeDataList, jsonBean, organization.getSubOrganizationList());
            }
        }
    }

    private void buildSubOrganizationTree(UIDefaultTreeNodeBean parentNode, List<Organization> subOrganizationList) {
        if (subOrganizationList != null && !subOrganizationList.isEmpty()) {

            for (Organization organization : subOrganizationList) {
                UIDefaultTreeNodeBean treeNode = new UIDefaultTreeNodeBean(organization.getId(), organization.getName());
                treeNode.setParent(parentNode);

                parentNode.addChild(organization.getId(), treeNode);

                buildSubOrganizationTree(treeNode, organization.getSubOrganizationList());
            }
        }

    }

    @Override
    public Integer updateOrganizationInfo_Service(Organization objInfo) {
        // TODO Auto-generated method stub
        return null;
    }

    // ---------------------------------------------------------------------------
    // DAO
    // ---------------------------------------------------------------------------
    private OrganizationDao organizationDao = null;

    public OrganizationDao getOrganizationDao() {
        return organizationDao;
    }

    public void setOrganizationDao(OrganizationDao organizationDao) {
        this.organizationDao = organizationDao;
    }
}
