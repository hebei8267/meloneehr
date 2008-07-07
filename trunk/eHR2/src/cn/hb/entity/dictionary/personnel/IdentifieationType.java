package cn.hb.entity.dictionary.personnel;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

import cn.hb.entity.dictionary.MasterEntityBean;

/**
 * @author kaka
 * 
 * 身份标识类型
 */
@Entity
@DiscriminatorValue(value = "IdentifieationType")
@NamedQueries( { @NamedQuery(name = "IdentifieationType.getIdentifieationTypeByID", query = "select obj from IdentifieationType obj where obj.masterID = ? And obj.slaveID = ? ") })
public class IdentifieationType extends MasterEntityBean {

    private static final long serialVersionUID = 7818888308553206962L;

    public final static String MASTER_ID = "0003";

    public IdentifieationType() {
    }

}
