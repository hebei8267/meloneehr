package cn.hb.view.dictionary.hr.organization;

import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import cn.hb.core.view.AbstractViewBean;
import cn.hb.entity.hr.organization.OrganizationType;
import cn.hb.services.dictionary.hr.organization.IDDOrganizationService;
import cn.hb.view.convert.ConvertUtil;

public class DDOrgType001_DWR_View extends AbstractViewBean {
    private IDDOrganizationService ddOrganizationService;

    /**
     * 取得组织类型信息列表
     * 
     * @return JSON格式的列表信息
     */
    public String getOrganizationTypeInfoList_Action(Map<String, String> requestMap) {
        List<OrganizationType> orgTypeList = ddOrganizationService.getOrganizationTypeInfoList_Service();
        // 所有数据数量
        int dataCount = orgTypeList.size();

        ConvertUtil<OrganizationType> util = new ConvertUtil<OrganizationType>();
        JSONObject json = util.javaListToJSONObject(dataCount, orgTypeList);

        return json.toString();
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
