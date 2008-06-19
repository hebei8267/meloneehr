package cn.hb.view.hr.organization;

import java.util.List;

import net.sf.json.JSONObject;

import org.richfaces.model.TreeNode;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import cn.hb.core.view.AbstractViewBean;
import cn.hb.services.hr.organization.IOrganizationService;
import cn.hb.view.convert.ConvertUtil;
import cn.hb.view.domain.json.OrganizationJsonTreeNodeBean;
import cn.hb.view.domain.ui.UIDefaultTreeNodeBean;

/**
 * @author kaka
 * 
 * 组织结构信息树
 */
@Component("Org001_View")
@Scope("request")
public class Org001_View extends AbstractViewBean {

    private static final long serialVersionUID = 3760159933121877831L;

    private TreeNode<UIDefaultTreeNodeBean> orgTreeData;
    /** 父编号 */
    private String pid;
    /** 编号 */
    private String id;
    /** 名称 */
    private String name;
    /** 设立时间 */
    private String startDate;
    /** 简称 */
    private String shortName;
    /** 地址 */
    private String address;
    /** 电话号码 */
    private String telephone;
    /** 传真号码 */
    private String fax;
    /** 组织描述 */
    private String description;
    /** 撤销时间 */
    private String endDate;
    /** 撤销原因 */
    private String endDescription;
    /** 组织类型ID */
    private String organizationTypeID;
    /** 地址所在地-国家ID */
    private String countryID;
    private String jsonNodeData;
    private IOrganizationService organizationService;

    public void updateOrganizationInfo_Action() {

    }

    public void delOrganizationInfo_Action() {

    }

    // ---------------------------------------------------------------------------
    // Override Method
    // ---------------------------------------------------------------------------
    @Override
    public void create() {
        init();
    }

    @Override
    public void destroy() {
        pid = "";
        id = "";
        name = "";
        startDate = "";
        shortName = "";
        address = "";
        telephone = "";
        fax = "";
        description = "";
        endDate = "";
        endDescription = "";
        organizationTypeID = "";
        countryID = "";
    }

    @SuppressWarnings("unchecked")
    @Override
    public void init() {
        Object[] dataObj = organizationService.getOrganizationInfoTree_Service();
        if (dataObj != null) {
            orgTreeData = (TreeNode<UIDefaultTreeNodeBean>) dataObj[0];

            ConvertUtil<OrganizationJsonTreeNodeBean> util = new ConvertUtil<OrganizationJsonTreeNodeBean>();

            JSONObject jsonObj = util.javaListToJSONObject((List<OrganizationJsonTreeNodeBean>) dataObj[1]);

            jsonNodeData = jsonObj.toString();
        }
    }

    // ---------------------------------------------------------------------------
    // Get Set Method
    // ---------------------------------------------------------------------------

    public TreeNode<UIDefaultTreeNodeBean> getOrgTreeData11111() {
        return orgTreeData;
    }

    public TreeNode<UIDefaultTreeNodeBean> getOrgTreeData() {
        return orgTreeData;
    }

    public String getPid() {
        return pid;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getStartDate() {
        return startDate;
    }

    public String getShortName() {
        return shortName;
    }

    public String getAddress() {
        return address;
    }

    public String getTelephone() {
        return telephone;
    }

    public String getFax() {
        return fax;
    }

    public String getDescription() {
        return description;
    }

    public String getEndDate() {
        return endDate;
    }

    public String getEndDescription() {
        return endDescription;
    }

    public String getOrganizationTypeID() {
        return organizationTypeID;
    }

    public String getCountryID() {
        return countryID;
    }

    public String getJsonNodeData() {
        return jsonNodeData;
    }

    public IOrganizationService getOrganizationService() {
        return organizationService;
    }

    public void setOrgTreeData(TreeNode<UIDefaultTreeNodeBean> orgTreeData) {
        this.orgTreeData = orgTreeData;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public void setEndDescription(String endDescription) {
        this.endDescription = endDescription;
    }

    public void setOrganizationTypeID(String organizationTypeID) {
        this.organizationTypeID = organizationTypeID;
    }

    public void setCountryID(String countryID) {
        this.countryID = countryID;
    }

    public void setJsonNodeData(String jsonNodeData) {
        this.jsonNodeData = jsonNodeData;
    }

    public void setOrganizationService(IOrganizationService organizationService) {
        this.organizationService = organizationService;
    }

}
