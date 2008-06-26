package cn.hb.entity.dictionary.personnel;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import cn.hb.entity.dictionary.MasterEntityBean;

/**
 * @author kaka
 * 
 * 身份标识类型
 */
@Entity
@DiscriminatorValue(value = "IdentifieationType")
public class IdentifieationType extends MasterEntityBean {

    private static final long serialVersionUID = 7818888308553206962L;

    public IdentifieationType() {
    }

}
