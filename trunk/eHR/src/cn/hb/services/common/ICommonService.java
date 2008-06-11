package cn.hb.services.common;

import java.util.List;

import cn.hb.core.services.IService;
import cn.hb.entity.common.Country;

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
     * @param cInfo 国家信息
     * @return 0-更新成功 1-更新失败
     */
    public Integer updateCountryInfo_Service(Country cInfo);

    /**
     * 删除国家信息
     * 
     * @param countryID 国家信息ID
     * @return true-删除成功 false-删除失败
     */
    public Integer delCountryInfo_Service(String countryID);

    /**
     * 添加国家信息
     * 
     * @param cInfo 国家信息
     * @return 0-更新成功 1-更新失败
     */
    public boolean addCountryInfo_Service(Country cInfo);

}
