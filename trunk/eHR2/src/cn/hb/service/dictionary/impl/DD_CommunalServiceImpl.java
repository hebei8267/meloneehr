package cn.hb.service.dictionary.impl;

import static cn.hb.constant.Constant.DEFAULT_ID;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import cn.hb.dao.dictionary.communal.CountryDao;
import cn.hb.dao.dictionary.communal.NationDao;
import cn.hb.dao.dictionary.communal.NativeplaceDao;
import cn.hb.entity.dictionary.communal.Country;
import cn.hb.entity.dictionary.communal.Nation;
import cn.hb.entity.dictionary.communal.Nativeplace;
import cn.hb.service.dictionary.IDD_CommunalService;
import cn.hb.service.vo.dictionary.Nativeplace_JsonBean;
import cn.hb.service.vo.dictionary.Nativeplace_TreeNode;

/**
 * @author kaka
 * 
 * 数据字典-公共服务
 */
@Component("DD_CommunalService")
@Scope("prototype")
public class DD_CommunalServiceImpl implements IDD_CommunalService {
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
        if (nationDao.existLikenessNation(obj.getName()) != null) {
            // 存在类似的民族
            return 1;
        } else {
            obj.setId(nationDao.getMaxNationID());

            nationDao.save(obj);

            return 0;
        }
    }

    public Integer addNativeplaceInfo_Service(Nativeplace obj, String pNativeplaceID) {
        if (nativeplaceDao.existLikenessNativeplace(obj.getName(), pNativeplaceID) != null) {
            // 存在类似的籍贯
            return 1;
        } else {
            obj.setId(nativeplaceDao.getMaxNativeplaceID());

            Nativeplace parentNativeplace = nativeplaceDao.getNativeplaceByID(pNativeplaceID);

            parentNativeplace.addSubNativeplace(obj);

            obj.setParentNativeplace(parentNativeplace);

            nativeplaceDao.save(obj);
            return 0;
        }
    }

    public Integer delCountryInfo_Service(String countryID) {
        Country dbObjInfo = countryDao.getCountryByID(countryID);
        if (dbObjInfo == null) {
            return 1;
        } else {
            countryDao.remove(dbObjInfo);
            return 0;
        }
    }

    public Integer delNationInfo_Service(String nationID) {
        Nation dbObjInfo = nationDao.getNationByID(nationID);
        if (dbObjInfo == null) {
            return 1;
        } else {
            nationDao.remove(dbObjInfo);
            return 0;
        }
    }

    public Integer delNativeplaceInfo_Service(String nativeplaceID, String pNativeplaceID) {
        Nativeplace dbObjInfo = nativeplaceDao.getNativeplaceByID(nativeplaceID);
        if (dbObjInfo == null) {
            return 1;
        } else {
            Nativeplace pNativeplace = nativeplaceDao.getNativeplaceByID(pNativeplaceID);
            pNativeplace.removeSubNativeplace(dbObjInfo);

            dbObjInfo.setParentNativeplace(null);

            nativeplaceDao.remove(dbObjInfo);
            return 0;
        }
    }

    public List<Country> getCountryInfoList_Service() {
        return countryDao.getAll();
    }

    public List<Nation> getNationInfoList_Service() {
        return nationDao.getAll();
    }

    public List<Nativeplace_JsonBean> getNativeplaceInfoList_Service() {
        Nativeplace nativeplace = nativeplaceDao.getNativeplaceByID(DEFAULT_ID);
        if (nativeplace != null) {
            List<Nativeplace_JsonBean> dataList = new ArrayList<Nativeplace_JsonBean>();
            Nativeplace_JsonBean jsonData = new Nativeplace_JsonBean(null, null, nativeplace);
            dataList.add(jsonData);

            buildSubNativeplaceListData(dataList, jsonData, nativeplace.getSubNativeplaceList());

            return dataList;
        }
        return null;
    }

    private void buildSubNativeplaceListData(List<Nativeplace_JsonBean> dataList, Nativeplace_JsonBean parentNode,
            List<Nativeplace> subNativeplaceList) {
        if (subNativeplaceList != null && !subNativeplaceList.isEmpty()) {
            for (Nativeplace nativeplace : subNativeplaceList) {
                Nativeplace_JsonBean jsonBean = new Nativeplace_JsonBean(parentNode.getId(), parentNode.getName(),
                        nativeplace);
                dataList.add(jsonBean);
                buildSubNativeplaceListData(dataList, jsonBean, nativeplace.getSubNativeplaceList());
            }
        }

    }

    public Nativeplace_TreeNode getNativeplaceInfoTree_Service() {
        Nativeplace nativeplace = nativeplaceDao.getNativeplaceByID(DEFAULT_ID);
        if (nativeplace != null) {
            Nativeplace_TreeNode rootNode = new Nativeplace_TreeNode(nativeplace.getId(), nativeplace.getName());
            buildSubNativeplaceTree(rootNode, nativeplace.getSubNativeplaceList());

            return rootNode;
        }
        return null;
    }

    private void buildSubNativeplaceTree(Nativeplace_TreeNode parentNode, List<Nativeplace> subNativeplaceList) {
        if (subNativeplaceList != null && !subNativeplaceList.isEmpty()) {
            for (Nativeplace nativeplace : subNativeplaceList) {
                Nativeplace_TreeNode treeNode = new Nativeplace_TreeNode(nativeplace.getId(), nativeplace.getName());
                treeNode.setParent(parentNode);

                parentNode.addChild(nativeplace.getId(), treeNode);

                buildSubNativeplaceTree(treeNode, nativeplace.getSubNativeplaceList());
            }
        }

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
