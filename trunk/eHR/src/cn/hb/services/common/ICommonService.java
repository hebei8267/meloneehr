package cn.hb.services.common;

import java.util.List;

import cn.hb.core.services.IService;
import cn.hb.entity.common.Country;
import cn.hb.entity.common.Nation;

/**
 * @author kaka
 * 
 */
public interface ICommonService extends IService {
    /**
     * 取得国家信息列表
     * 
     * @return 国家信息列表
     */
    public List<Country> getCountryInfoList_Service();

    /**
     * 更新国家信息
     * 
     * @param objInfo 国家信息
     * @return 0-更新成功 1-更新失败(数据不存在) 2-更新失败(存在类似的国家)
     */
    public Integer updateCountryInfo_Service(Country objInfo);

    /**
     * 删除国家信息
     * 
     * @param countryID 国家信息ID
     * @return 0-更新成功 1-更新失败
     */
    public Integer delCountryInfo_Service(String countryID);

    /**
     * 添加国家信息
     * 
     * @param objInfo 国家信息
     * @return 0-更新成功 1-更新失败
     */
    public Integer addCountryInfo_Service(Country objInfo);

    /**
     * 取得民族信息列表
     * 
     * @return 民族信息列表
     */
    public List<Nation> getNationInfoList_Service();

    /**
     * 更新民族信息
     * 
     * @param objInfo 民族信息
     * @return 0-更新成功 1-更新失败(数据不存在) 2-更新失败(存在类似的民族)
     */
    public Integer updateNationInfo_Service(Nation objInfo);

    /**
     * 删除民族信息
     * 
     * @param nationID 民族信息ID
     * @return 0-更新成功 1-更新失败
     */
    public Integer delNationInfo_Service(String nationID);

    /**
     * 添加民族信息
     * 
     * @param objInfo 民族信息
     * @return 0-更新成功 1-更新失败
     */
    public Integer addNationInfo_Service(Nation objInfo);

}
