package cn.hb.service.dictionary;

import java.util.List;

import cn.hb.core.service.IService;
import cn.hb.entity.dictionary.communal.Country;
import cn.hb.entity.dictionary.communal.Nation;
import cn.hb.entity.dictionary.communal.Nativeplace;
import cn.hb.service.vo.dictionary.communal.Nativeplace_JsonBean;
import cn.hb.service.vo.dictionary.communal.Nativeplace_TreeNode;

/**
 * @author kaka
 * 
 * 数据字典-公共服务
 */
public interface IDD_CommunalService extends IService {

    /**
     * 添加国家信息
     * 
     * @param obj 国家
     * @return 0-添加成功 1-添加失败
     */
    public abstract Integer addCountryInfo_Service(Country obj);

    /**
     * 添加民族信息
     * 
     * @param obj 民族
     * @return 0-添加成功 1-添加失败
     */
    public abstract Integer addNationInfo_Service(Nation obj);

    /**
     * 添加籍贯信息
     * 
     * @param obj 籍贯信息
     * @param pNativeplaceID 父籍贯信息ID
     * @return 0-添加成功 1-添加失败
     */
    public abstract Integer addNativeplaceInfo_Service(Nativeplace obj, String pNativeplaceID);

    /**
     * 删除国家信息
     * 
     * @param countryID 国家ID
     * @return 0-删除成功 1-删除失败
     */
    public abstract Integer delCountryInfo_Service(String countryID);

    /**
     * 删除民族信息
     * 
     * @param nationID 民族ID
     * @return 0-删除成功 1-删除失败
     */
    public abstract Integer delNationInfo_Service(String nationID);

    /**
     * 删除籍贯信息
     * 
     * 0-删除成功 1-删除失败
     * 
     * @param nativeplaceID 籍贯信息ID
     * @param pNativeplaceID 父籍贯信息ID
     * @return 0-删除成功 1-删除失败
     */
    public abstract Integer delNativeplaceInfo_Service(String nativeplaceID, String pNativeplaceID);

    /**
     * 取得国家信息列表
     */
    public abstract List<Country> getCountryInfoList_Service();

    /**
     * 取得民族信息列表
     */
    public abstract List<Nation> getNationInfoList_Service();

    /**
     * 取得籍贯树信息
     * 
     * 所有节点对象数据列表
     */
    public abstract List<Nativeplace_JsonBean> getNativeplaceInfoList_Service();

    /**
     * 取得籍贯树信息
     * 
     * 该树的根节点对象(包含子树节点)
     */
    public abstract Nativeplace_TreeNode getNativeplaceInfoTree_Service();

    /**
     * 更新国家信息
     * 
     * @param obj 国家
     * @return 0-更新成功 1-更新失败(数据不存在) 2-更新失败(存在类似的国家)
     */
    public abstract Integer updateCountryInfo_Service(Country obj);

    /**
     * 更新民族信息
     * 
     * @param obj 民族
     * @return 0-更新成功 1-更新失败(数据不存在) 2-更新失败(存在类似的民族)
     */
    public abstract Integer updateNationInfo_Service(Nation obj);

    /**
     * 更新籍贯信息
     * 
     * @param obj 籍贯信息
     * @param pNativeplaceID 父民族ID
     * @return 0-更新成功 1-更新失败(数据不存在) 2-更新失败(存在类似的籍贯)成功
     */
    public abstract Integer updateNativeplaceInfo_Service(Nativeplace obj, String pNativeplaceID);
}
