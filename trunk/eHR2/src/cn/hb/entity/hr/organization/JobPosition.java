package cn.hb.entity.hr.organization;

import cn.hb.core.bean.AbstractEntityBean;
import cn.hb.entity.dictionary.organization.JobPositionType;

/**
 * @author kaka
 * 
 * 职务
 */
public class JobPosition extends AbstractEntityBean {
    public JobPosition() {
    }

    /** 编号 */
    private String id;

    /** 名称 */
    private String name;

    /** 简称 */
    private String shortName;

    /** 详细描述 */
    private String note;

    /** 职种(职务类型) */
    private JobPositionType jobPositionType;

    /**
     * @see java.lang.Object#equals(Object)
     */
    public boolean equals(Object object) {
        return false;
    }

    /**
     * @see java.lang.Object#hashCode()
     */
    public int hashCode() {
        return 1;
    }

}
