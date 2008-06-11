package cn.hb.view.common;

import static cn.hb.view.MsgID.ERROR_UPDATE_COUNTRY;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import cn.hb.core.view.AbstractViewBean;
import cn.hb.entity.common.Country;
import cn.hb.services.common.ICommonService;

/**
 * @author kaka
 * 
 * 国家信息列表
 */
@Component("Com001_View")
@Scope("request")
public class Com001_View extends AbstractViewBean {
    private String id;
    private String name;
    private String shortName;
    private String description;
    private ICommonService commonService;

    public void updateCountryInfo() {
        Country cInfo = new Country();
        cInfo.setId(id);
        cInfo.setName(name);
        cInfo.setShortName(shortName);
        cInfo.setDescription(description);

        int result = commonService.updateCountryInfo_Service(cInfo);

        if (result != 0) {
            addErrorMessage(ERROR_UPDATE_COUNTRY);
        }
        reset();
        return;
    }

    public void delCountryInfo() {

    }

    public void addCountryInfo() {

    }

    private void reset() {
        id = "";
        name = "";
        shortName = "";
        description = "";
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

    // ---------------------------------------------------------------------------
    // Get Set Method
    // ---------------------------------------------------------------------------
    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getShortName() {
        return shortName;
    }

    public String getDescription() {
        return description;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ICommonService getCommonService() {
        return commonService;
    }

    public void setCommonService(ICommonService commonService) {
        this.commonService = commonService;
    }

}
