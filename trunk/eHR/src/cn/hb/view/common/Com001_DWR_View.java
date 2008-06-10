package cn.hb.view.common;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import net.sf.json.JSONObject;

import cn.hb.core.view.AbstractViewBean;
import cn.hb.entity.common.Country;
import cn.hb.services.common.ICommonService;
import cn.hb.view.convert.ConvertUtil;

/**
 * @author kaka
 * 
 * 国家信息列表
 */
// @Component("Com001_DWR_View")
// @Scope("request")
public class Com001_DWR_View extends AbstractViewBean {
    private ICommonService commonService;

    /**
     * 取得国家信息列表
     * 
     * @return JSON格式的列表信息
     */
    public String getCountryInfoList_Action(Map<String, String> requestMap) {

        List<Country> countryList = commonService.getCountryInfoList_Service();

        // 所有数据数量
        int dataCount = countryList.size();

        List mmlist = new ArrayList<Countrymm>();
        for (Country country : countryList) {
            Countrymm mm = new Countrymm();
            mm.setId(country.getId());
            mm.setName(country.getName());
            mm.setShortName(country.getShortName());
            mm.setDescription(country.getDescription());
            mmlist.add(mm);
        }

        ConvertUtil<Countrymm> util = new ConvertUtil<Countrymm>();
        JSONObject json = util.javaListToJSONObject(dataCount, mmlist);
        System.out.println(json.toString());
        return json.toString();
    }

    // ---------------------------------------------------------------------------
    // Get Set Method
    // ---------------------------------------------------------------------------
    public ICommonService getCommonService() {
        return commonService;
    }

    public void setCommonService(ICommonService commonService) {
        this.commonService = commonService;
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
