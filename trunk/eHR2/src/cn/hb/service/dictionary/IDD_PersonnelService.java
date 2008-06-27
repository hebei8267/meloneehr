package cn.hb.service.dictionary;

import java.util.List;

import cn.hb.core.service.IService;
import cn.hb.entity.dictionary.personnel.Currculum;
import cn.hb.entity.dictionary.personnel.EducateSpecialty;
import cn.hb.entity.dictionary.personnel.IdentifieationType;
import cn.hb.entity.dictionary.personnel.MarriageState;
import cn.hb.service.vo.dictionary.personnel.EducateSpecialty_JsonBean;
import cn.hb.service.vo.dictionary.personnel.EducateSpecialty_TreeNode;

/**
 * @author kaka
 * 
 * 数据字典-个人信息服务
 */
public interface IDD_PersonnelService extends IService {

    /**
     * 添加学历信息
     * 
     * @param obj 学历
     * @return 0-添加成功 1-添加失败
     */
    public abstract Integer addCurrculumInfo_Service(Currculum obj);

    /**
     * 删除学历信息
     * 
     * @param currculumID 学历ID
     * @return 0-删除成功 1-删除失败
     */
    public abstract Integer delCurrculumInfo_Service(String currculumID);

    /**
     * 取得学历信息列表
     */
    public abstract List<Currculum> getCurrculumInfoList_Service();

    /**
     * 更新学历信息
     * 
     * @param obj 学历
     * @return 0-更新成功 1-更新失败(数据不存在) 2-更新失败(存在类似的学历)
     */
    public abstract Integer updateCurrculumInfo_Service(Currculum obj);

    /**
     * 添加教育专业信息
     * 
     * @param obj 教育专业信息
     * @param pEducateSpecialtyID 父教育专业信息ID
     * @return 0-添加成功 1-添加失败
     */
    public abstract Integer addEducateSpecialtyInfo_Service(EducateSpecialty obj, String pEducateSpecialtyID);

    /**
     * 删除教育专业信息
     * 
     * @param educateSpecialtyID 教育专业信息ID
     * @param pEducateSpecialtyID 父教育专业信息ID
     * @return 0-删除成功 1-删除失败
     */
    public abstract Integer delEducateSpecialtyInfo_Service(String educateSpecialtyID, String pEducateSpecialtyID);

    /**
     * 取得教育专业树信息
     * 
     * 所有节点对象数据列表
     */
    public abstract List<EducateSpecialty_JsonBean> getEducateSpecialtyInfoList_Service();

    /**
     * 取得教育专业树信息
     * 
     * 该树的根节点对象(包含子树节点)
     */
    public abstract EducateSpecialty_TreeNode getEducateSpecialtyInfoTree_Service();

    /**
     * 更新教育专业信息
     * 
     * @param obj 教育专业信息
     * @param pEducateSpecialtyID 父民族ID
     * @return 0-更新成功 1-更新失败(数据不存在) 2-更新失败(存在类似的教育专业)成功
     */
    public abstract Integer updateEducateSpecialtyInfo_Service(EducateSpecialty obj, String pEducateSpecialtyID);

    /**
     * 添加身份标识类型信息
     * 
     * @param obj 身份标识类型
     * @return 0-添加成功 1-添加失败
     */
    public abstract Integer addIdentifieationTypeInfo_Service(IdentifieationType obj);

    /**
     * 删除身份标识类型信息
     * 
     * @param identifieationTypeID 身份标识类型ID
     * @return 0-删除成功 1-删除失败
     */
    public abstract Integer delIdentifieationTypeInfo_Service(String identifieationTypeID);

    /**
     * 取得身份标识类型信息列表
     */
    public abstract List<IdentifieationType> getIdentifieationTypeInfoList_Service();

    /**
     * 更新身份标识类型信息
     * 
     * @param obj 身份标识类型
     * @return 0-更新成功 1-更新失败(数据不存在) 2-更新失败(存在类似的身份标识类型)
     */
    public abstract Integer updateIdentifieationTypeInfo_Service(IdentifieationType obj);

    /**
     * 添加婚姻状况信息
     * 
     * @param obj 婚姻状况
     * @return 0-添加成功 1-添加失败
     */
    public abstract Integer addMarriageStateInfo_Service(MarriageState obj);

    /**
     * 删除婚姻状况信息
     * 
     * @param marriageStateID 婚姻状况ID
     * @return 0-删除成功 1-删除失败
     */
    public abstract Integer delMarriageStateInfo_Service(String marriageStateID);

    /**
     * 取得婚姻状况信息列表
     */
    public abstract List<MarriageState> getMarriageStateInfoList_Service();

    /**
     * 更新婚姻状况信息
     * 
     * @param obj 婚姻状况
     * @return 0-更新成功 1-更新失败(数据不存在) 2-更新失败(存在类似的婚姻状况)
     */
    public abstract Integer updateMarriageStateInfo_Service(MarriageState obj);

}
