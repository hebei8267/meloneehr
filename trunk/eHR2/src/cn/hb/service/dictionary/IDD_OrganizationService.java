package cn.hb.service.dictionary;

import java.util.List;

import cn.hb.core.service.IService;
import cn.hb.entity.dictionary.organization.JobPositionType;
import cn.hb.entity.dictionary.organization.OrganizationType;
import cn.hb.service.vo.dictionary.organization.JobPositionType_JsonBean;
import cn.hb.service.vo.dictionary.organization.JobPositionType_TreeNode;

/**
 * @author kaka
 * 
 * 数据字典-组织服务
 */
public interface IDD_OrganizationService extends IService {
    /**
     * 添加职种(职务类型)信息
     * 
     * @param obj 职种(职务类型)
     * @param pJobPositionTypeID 父职种(职务类型)信息ID
     * @return 0-添加成功 1-添加失败
     */
    public abstract Integer addJobPositionTypeInfo_Service(JobPositionType obj, String pJobPositionTypeID);

    /**
     * 添加组织类型信息
     * 
     * @param obj 组织类型
     * @return 0-添加成功 1-添加失败
     */
    public abstract Integer addOrganizationTypeInfo_Service(OrganizationType obj);

    /**
     * 删除职种(职务类型)信息
     * 
     * @param jobPositionTypeID 职种(职务类型)ID
     * @param pJobPositionTypeID 父职种(职务类型)信息ID
     * @return 0-删除成功 1-删除失败
     */
    public abstract Integer delJobPositionTypeInfo_Service(String jobPositionTypeID, String pJobPositionTypeID);

    /**
     * 删除组织类型信息
     * 
     * @param organizationTypeID 组织类型ID
     * @return 0-删除成功 1-删除失败
     */
    public abstract Integer delOrganizationTypeInfo_Service(String organizationTypeID);

    /**
     * 取得职种(职务类型)信息
     * 
     * 所有节点对象数据列表
     */
    public abstract List<JobPositionType_JsonBean> getJobPositionTypeInfoList_Service();

    /**
     * 取得职种(职务类型)树信息
     * 
     * 该树的根节点对象(包含子树节点)
     */
    public abstract JobPositionType_TreeNode getJobPositionTypeInfoTree_Service();

    /**
     * 取得组织类型信息列表
     */
    public abstract List<OrganizationType> getOrganizationTypeInfoList_Service();

    /**
     * 更新职种(职务类型)信息
     * 
     * @param obj 职种(职务类型)
     * @param pJobPositionTypeID 父职种(职务类型)信息ID
     * @return 0-更新成功 1-更新失败(数据不存在) 2-更新失败(存在类似的职种(职务类型))
     */
    public abstract Integer updateJobPositionTypeInfo_Service(JobPositionType obj, String pJobPositionTypeID);

    /**
     * 更新组织类型信息
     * 
     * @param obj 组织类型
     * @return 0-更新成功 1-更新失败(数据不存在) 2-更新失败(存在类似的组织类型)
     */
    public abstract Integer updateOrganizationTypeInfo_Service(OrganizationType obj);
}
