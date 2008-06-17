package cn.hb.view.dictionary.hr.organization;

import static cn.hb.view.MsgID.ERROR_ADD_ORGANIZATION_TYPE;
import static cn.hb.view.MsgID.ERROR_DEL_ORGANIZATION_TYPE;
import static cn.hb.view.MsgID.ERROR_UPDATE_ORGANIZATION_TYPE1;
import static cn.hb.view.MsgID.ERROR_UPDATE_ORGANIZATION_TYPE2;
import cn.hb.core.view.AbstractViewBean;
import cn.hb.entity.hr.organization.OrganizationType;
import cn.hb.services.dictionary.hr.organization.IDDOrganizationService;

/**
 * @author kaka
 * 
 * 组织类型信息列表
 */
public class DDOrgType001_View extends AbstractViewBean {
    private String id;
    private String name;
    private String shortName;
    private String description;
    private IDDOrganizationService ddOrganizationService;

    public void updateOrganizationTypeInfo_Action() {
        OrganizationType objInfo = new OrganizationType();
        objInfo.setId(id);
        objInfo.setName(name);
        objInfo.setDescription(description);

        int result = ddOrganizationService.updateOrganizationTypeInfo_Service(objInfo);

        if (result == 1) {
            addErrorMessage(ERROR_UPDATE_ORGANIZATION_TYPE1);
        }
        if (result == 2) {
            addErrorMessage(ERROR_UPDATE_ORGANIZATION_TYPE2);
        }
        destroy();
        return;
    }

    public void delOrganizationTypeInfo_Action() {
        int result = ddOrganizationService.delOrganizationTypeInfo_Service(id);

        if (result != 0) {
            addErrorMessage(ERROR_DEL_ORGANIZATION_TYPE);
        }
        destroy();
        return;
    }

    public void addOrganizationTypeInfo_Action() {
        OrganizationType objInfo = new OrganizationType();

        objInfo.setName(name);
        objInfo.setDescription(description);

        int result = ddOrganizationService.addOrganizationTypeInfo_Service(objInfo);

        if (result != 0) {
            addErrorMessage(ERROR_ADD_ORGANIZATION_TYPE);
        }
        destroy();
        return;
    }

    // ---------------------------------------------------------------------------
    // Get Set Method
    // ---------------------------------------------------------------------------

    public IDDOrganizationService getDdOrganizationService() {
        return ddOrganizationService;
    }

    public void setDdOrganizationService(IDDOrganizationService ddOrganizationService) {
        this.ddOrganizationService = ddOrganizationService;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getShortName() {
        return shortName;
    }

    public String getDescription() {
        return description;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    // ---------------------------------------------------------------------------
    // Override Method
    // ---------------------------------------------------------------------------
    @Override
    public void create() {
    }

    @Override
    public void destroy() {
    }

    @Override
    public void init() {
    }

}
