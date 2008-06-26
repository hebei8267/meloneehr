package cn.hb.service.vo.dictionary.communal;

import cn.hb.entity.dictionary.communal.Nativeplace;
import cn.hb.service.vo.dictionary.JsonDataBean;

/**
 * @author 何 贝
 * 
 */
public class Nativeplace_JsonBean extends JsonDataBean {

    private static final long serialVersionUID = -2646405046903107495L;

    public Nativeplace_JsonBean() {

    }

    public Nativeplace_JsonBean(String pid, String pname, Nativeplace nativeplace) {
        this.pid = pid;
        this.pname = pname;
        this.id = nativeplace.getId();
        this.name = nativeplace.getName();
        this.note = nativeplace.getNote();
    }

    /** 编号 */
    private String id;

    /** 名称 */
    private String name;

    /** 详细描述 */
    private String note;

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getNote() {
        return note;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
