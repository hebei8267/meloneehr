package cn.hb.view.dictionary.common;

import static cn.hb.view.MsgID.ERROR_UPDATE_COUNTRY1;
import static cn.hb.view.MsgID.ERROR_UPDATE_COUNTRY2;
import static cn.hb.view.MsgID.ERROR_DEL_COUNTRY;
import static cn.hb.view.MsgID.ERROR_ADD_COUNTRY;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import cn.hb.core.view.AbstractViewBean;
import cn.hb.entity.common.Country;
import cn.hb.services.dictionary.common.IDDCommonService;

/**
 * @author kaka
 * 
 * 国家信息列表
 */
@Component("DDCom001_View")
@Scope("request")
public class DDCom001_View extends AbstractViewBean {
    private String id;
    private String name;
    private String shortName;
    private String description;
    private IDDCommonService ddCommonService;

    public void updateCountryInfo() {
        Country objInfo = new Country();
        objInfo.setId(id);
        objInfo.setName(name);
        objInfo.setShortName(shortName);
        objInfo.setDescription(description);

        int result = ddCommonService.updateCountryInfo_Service(objInfo);

        if (result == 1) {
            addErrorMessage(ERROR_UPDATE_COUNTRY1);
        }
        if (result == 2) {
            addErrorMessage(ERROR_UPDATE_COUNTRY2);
        }
        destroy();
        return;
    }

    public void delCountryInfo() {
        int result = ddCommonService.delCountryInfo_Service(id);

        if (result != 0) {
            addErrorMessage(ERROR_DEL_COUNTRY);
        }
        destroy();
        return;
    }

    public void addCountryInfo() {
        Country objInfo = new Country();

        objInfo.setName(name);
        objInfo.setShortName(shortName);
        objInfo.setDescription(description);

        int result = ddCommonService.addCountryInfo_Service(objInfo);

        if (result != 0) {
            addErrorMessage(ERROR_ADD_COUNTRY);
        }
        destroy();
        return;
    }

    // ---------------------------------------------------------------------------
    // Override Method
    // ---------------------------------------------------------------------------
    @Override
    public void create() {

    }

    @Override
    public void destroy() {
        id = "";
        name = "";
        shortName = "";
        description = "";
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

    public IDDCommonService getDdCommonService() {
        return ddCommonService;
    }

    public void setDdCommonService(IDDCommonService ddCommonService) {
        this.ddCommonService = ddCommonService;
    }

}
