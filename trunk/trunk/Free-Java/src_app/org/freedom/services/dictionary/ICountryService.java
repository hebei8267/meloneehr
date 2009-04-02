/*
 * Copyright 2009 by hebei, All rights reserved.
 */
package org.freedom.services.dictionary;

import java.util.List;

import org.freedom.entity.dictionary.common.Country;

/**
 * 国家(数据字典)信息相关服务
 * 
 * @author 何贝
 * @since JDK1.5
 */
public interface ICountryService {
    /**
     * 删除指定的国家信息
     * 
     * @param countryID 要删除的国家信息ID
     * @param dataVersion 版本
     * @return 0-成功 1-失败(数据同步错误) 2-失败(该国家信息和其他对象有关联关系)
     */
    public int delCountryInfoService(String countryID, int dataVersion);

    /**
     * 取得国家列表信息
     * 
     * @return 国家列表信息
     */
    public List<Country> getAllCountryInfoListService();

    /**
     * 更新国家信息
     * 
     * @param country 要更新的国家信息
     * @return true-成功 false-失败
     */
    public boolean updateCountryInfoService(Country country);

    // TODO del
    public void saveCountryInfo(Country country);
}
