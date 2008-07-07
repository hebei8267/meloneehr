package cn.hb.entity.dictionary.personnel;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

import cn.hb.entity.dictionary.MasterEntityBean;

/**
 * @author kaka
 * 
 * 性别类型
 */
@Entity
@DiscriminatorValue(value = "SexType")
@NamedQueries( { @NamedQuery(name = "SexType.getSexTypeByID", query = "select obj from SexType obj where obj.masterID = ? And obj.slaveID = ? ") })
public class SexType extends MasterEntityBean {

    private static final long serialVersionUID = -5745068295690448106L;

    public final static String MASTER_ID = "0006";

    public SexType() {

    }
}
