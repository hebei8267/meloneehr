package cn.hb.entity.dictionary.employment;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

import cn.hb.entity.dictionary.MasterEntityBean;

/**
 * @author kaka
 * 
 * 任职类型
 */
@Entity
@DiscriminatorValue(value = "HoldingJobType")
@NamedQueries( { @NamedQuery(name = "HoldingJobType.getHoldingJobTypeByID", query = "select obj from HoldingJobType obj where obj.masterID = ? And obj.slaveID = ? ") })
public class HoldingJobType extends MasterEntityBean {

    private static final long serialVersionUID = -3759296790067178600L;

    public final static String MASTER_ID = "0004";

    public HoldingJobType() {
    }
}
