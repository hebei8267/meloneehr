package cn.hb.entity.hr.personnel;

import cn.hb.core.bean.AbstractEntityBean;
import cn.hb.entity.dictionary.personnel.IdentifieationType;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

/** 身份标识 */
public class Identifieation extends AbstractEntityBean {

    private static final long serialVersionUID = 3140428651764533089L;

    public Identifieation() {
    }

    /** 证件号码 */
    private String no;

    /** 获得时间 */
    private String startDate;

    /** 失效时间 */
    private String endDate;

    /** 个人基本信息 */
    private Person person;

    /** 身份标识类型 */
    private IdentifieationType identifieationType;

    /**
     * 取得证件号码
     * 
     * @return 证件号码
     */
    public String getNo() {
        return no;
    }

    /**
     * 取得获得时间
     * 
     * @return 获得时间
     */
    public String getStartDate() {
        return startDate;
    }

    /**
     * 取得失效时间
     * 
     * @return 失效时间
     */
    public String getEndDate() {
        return endDate;
    }

    /**
     * 取得个人基本信息
     * 
     * @return 个人基本信息
     */
    public Person getPerson() {
        return person;
    }

    /**
     * 取得身份标识类型
     * 
     * @return 身份标识类型
     */
    public IdentifieationType getIdentifieationType() {
        return identifieationType;
    }

    /**
     * 设置证件号码
     * 
     * @param no 证件号码
     */
    public void setNo(String no) {
        this.no = no;
    }

    /**
     * 设置获得时间
     * 
     * @param startDate 获得时间
     */
    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    /**
     * 设置失效时间
     * 
     * @param endDate 失效时间
     */
    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    /**
     * 设置个人基本信息
     * 
     * @param Person 个人基本信息
     */
    public void setPerson(Person Person) {
        this.person = Person;
    }

    /**
     * 设置身份标识类型
     * 
     * @param IdentifieationType 身份标识类型
     */
    public void setIdentifieationType(IdentifieationType identifieationType) {
        this.identifieationType = identifieationType;
    }

    /**
     * @see java.lang.Object#equals(Object)
     */
    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Identifieation)) {
            return false;
        }
        Identifieation rhs = (Identifieation) object;
        return new EqualsBuilder().append(this.startDate, rhs.startDate).append(this.no, rhs.no).isEquals();
    }

    /**
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        return new HashCodeBuilder(-166704575, -1122869973).append(this.startDate).append(this.no).toHashCode();
    }

}
