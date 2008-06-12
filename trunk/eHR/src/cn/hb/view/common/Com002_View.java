package cn.hb.view.common;

import static cn.hb.view.MsgID.ERROR_ADD_NATION;
import static cn.hb.view.MsgID.ERROR_DEL_NATION;
import static cn.hb.view.MsgID.ERROR_UPDATE_NATION1;
import static cn.hb.view.MsgID.ERROR_UPDATE_NATION2;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import cn.hb.core.view.AbstractViewBean;
import cn.hb.entity.common.Nation;
import cn.hb.services.common.ICommonService;

/**
 * @author kaka
 * 
 * 民族信息列表
 */
@Component("Com002_View")
@Scope("request")
public class Com002_View extends AbstractViewBean {
    private String id;
    private String name;
    private String description;
    private ICommonService commonService;

    public void updateNationInfo() {
        Nation objInfo = new Nation();
        objInfo.setId(id);
        objInfo.setName(name);
        objInfo.setDescription(description);

        int result = commonService.updateNationInfo_Service(objInfo);

        if (result == 1) {
            addErrorMessage(ERROR_UPDATE_NATION1);
        }
        if (result == 2) {
            addErrorMessage(ERROR_UPDATE_NATION2);
        }
        destroy();
        return;
    }

    public void delNationInfo() {
        int result = commonService.delNationInfo_Service(id);

        if (result != 0) {
            addErrorMessage(ERROR_DEL_NATION);
        }
        destroy();
        return;
    }

    public void addNationInfo() {
        Nation objInfo = new Nation();

        objInfo.setName(name);
        objInfo.setDescription(description);

        int result = commonService.addNationInfo_Service(objInfo);

        if (result != 0) {
            addErrorMessage(ERROR_ADD_NATION);
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

    public String getDescription() {
        return description;
    }

    public ICommonService getCommonService() {
        return commonService;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setCommonService(ICommonService commonService) {
        this.commonService = commonService;
    }

}
