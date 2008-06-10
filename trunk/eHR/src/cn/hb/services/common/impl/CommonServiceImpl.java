package cn.hb.services.common.impl;

import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import cn.hb.dao.common.CountryDao;
import cn.hb.entity.common.Country;
import cn.hb.services.common.ICommonService;

/**
 * @author kaka
 * 
 */
@Component("commonService")
@Scope("prototype")
public class CommonServiceImpl implements ICommonService {
    // ---------------------------------------------------------------------------
    // 接口实现
    // ---------------------------------------------------------------------------

    @Override
    public List<Country> getCountryInfoList_Service() {
        return countryDao.getAll();
    }

    // ---------------------------------------------------------------------------
    // DAO
    // ---------------------------------------------------------------------------
    private CountryDao countryDao = null;

    public CountryDao getCountryDao() {
        return countryDao;
    }

    public void setCountryDao(CountryDao countryDao) {
        this.countryDao = countryDao;
    }
}
