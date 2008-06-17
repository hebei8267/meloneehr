package cn.hb.services.dictionary.hr.organization;

import java.util.List;

import cn.hb.core.services.IService;
import cn.hb.entity.hr.organization.OrganizationType;

/**
 * @author kaka
 * 
 * 数据字典服务
 */
public interface IDDOrganizationService extends IService {
    /**
     * 取得组织类型信息列表
     * 
     * @return 组织类型信息列表
     */
    public List<OrganizationType> getOrganizationTypeInfoList_Service();

    /**
     * 更新组织类型信息
     * 
     * @param objInfo 组织类型信息
     * @return 0-更新成功 1-更新失败(数据不存在) 2-更新失败(存在类似的组织类型)
     */
    public Integer updateOrganizationTypeInfo_Service(OrganizationType objInfo);

    /**
     * 删除组织类型信息
     * 
     * @param orgTypeID 组织类型信息ID
     * @return 0-更新成功 1-更新失败
     */
    public Integer delOrganizationTypeInfo_Service(String orgTypeID);

    /**
     * 添加组织类型信息
     * 
     * @param objInfo 组织类型信息
     * @return 0-更新成功 1-更新失败
     */
    public Integer addOrganizationTypeInfo_Service(OrganizationType objInfo);
}
