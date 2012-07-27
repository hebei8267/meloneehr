package cn.hb.view.dictionary.common;

import static cn.hb.view.constant.MsgID.ERROR_ADD_NATIVE_PLACE;
import static cn.hb.view.constant.MsgID.ERROR_DEL_NATIVE_PLACE;
import static cn.hb.view.constant.MsgID.ERROR_UPDATE_NATIVE_PLACE1;
import static cn.hb.view.constant.MsgID.ERROR_UPDATE_NATIVE_PLACE2;

import java.util.List;

import net.sf.json.JSONObject;

import org.richfaces.model.TreeNode;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import cn.hb.core.view.AbstractViewBean;
import cn.hb.entity.common.Nativeplace;
import cn.hb.services.dictionary.common.IDDCommonService;
import cn.hb.view.convert.ConvertUtil;
import cn.hb.view.domain.json.NativeplaceJsonTreeNodeBean;
import cn.hb.view.domain.ui.UIDefaultTreeNodeBean;

/**
 * @author kaka
 * 
 * 籍贯信息树
 */
@Component("DDCom003_View")
@Scope("request")
public class DDCom003_View extends AbstractViewBean {

    private static final long serialVersionUID = 9171516180933339876L;
    private TreeNode<UIDefaultTreeNodeBean> npTreeData;
    private String pid;
    private String id;
    private String name;
    private String description;
    private String jsonNodeData;
    private IDDCommonService ddCommonService;

    public void updateNativeplaceInfo_Action() {
        Nativeplace objInfo = new Nativeplace();
        objInfo.setId(id);
        objInfo.setName(name);
        objInfo.setDescription(description);

        int result = ddCommonService.updateNativeplaceInfo_Service(objInfo);

        if (result == 1) {
            addErrorMessage(ERROR_UPDATE_NATIVE_PLACE1);
        }
        if (result == 2) {
            addErrorMessage(ERROR_UPDATE_NATIVE_PLACE2);
        }
        destroy();

        init();
        return;
    }

    public void delNativeplaceInfo_Action() {
        int result = ddCommonService.delNativeplaceInfo_Service(id, pid);

        if (result != 0) {
            addErrorMessage(ERROR_DEL_NATIVE_PLACE);
        }
        destroy();

        init();
        return;
    }

    public void addNativeplaceInfo_Action() {
        Nativeplace objInfo = new Nativeplace();

        objInfo.setName(name);
        objInfo.setDescription(description);

        int result = ddCommonService.addNativeplaceInfo_Service(objInfo, pid);

        if (result != 0) {
            addErrorMessage(ERROR_ADD_NATIVE_PLACE);
        }

        destroy();

        init();
        return;
    }

    // ---------------------------------------------------------------------------
    // Override Method
    // ---------------------------------------------------------------------------
    @Override
    public void create() {
        init();
    }

    @Override
    public void destroy() {
        pid = "";
        id = "";
        name = "";
        description = "";
    }

    @SuppressWarnings("unchecked")
    @Override
    public void init() {
        Object[] dataObj = ddCommonService.getNativeplaceInfoTree_Service();
        if (dataObj != null) {
            npTreeData = (TreeNode<UIDefaultTreeNodeBean>) dataObj[0];

            ConvertUtil<NativeplaceJsonTreeNodeBean> util = new ConvertUtil<NativeplaceJsonTreeNodeBean>();

            JSONObject jsonObj = util.javaListToJSONObject((List<NativeplaceJsonTreeNodeBean>) dataObj[1]);

            jsonNodeData = jsonObj.toString();
        }
    }

    // ---------------------------------------------------------------------------
    // Get Set Method
    // ---------------------------------------------------------------------------

    public TreeNode<UIDefaultTreeNodeBean> getNpTreeData() {
        return npTreeData;
    }

    public String getPid() {
        return pid;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getJsonNodeData() {
        return jsonNodeData;
    }

    public IDDCommonService getDdCommonService() {
        return ddCommonService;
    }

    public void setNpTreeData(TreeNode<UIDefaultTreeNodeBean> npTreeData) {
        this.npTreeData = npTreeData;
    }

    public void setPid(String pid) {
        this.pid = pid;
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

    public void setJsonNodeData(String jsonNodeData) {
        this.jsonNodeData = jsonNodeData;
    }

    public void setDdCommonService(IDDCommonService ddCommonService) {
        this.ddCommonService = ddCommonService;
    }
}