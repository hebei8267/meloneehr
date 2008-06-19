package cn.hb.view.hr.organization;

import java.util.List;

import net.sf.json.JSONObject;

import org.richfaces.model.TreeNode;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import cn.hb.core.view.AbstractViewBean;
import cn.hb.services.hr.organization.IOrganizationService;
import cn.hb.view.convert.ConvertUtil;
import cn.hb.view.domain.json.OrganizationJsonTreeNodeBean;
import cn.hb.view.domain.ui.UIDefaultTreeNodeBean;

/**
 * @author kaka
 * 
 * 组织结构信息树
 */
@Component("Org001_View")
@Scope("request")
public class Org001_View extends AbstractViewBean {
    private TreeNode<UIDefaultTreeNodeBean> orgTreeData;
    private String pid;
    private String id;
    private String name;
    private String description;
    private String jsonNodeData;
    private IOrganizationService organizationService;

    public void updateOrganizationInfo_Action() {

    }

    public void delOrganizationInfo_Action() {

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
        Object[] dataObj = organizationService.getOrganizationInfoTree_Service();
        if (dataObj != null) {
            orgTreeData = (TreeNode<UIDefaultTreeNodeBean>) dataObj[0];

            ConvertUtil<OrganizationJsonTreeNodeBean> util = new ConvertUtil<OrganizationJsonTreeNodeBean>();

            JSONObject jsonObj = util.javaListToJSONObject((List<OrganizationJsonTreeNodeBean>) dataObj[1]);

            jsonNodeData = jsonObj.toString();
        }
    }

    // ---------------------------------------------------------------------------
    // Get Set Method
    // ---------------------------------------------------------------------------

    public TreeNode<UIDefaultTreeNodeBean> getOrgTreeData() {
        return orgTreeData;
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

    public void setOrgTreeData(TreeNode<UIDefaultTreeNodeBean> orgTreeData) {
        this.orgTreeData = orgTreeData;
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

    public IOrganizationService getOrganizationService() {
        return organizationService;
    }

    public void setOrganizationService(IOrganizationService organizationService) {
        this.organizationService = organizationService;
    }

}
