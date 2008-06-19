package cn.hb.view.domain.json;

import cn.hb.entity.common.Nativeplace;

/**
 * @author kaka
 * 
 */
public class NativeplaceJsonTreeNodeBean extends AbstractJsonTreeNodeBean {

    private static final long serialVersionUID = 9094847246945371046L;

    public NativeplaceJsonTreeNodeBean() {

    }

    public NativeplaceJsonTreeNodeBean(String pid, String pname, Nativeplace nativeplace) {
        this.pid = pid;
        this.pname = pname;
        this.id = nativeplace.getId();
        this.name = nativeplace.getName();
        this.description = nativeplace.getDescription();
    }

    /** 编号 */
    private String id;

    /** 名称 */
    private String name;

    /** 详细描述 */
    private String description;

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
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

    public void setDescription(String description) {
        this.description = description;
    }

}
