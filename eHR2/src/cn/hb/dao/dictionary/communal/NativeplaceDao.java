package cn.hb.dao.dictionary.communal;

import static cn.hb.constant.Constant.DEFAULT_ID;

import java.util.List;

import org.springframework.stereotype.Component;
import cn.hb.core.dao.impl.HibernateDaoImpl;
import cn.hb.entity.dictionary.communal.Nativeplace;

/**
 * 籍贯Dao
 */
@Component("nativeplaceDao")
public class NativeplaceDao extends HibernateDaoImpl<Nativeplace> {
    /**
     * 根据籍贯ID取得籍贯信息
     * 
     * @param nativeplaceID 籍贯信息ID
     * @return 籍贯信息
     */
    @SuppressWarnings("unchecked")
    public Nativeplace getNativeplaceByID(String nativeplaceID) {
        List<Nativeplace> resultList = getHibernateTemplate().findByNamedQuery("Nativeplace.getNativeplaceByID",
                nativeplaceID);
        if (resultList.size() > 0) {
            return resultList.get(0);
        }
        return null;
    }

    /**
     * 是否存在类似的籍贯（籍贯名称一样）
     * 
     * @param name 籍贯名称
     * @param pNativeplaceID 父籍贯ID
     * @return true-存在 false-不存在
     */
    @SuppressWarnings("unchecked")
    public List<Nativeplace> existLikenessNativeplace(String name, String pNativeplaceID) {
        List<Nativeplace> resultList = getHibernateTemplate().findByNamedQuery("Nativeplace.existLikenessNativeplace",
                new String[] { name, pNativeplaceID });
        if (resultList.size() > 0) {
            return resultList;
        }
        return null;
    }

    /**
     * 取得当前最大籍贯信息ID
     * 
     * @return
     */
    public String getMaxNativeplaceID() {
        return formatMaxID(_getMaxNativeplaceID());
    }

    /**
     * 取得当前最大籍贯信息ID
     * 
     * @return
     */
    @SuppressWarnings("unchecked")
    protected String _getMaxNativeplaceID() {
        List<String> resultList = getHibernateTemplate().findByNamedQuery("Nativeplace.getMaxNativeplaceID");
        if (resultList.size() > 0) {
            return resultList.get(0);
        }
        return DEFAULT_ID;
    }
}
