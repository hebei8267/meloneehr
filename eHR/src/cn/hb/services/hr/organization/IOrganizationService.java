package cn.hb.services.hr.organization;

import cn.hb.entity.hr.organization.Organization;

/**
 * @author kaka
 * 
 * 组织服务
 */
public interface IOrganizationService {
    /**
     * 取得组织树信息 一个长度为2的对象数组，第一个对象是该树的根节点对象，第二个对象是该树所有节点对象数据列表
     * 
     * @return
     */
    public Object[] getOrganizationInfoTree_Service();

    /**
     * 更新组织信息
     * 
     * @param objInfo 组织信息
     * @return 0-更新成功 1-更新失败(数据不存在) 2-更新失败(存在类似的组织)
     */
    public Integer updateOrganizationInfo_Service(Organization objInfo);

    /**
     * 删除组织信息
     * 
     * @param organizationID 组织信息ID
     * @param pOrganizationID 父组织信息ID
     * @return 0-更新成功 1-更新失败
     */
    public Integer delOrganizationInfo_Service(String organizationID, String pOrganizationID);

    /**
     * 添加组织信息
     * 
     * @param objInfo 组织信息
     * @param pOrganizationID 父组织信息ID
     * @return 0-更新成功 1-更新失败
     */
    public Integer addOrganizationInfo_Service(Organization objInfo, String pOrganizationID);
}
