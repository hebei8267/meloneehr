package cn.hb.service.dictionary;

import java.util.List;
import cn.hb.entity.dictionary.communal.Country;
import cn.hb.entity.dictionary.communal.Nation;
import cn.hb.entity.dictionary.communal.Nativeplace;
import cn.hb.service.domain.dictionary.Nativeplace_Tree_VO;
import cn.hb.service.domain.dictionary.Nativeplace_VO;

/**
 * @author kaka
 * 
 * 数据字典-公共服务
 */
public interface IDDCommunalService {

    /**
     * 取得国家信息列表
     */
    public abstract List<Country> getCountryInfoList_Service();

    /**
     * 更新国家信息
     * 
     * @param obj 国家
     * @return 0-更新成功 1-更新失败(数据不存在) 2-更新失败(存在类似的国家)
     */
    public abstract Integer updateCountryInfo_Service(Country obj);

    /**
     * 删除国家信息
     * 
     * @param countryID 国家ID
     * @return 0-删除成功 1-删除失败
     */
    public abstract Integer delCountryInfo_Service(String countryID);

    /**
     * 添加国家信息
     * 
     * @param obj 国家
     * @return 0-添加成功 1-添加失败
     */
    public abstract Integer addCountryInfo_Service(Country obj);

    /**
     * 取得民族信息列表
     */
    public abstract List<Nation> getNationInfoList_Service();

    /**
     * 更新民族信息
     * 
     * @param obj 民族
     * @return 0-更新成功 1-更新失败(数据不存在) 2-更新失败(存在类似的民族)
     */
    public abstract Integer updateNationInfo_Service(Nation obj);

    /**
     * 删除民族信息
     * 
     * @param nationID 民族ID
     * @return 0-删除成功 1-删除失败
     */
    public abstract Integer delNationInfo_Service(String nationID);

    /**
     * 添加民族信息
     * 
     * @param obj 民族
     * @return 0-添加成功 1-添加失败
     */
    public abstract Integer addNationInfo_Service(Nation obj);

    /**
     * 取得籍贯树信息
     * 
     * 该树的根节点对象(包含子树节点)
     */
    public abstract Nativeplace_Tree_VO getNativeplaceInfoTree_Service();

    /**
     * 取得籍贯树信息
     * 
     * 所有节点对象数据列表
     */
    public abstract List<Nativeplace_VO> getNativeplaceInfoList_Service();

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
     * 添加籍贯信息
     * 
     * @param obj 籍贯信息
     * @param pNativeplaceID 父籍贯信息ID
     * @return 0-添加成功 1-添加失败
     */
    public abstract Integer addNativeplaceInfo_Service(Nativeplace obj, String pNativeplaceID);

    /**
     * 更新籍贯信息
     * 
     * @param obj 籍贯信息
     * @return 0-更新成功 1-更新失败(数据不存在) 2-更新失败(存在类似的籍贯)成功
     */
    public abstract Integer updateNativeplaceInfo_Service(Nativeplace obj);
}
