package cn.hb.view.common;

import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;
import cn.hb.core.view.AbstractViewBean;
import cn.hb.entity.common.Nation;
import cn.hb.services.common.ICommonService;
import cn.hb.view.convert.ConvertUtil;

/**
 * @author kaka
 * 
 * 民族信息列表
 */
public class Com002_DWR_View extends AbstractViewBean {
    private ICommonService commonService;

    /**
     * 取得国家信息列表
     * 
     * @return JSON格式的列表信息
     */
    public String getNationInfoList_Action(Map<String, String> requestMap) {

        List<Nation> nationList = commonService.getNationInfoList_Service();

        // 所有数据数量
        int dataCount = nationList.size();

        ConvertUtil<Nation> util = new ConvertUtil<Nation>();
        JSONObject json = util.javaListToJSONObject(dataCount, nationList);

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
