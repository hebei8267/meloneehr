package cn.hb.constant;

public class Constant {
    /** Entity 对象默认ID */
    public final static String DEFAULT_ID = "00000001";

    /**
     * 组织-职务关联类型(排斥_非排斥-关联) MASTER_ID
     * 
     * @see cn.hb.entity.dictionary.organization.Organization_JobPosition_RelateType
     */
    public final static String ORGANIZATION_JOBPOSITION_RELATE_TYPE_MASTER_ID = "00001";

    /**
     * 婚姻状况 MASTER_ID
     * 
     * @see cn.hb.entity.dictionary.personnel.MasterEntityBean
     */
    public final static String MARRIAGE_STATE_MASTER_ID = "00002";

    /**
     * 身份标识类型 MASTER_ID
     * 
     * @see cn.hb.entity.dictionary.personnel.MasterEntityBean
     */
    public final static String IDENTIFIEATION_TYPE_MASTER_ID = "00003";

    /**
     * 任职类型 MASTER_ID
     * 
     * @see cn.hb.entity.dictionary.employment.HoldingJobType
     */
    public final static String HOLDING_JOB_TYPE_MASTER_ID = "00004";
}
