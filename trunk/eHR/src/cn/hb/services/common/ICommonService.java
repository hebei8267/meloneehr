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

}
