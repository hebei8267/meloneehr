package cn.hb.service.dictionary;

import java.util.List;

import cn.hb.core.service.IService;
import cn.hb.entity.dictionary.personnel.Currculum;
import cn.hb.entity.dictionary.personnel.EducateSpecialty;
import cn.hb.service.vo.dictionary.personnel.EducateSpecialty_JsonBean;
import cn.hb.service.vo.dictionary.personnel.EducateSpecialty_TreeNode;

public interface IDD_PersonnelService extends IService {
    /**
     * 添加学历信息
     * 
     * @param obj 学历
     * @return 0-添加成功 1-添加失败
     */
    public abstract Integer addCurrculumInfo_Service(Currculum obj);

    /**
     * 添加教育专业信息
     * 
     * @param obj 教育专业信息
     * @param pEducateSpecialtyID 父教育专业信息ID
     * @return 0-添加成功 1-添加失败
     */
    public abstract Integer addEducateSpecialtyInfo_Service(EducateSpecialty obj, String pEducateSpecialtyID);

    /**
     * 删除学历信息
     * 
     * @param countryID 学历ID
     * @return 0-删除成功 1-删除失败
     */
    public abstract Integer delCurrculumInfo_Service(String countryID);

    /**
     * 删除教育专业信息
     * 
     * @param nativeplaceID 教育专业信息ID
     * @param pEducateSpecialtyID 父教育专业信息ID
     * @return 0-删除成功 1-删除失败
     */
    public abstract Integer delEducateSpecialtyInfo_Service(String nativeplaceID, String pEducateSpecialtyID);

    /**
     * 取得学历信息列表
     */
    public abstract List<Currculum> getCurrculumInfoList_Service();

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
     * 更新学历信息
     * 
     * @param obj 学历
     * @return 0-更新成功 1-更新失败(数据不存在) 2-更新失败(存在类似的学历)
     */
    public abstract Integer updateCurrculumInfo_Service(Currculum obj);

    /**
     * 更新教育专业信息
     * 
     * @param obj 教育专业信息
     * @param pEducateSpecialtyID 父民族ID
     * @return 0-更新成功 1-更新失败(数据不存在) 2-更新失败(存在类似的教育专业)成功
     */
    public abstract Integer updateEducateSpecialtyInfo_Service(EducateSpecialty obj, String pEducateSpecialtyID);
}
