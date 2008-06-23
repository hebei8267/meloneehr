package cn.hb.services.domain.dictionary;

import cn.hb.core.bean.BaseBean;

/**
 * @author 何 贝
 * 
 */
public class Nativeplace_VO extends BaseBean {

    private static final long serialVersionUID = -2646405046903107495L;

    public Nativeplace_VO() {

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
