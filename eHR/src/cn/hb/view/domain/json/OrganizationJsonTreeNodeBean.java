package cn.hb.view.domain.json;

import cn.hb.entity.hr.organization.Organization;

/**
 * @author kaka
 * 
 */
public class OrganizationJsonTreeNodeBean extends AbstractJsonTreeNodeBean {

    private static final long serialVersionUID = -2962364526062938741L;

    public OrganizationJsonTreeNodeBean() {

    }

    public OrganizationJsonTreeNodeBean(String pid, String pname, Organization organization) {
        this.pid = pid;
        this.pname = pname;
        this.id = organization.getId();
        this.startDate = organization.getStartDate();
        this.name = organization.getName();
        this.shortName = organization.getShortName();
        this.address = organization.getAddress();
        this.telephone = organization.getTelephone();
        this.fax = organization.getFax();
        this.description = organization.getDescription();
        this.endDate = organization.getEndDate();
        this.endDesc = organization.getEndDesc();
        this.organizationTypeID = organization.getOrganizationTypeID();
        this.countryID = organization.getCountryID();
    }

    /** 编号 */
    private String id;

    /** 设立时间 */
    private String startDate;

    /** 名称 */
    private String name;

    /** 简称 */
    private String shortName;

    /** 地址 */
    private String address;

    /** 电话号码 */
    private String telephone;

    /** 传真号码 */
    private String fax;

    /** 详细描述 */
    private String description;

    /** 撤销时间 */
    private String endDate;

    /** 撤销原因 */
    private String endDesc;

    /** 组织类型ID */
    private String organizationTypeID;

    /** 地址所在地-国家ID */
    private String countryID;

    public String getId() {
        return id;
    }

    public String getStartDate() {
        return startDate;
    }

    public String getName() {
        return name;
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

    public String getEndDesc() {
        return endDesc;
    }

    public String getOrganizationTypeID() {
        return organizationTypeID;
    }

    public String getCountryID() {
        return countryID;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public void setName(String name) {
        this.name = name;
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

    public void setEndDesc(String endDesc) {
        this.endDesc = endDesc;
    }

    public void setOrganizationTypeID(String organizationTypeID) {
        this.organizationTypeID = organizationTypeID;
    }

    public void setCountryID(String countryID) {
        this.countryID = countryID;
    }

}
