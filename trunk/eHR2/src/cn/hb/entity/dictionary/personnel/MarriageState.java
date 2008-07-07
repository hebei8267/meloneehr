package cn.hb.entity.dictionary.personnel;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

import cn.hb.entity.dictionary.MasterEntityBean;

/**
 * @author kaka
 * 
 * 婚姻状况
 */
@Entity
@DiscriminatorValue(value = "MarriageState")
@NamedQueries( { @NamedQuery(name = "MarriageState.getMarriageStateByID", query = "select obj from MarriageState obj where obj.masterID = ? And obj.slaveID = ? ") })
public class MarriageState extends MasterEntityBean {

    private static final long serialVersionUID = -7319647672902301256L;

    public final static String MASTER_ID = "0002";

    public MarriageState() {
    }

}
