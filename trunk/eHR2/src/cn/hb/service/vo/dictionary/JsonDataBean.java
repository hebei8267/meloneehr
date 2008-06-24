package cn.hb.service.vo.dictionary;

import cn.hb.core.bean.BaseBean;

public abstract class JsonDataBean extends BaseBean {
    public JsonDataBean() {

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
