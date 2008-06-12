package cn.hb.services.common.impl;

import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import cn.hb.dao.common.CountryDao;
import cn.hb.dao.common.NationDao;
import cn.hb.entity.common.Country;
import cn.hb.entity.common.Nation;
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
    public Integer updateCountryInfo_Service(Country objInfo) {
        Country dbObjInfo = countryDao.getCountryByID(objInfo.getId());

        if (dbObjInfo == null) {
            return 1;
        }
        List<Country> likenessList = countryDao.existLikenessCountry(objInfo.getName(), objInfo.getShortName());
        if (likenessList != null) {
            for (Country country : likenessList) {
                if (!country.getId().equals(objInfo.getId())) {
                    // 存在类似的国家
                    return 2;
                }
            }
        }

        dbObjInfo.setName(objInfo.getName());
        dbObjInfo.setShortName(objInfo.getShortName());
        dbObjInfo.setDescription(objInfo.getDescription());

        countryDao.save(dbObjInfo);
        return 0;

    }

    @Override
    public Integer addCountryInfo_Service(Country objInfo) {
        if (countryDao.existLikenessCountry(objInfo.getName(), objInfo.getShortName()) != null) {
            // 存在类似的国家
            return 1;
        } else {
            objInfo.setId(countryDao.getMaxCountryID());

            countryDao.save(objInfo);

            return 0;
        }
    }

    @Override
    public Integer delCountryInfo_Service(String countryID) {
        Country dbObjInfo = countryDao.getCountryByID(countryID);
        if (dbObjInfo == null) {
            return 1;
        } else {
            countryDao.remove(dbObjInfo);
            return 0;
        }

    }

    @Override
    public Integer addNationInfo_Service(Nation objInfo) {
        if (nationDao.existLikenessNation(objInfo.getName()) != null) {
            // 存在类似的民族
            return 1;
        } else {
            objInfo.setId(nationDao.getMaxNationID());

            nationDao.save(objInfo);

            return 0;
        }
    }

    @Override
    public Integer delNationInfo_Service(String nationID) {
        Nation dbObjInfo = nationDao.getNationByID(nationID);
        if (dbObjInfo == null) {
            return 1;
        } else {
            nationDao.remove(dbObjInfo);
            return 0;
        }
    }

    @Override
    public List<Nation> getNationInfoList_Service() {
        return nationDao.getAll();
    }

    @Override
    public Integer updateNationInfo_Service(Nation objInfo) {
        Nation dbObjInfo = nationDao.getNationByID(objInfo.getId());

        if (dbObjInfo == null) {
            return 1;
        }
        List<Nation> likenessList = nationDao.existLikenessNation(objInfo.getName());
        if (likenessList != null) {
            for (Nation nation : likenessList) {
                if (!nation.getId().equals(objInfo.getId())) {
                    // 存在类似的民族
                    return 2;
                }
            }
        }

        dbObjInfo.setName(objInfo.getName());
        dbObjInfo.setDescription(objInfo.getDescription());

        nationDao.save(dbObjInfo);
        return 0;
    }

    // ---------------------------------------------------------------------------
    // DAO
    // ---------------------------------------------------------------------------
    private CountryDao countryDao = null;
    private NationDao nationDao = null;

    public CountryDao getCountryDao() {
        return countryDao;
    }

    public void setCountryDao(CountryDao countryDao) {
        this.countryDao = countryDao;
    }

    public NationDao getNationDao() {
        return nationDao;
    }

    public void setNationDao(NationDao nationDao) {
        this.nationDao = nationDao;
    }

}
