package cn.hb.view.domain.json;

import cn.hb.core.bean.BaseBean;

/**
 * @author kaka
 * 
 */
public abstract class AbstractJsonTreeNodeBean extends BaseBean {
    public AbstractJsonTreeNodeBean() {

    }

    /** 父节点编号 */
    protected String pid;

    /** 父节点名称 */
    protected String pname;

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
