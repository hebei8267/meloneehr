package cn.hb.services.common.impl;

import java.util.List;

import org.richfaces.model.TreeNode;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import cn.hb.dao.common.CountryDao;
import cn.hb.dao.common.NationDao;
import cn.hb.dao.common.NativeplaceDao;
import cn.hb.entity.common.Country;
import cn.hb.entity.common.Nation;
import cn.hb.entity.common.Nativeplace;
import cn.hb.services.common.ICommonService;
import cn.hb.view.domain.UINativeplaceTreeNodeBean;
import static cn.hb.constant.Constant.DEFAULT_ID;

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

    @Override
    public TreeNode<UINativeplaceTreeNodeBean> getNativeplaceInfoTree_Service() {
        Nativeplace nativeplace = nativeplaceDao.getNativeplaceByID(DEFAULT_ID);

        if (nativeplace != null) {
            // 空信息根结点
            UINativeplaceTreeNodeBean rootNode = new UINativeplaceTreeNodeBean(nativeplace.getId(), nativeplace
                    .getName(), nativeplace.getDescription());

            buildSubMenuTree(rootNode, nativeplace.getSubNativeplaceList());

            return rootNode;
        }
        return null;
    }

    private void buildSubMenuTree(UINativeplaceTreeNodeBean parentNode, List<Nativeplace> subNativeplaceList) {
        if (subNativeplaceList != null && !subNativeplaceList.isEmpty()) {
            for (Nativeplace nativeplace : subNativeplaceList) {
                UINativeplaceTreeNodeBean treeNode = new UINativeplaceTreeNodeBean(parentNode.getId(), parentNode
                        .getName(), nativeplace.getId(), nativeplace.getName(), nativeplace.getDescription());
                treeNode.setParent(parentNode);

                parentNode.addChild(nativeplace.getId(), treeNode);

                buildSubMenuTree(treeNode, nativeplace.getSubNativeplaceList());
            }
        }
    }

    @Override
    public Integer addNativeplaceInfo_Service(Nativeplace objInfo, String pNativeplaceID) {
        if (nativeplaceDao.existLikenessNativeplace(objInfo.getName()) != null) {
            // 存在类似的籍贯
            return 1;
        } else {
            objInfo.setId(nativeplaceDao.getMaxNativeplaceID());

            Nativeplace parentNativeplace = nativeplaceDao.getNativeplaceByID(pNativeplaceID);

            parentNativeplace.addSubNativeplace(objInfo);

            objInfo.setParentNativeplace(parentNativeplace);

            nativeplaceDao.save(objInfo);
            return 0;
        }
    }

    @Override
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

    @Override
    public Integer updateNativeplaceInfo_Service(Nativeplace objInfo) {
        Nativeplace dbObjInfo = nativeplaceDao.getNativeplaceByID(objInfo.getId());

        if (dbObjInfo == null) {
            return 1;
        }
        List<Nativeplace> likenessList = nativeplaceDao.existLikenessNativeplace(objInfo.getName());
        if (likenessList != null) {
            for (Nativeplace nativeplace : likenessList) {
                if (!nativeplace.getId().equals(objInfo.getId())) {
                    // 存在类似的民族
                    return 2;
                }
            }
        }

        dbObjInfo.setName(objInfo.getName());
        dbObjInfo.setDescription(objInfo.getDescription());

        nativeplaceDao.save(dbObjInfo);
        return 0;
    }

    // ---------------------------------------------------------------------------
    // DAO
    // ---------------------------------------------------------------------------
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
