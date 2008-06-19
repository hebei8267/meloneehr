package cn.hb.view.dictionary.common;

import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import cn.hb.core.view.AbstractViewBean;
import cn.hb.entity.common.Country;
import cn.hb.services.dictionary.common.IDDCommonService;
import cn.hb.view.convert.ConvertUtil;

/**
 * @author kaka
 * 
 * 国家信息列表
 */
public class DDCom001_DWR_View extends AbstractViewBean {

    private static final long serialVersionUID = 6363307820644662093L;
    private IDDCommonService ddCommonService;

    /**
     * 取得国家信息列表
     * 
     * @return JSON格式的列表信息
     */
    public String getCountryInfoList_Action(Map<String, String> requestMap) {

        List<Country> countryList = ddCommonService.getCountryInfoList_Service();

        // 所有数据数量
        int dataCount = countryList.size();

        ConvertUtil<Country> util = new ConvertUtil<Country>();
        JSONObject json = util.javaListToJSONObject(dataCount, countryList);

        return json.toString();
    }

    // ---------------------------------------------------------------------------
    // Get Set Method
    // ---------------------------------------------------------------------------

    public IDDCommonService getDdCommonService() {
        return ddCommonService;
    }

    public void setDdCommonService(IDDCommonService ddCommonService) {
        this.ddCommonService = ddCommonService;
    }

    // ---------------------------------------------------------------------------
    // Override Method
    // ---------------------------------------------------------------------------
    @Override
    public void create() {
    }

    @Override
    public void destroy() {
    }

    @Override
    public void init() {
    }

}
