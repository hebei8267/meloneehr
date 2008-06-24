package cn.hb.services.dictionary.impl;

import java.util.List;

import cn.hb.dao.dictionary.communal.CountryDao;
import cn.hb.dao.dictionary.communal.NationDao;
import cn.hb.dao.dictionary.communal.NativeplaceDao;
import cn.hb.entity.dictionary.communal.Country;
import cn.hb.entity.dictionary.communal.Nation;
import cn.hb.entity.dictionary.communal.Nativeplace;
import cn.hb.services.dictionary.IDD_Communal_Service;
import cn.hb.services.domain.dictionary.Nativeplace_Tree_VO;
import cn.hb.services.domain.dictionary.Nativeplace_VO;

/**
 * @author kaka
 * 
 * 数据字典-公共服务
 */
public class DD_Communal_Service implements IDD_Communal_Service {
    // ---------------------------------------------------------------------------
    // 接口实现
    // ---------------------------------------------------------------------------
    public Integer addCountryInfo_Service(Country obj) {
        if (countryDao.existLikenessCountry(obj.getName(), obj.getShortName()) != null) {
            // 存在类似的国家
            return 1;
        } else {
            obj.setId(countryDao.getMaxCountryID());
            countryDao.save(obj);
            return 0;
        }
    }

    public Integer addNationInfo_Service(Nation obj) {
        // TODO Auto-generated method stub
        return null;
    }

    public Integer addNativeplaceInfo_Service(Nativeplace obj, String nativeplaceID) {
        // TODO Auto-generated method stub
        return null;
    }

    public Integer delCountryInfo_Service(String countryID) {
        // TODO Auto-generated method stub
        return null;
    }

    public Integer delNationInfo_Service(String nationID) {
        // TODO Auto-generated method stub
        return null;
    }

    public Integer delNativeplaceInfo_Service(String nativeplaceID, String nativeplaceID2) {
        // TODO Auto-generated method stub
        return null;
    }

    public List<Country> getCountryInfoList_Service() {
        // TODO Auto-generated method stub
        return null;
    }

    public List<Nation> getNationInfoList_Service() {
        // TODO Auto-generated method stub
        return null;
    }

    public List<Nativeplace_VO> getNativeplaceInfoList_Service() {
        // TODO Auto-generated method stub
        return null;
    }

    public Nativeplace_Tree_VO getNativeplaceInfoTree_Service() {
        // TODO Auto-generated method stub
        return null;
    }

    public Integer updateCountryInfo_Service(Country obj) {
        // TODO Auto-generated method stub
        return null;
    }

    public Integer updateNationInfo_Service(Nation obj) {
        // TODO Auto-generated method stub
        return null;
    }

    public Integer updateNativeplaceInfo_Service(Nativeplace obj) {
        // TODO Auto-generated method stub
        return null;
    }

    private CountryDao countryDao = null;
    private NationDao nationDao = null;
    private NativeplaceDao nativeplaceDao = null;

    public CountryDao getCountryDao() {
        return countryDao;
    }

    public NationDao getNationDao() {
        return nationDao;
    }

    public NativeplaceDao getNativeplaceDao() {
        return nativeplaceDao;
    }

    public void setCountryDao(CountryDao countryDao) {
        this.countryDao = countryDao;
    }

    public void setNationDao(NationDao nationDao) {
        this.nationDao = nationDao;
    }

    public void setNativeplaceDao(NativeplaceDao nativeplaceDao) {
        this.nativeplaceDao = nativeplaceDao;
    }
}
