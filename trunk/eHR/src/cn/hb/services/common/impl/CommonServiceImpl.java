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

    @Override
    public Integer updateCountryInfo_Service(Country cInfo) {
        Country dbCInfo = countryDao.getCountryByID(cInfo.getId());

        if (dbCInfo == null) {
            return 1;
        } else {
            dbCInfo.setName(cInfo.getName());
            dbCInfo.setShortName(cInfo.getShortName());
            dbCInfo.setDescription(cInfo.getDescription());

            countryDao.save(dbCInfo);
            return 0;
        }
    }

    @Override
    public Integer addCountryInfo_Service(Country cInfo) {
        if (countryDao.existLikenessCountry(cInfo.getName(), cInfo.getShortName())) {
            // 存在类似的国家
            return 1;
        } else {
            cInfo.setId(countryDao.getMaxCountryID());

            countryDao.save(cInfo);

            return 0;
        }
    }

    @Override
    public Integer delCountryInfo_Service(String countryID) {
        Country dbCInfo = countryDao.getCountryByID(countryID);
        if (dbCInfo == null) {
            return 1;
        } else {
            countryDao.remove(dbCInfo);
            return 0;
        }

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
