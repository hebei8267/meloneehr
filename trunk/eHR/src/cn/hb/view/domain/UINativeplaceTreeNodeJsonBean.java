package cn.hb.view.domain;

import cn.hb.core.bean.BaseBean;

public class UINativeplaceTreeNodeJsonBean extends BaseBean {

    private static final long serialVersionUID = 9094847246945371046L;

    public UINativeplaceTreeNodeJsonBean() {

    }

    public UINativeplaceTreeNodeJsonBean(String pid, String pname, String id, String name, String description) {
        super();
        this.pid = pid;
        this.pname = pname;
        this.id = id;
        this.name = name;
        this.description = description;
    }

    /** 父节点编号 */
    private String pid;

    /** 父节点名称 */
    private String pname;

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

    public String getPid() {
        return pid;
    }

    public String getPname() {
        return pname;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public void setPname(String pname) {
        this.pname = pname;
    }
}
