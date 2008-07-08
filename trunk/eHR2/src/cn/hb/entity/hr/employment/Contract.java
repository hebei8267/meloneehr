package cn.hb.entity.hr.employment;

import cn.hb.core.bean.AbstractEntityBean;
import cn.hb.entity.dictionary.employment.ContractType;

/** 合同 */
public class Contract {
    public Contract() {
    }

    /** 编号 */
    private String id;

    /** 生效时间 */
    private String startDate;

    /** 失效时间 */
    private String endDate;

    /** 名称 */
    private String name;

    /** 详细描述 */
    private String note;

    /** 合同类型 */
    private ContractType contractType;

    /** 雇佣 */
    private Employment Employment;

}
