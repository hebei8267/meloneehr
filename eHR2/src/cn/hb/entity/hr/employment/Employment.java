package cn.hb.entity.hr.employment;

import cn.hb.core.bean.AbstractEntityBean;
import cn.hb.entity.dictionary.employment.EmployType;
import cn.hb.entity.hr.personnel.Person;

/** 雇佣 */
public class Employment {
    public Employment() {
    }

    /** 个人编号 */
    private String personID;

    /** 员工编号 */
    private String staffID;

    /** 雇佣开始时间 */
    private String startDate;

    /** 雇佣结束时间 */
    private String endDate;

    /** 详细描述 */
    private String note;

    /** 雇佣类型ID */
    private EmployType employTypeID;

    /** 雇佣类型 */
    private EmployType employType;

    /** 个人基本信息 */
    private Person person;

    /** 合同 */
    private Contract[] contract;

    /** 员工 */
    private Staff staff;

}
