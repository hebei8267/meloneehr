package cn.hb.entity.dictionary.employment;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import cn.hb.entity.dictionary.MasterEntityBean;

/**
 * @author kaka
 * 
 * 任职类型
 */
@Entity
@DiscriminatorValue(value = "HoldingJobType")
public class HoldingJobType extends MasterEntityBean {

    private static final long serialVersionUID = -3759296790067178600L;

    public HoldingJobType() {
    }
}
