package cn.hb.entity.hr.personnel;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import cn.hb.core.bean.AbstractEntityBean;
import cn.hb.entity.dictionary.personnel.MarriageState;
import cn.hb.entity.dictionary.personnel.Currculum;
import cn.hb.entity.dictionary.personnel.SexType;
import cn.hb.entity.dictionary.communal.Country;
import cn.hb.entity.dictionary.communal.Nation;
import cn.hb.entity.dictionary.communal.Nativeplace;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.hibernate.annotations.IndexColumn;
import org.hibernate.annotations.NaturalId;

/**
 * @author kaka
 * 
 * 个人基本信息
 */
@Entity
@Table(name = "W_PERSON")
public class Person extends AbstractEntityBean {

    private static final long serialVersionUID = -1469408001002812874L;

    public Person() {
    }

    /** 编号 */
    private String id;

    /** 姓名 */
    private String name1;

    /** 英文名 */
    private String name2;

    /** 性别 */
    private SexType sex;

    /** 性别ID */
    private String sexID;

    /** 出生日期 */
    private String birthdate;

    /** 婚姻状况ID */
    private String marriageStateID;

    /** 婚姻状况 */
    private MarriageState marriageState;

    /** 最终学历信息ID */
    private String lastCurrculumID;

    /** 最终学历信息 */
    private Currculum lastCurrculum;

    /** 国家ID */
    private String countryID;

    /** 国家 */
    private Country country;

    /** 民族ID */
    private String nationID;

    /** 民族 */
    private Nation nation;

    /** 籍贯ID */
    private String nativeplaceID;

    /** 籍贯 */
    private Nativeplace nativeplace;

    /** 身份标识ID */
    private String identifieationID;

    /** 身份标识 */
    private Identifieation identifieation;

    /** 联系地址 */
    private List<ContactAddress> contactAddressList = new ArrayList<ContactAddress>();

    /** 教育信息 */
    private List<Education> educationList = new ArrayList<Education>();

    /** 培训信息 */
    private List<Training> trainingList = new ArrayList<Training>();

    /**
     * 取得编号
     * 
     * @return 编号
     */
    @NaturalId
    @Column(name = "PERSON_ID", nullable = false, length = 20)
    public String getId() {
        return id;
    }

    /**
     * 取得姓名
     * 
     * @return 姓名
     */
    @Basic
    @Column(name = "NAME_1", nullable = false, length = 20)
    public String getName1() {
        return name1;
    }

    /**
     * 取得英文名
     * 
     * @return 英文名
     */
    @Basic
    @Column(name = "NAME_2", length = 20)
    public String getName2() {
        return name2;
    }

    /**
     * 取得性别
     * 
     * @return 性别
     */
    @ManyToOne
    @JoinColumn(name = "SEX_TYPE_H_ID")
    public SexType getSex() {
        return sex;
    }

    /**
     * 取得性别ID
     * 
     * @return 性别ID
     */
    @Basic
    @Column(name = "SEX_TYPE_ID", nullable = false, length = 20)
    public String getSexID() {
        return sexID;
    }

    /**
     * 取得出生日期
     * 
     * @return 出生日期
     */
    @Basic
    @Column(name = "BIRTH_DATE", nullable = false, length = 8)
    public String getBirthdate() {
        return birthdate;
    }

    /**
     * 取得婚姻状况ID
     * 
     * @return 婚姻状况ID
     */
    @Basic
    @Column(name = "MARRIAGE_STATE_ID", length = 20)
    public String getMarriageStateID() {
        return marriageStateID;
    }

    /**
     * 取得婚姻状况
     * 
     * @return 婚姻状况
     */
    @ManyToOne
    @JoinColumn(name = "MARRIAGE_STATE_H_ID")
    public MarriageState getMarriageState() {
        return marriageState;
    }

    /**
     * 取得最终学历信息ID
     * 
     * @return 最终学历信息ID
     */
    @Basic
    @Column(name = "LAST_CURRCULUM_ID", length = 20)
    public String getLastCurrculumID() {
        return lastCurrculumID;
    }

    /**
     * 取得最终学历信息
     * 
     * @return 最终学历信息
     */
    @ManyToOne
    @JoinColumn(name = "LAST_CURRCULUM_H_ID")
    public Currculum getLastCurrculum() {
        return lastCurrculum;
    }

    /**
     * 取得国家ID
     * 
     * @return 国家ID
     */
    @Basic
    @Column(name = "COUNTRY_ID", nullable = false, length = 20)
    public String getCountryID() {
        return countryID;
    }

    /**
     * 取得国家
     * 
     * @return 国家
     */
    @ManyToOne
    @JoinColumn(name = "COUNTRY_H_ID")
    public Country getCountry() {
        return country;
    }

    /**
     * 取得民族ID
     * 
     * @return 民族ID
     */
    @Basic
    @Column(name = "NATION_ID", length = 20)
    public String getNationID() {
        return nationID;
    }

    /**
     * 取得民族
     * 
     * @return 民族
     */
    @ManyToOne
    @JoinColumn(name = "NATION_H_ID")
    public Nation getNation() {
        return nation;
    }

    /**
     * 取得籍贯ID
     * 
     * @return 籍贯ID
     */
    @Basic
    @Column(name = "NATIVEPLACE_ID", length = 20)
    public String getNativeplaceID() {
        return nativeplaceID;
    }

    /**
     * 取得籍贯
     * 
     * @return 籍贯
     */
    @ManyToOne
    @JoinColumn(name = "NATIVEPLACE_H_ID")
    public Nativeplace getNativeplace() {
        return nativeplace;
    }

    /**
     * 取得身份标识ID
     * 
     * @return 身份标识ID
     */
    @Basic
    @Column(name = "IDENTIFIEATION_ID", nullable = false, length = 20)
    public String getIdentifieationID() {
        return identifieationID;
    }

    /**
     * 取得身份标识
     * 
     * @return 身份标识
     */
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "IDENTIFIEATION_H_ID")
    public Identifieation getIdentifieation() {
        return identifieation;
    }

    /**
     * 取得联系地址
     * 
     * @return 联系地址
     */
    @OneToMany(mappedBy = "person", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @IndexColumn(name = "_INDEX", base = 1)
    public List<ContactAddress> getContactAddressList() {
        return contactAddressList;
    }

    /**
     * 取得教育信息
     * 
     * @return 教育信息
     */
    @OneToMany(mappedBy = "person", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @IndexColumn(name = "_INDEX", base = 1)
    public List<Education> getEducationList() {
        return educationList;
    }

    /**
     * 取得培训信息
     * 
     * @return 培训信息
     */
    @OneToMany(mappedBy = "person", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @IndexColumn(name = "_INDEX", base = 1)
    public List<Training> getTrainingList() {
        return trainingList;
    }

    /**
     * 设置编号
     * 
     * @param id 编号
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * 设置姓名
     * 
     * @param name1 姓名
     */
    public void setName1(String name1) {
        this.name1 = name1;
    }

    /**
     * 设置英文名
     * 
     * @param name2 英文名
     */
    public void setName2(String name2) {
        this.name2 = name2;
    }

    /**
     * 设置性别
     * 
     * @param sex 性别
     */
    public void setSex(SexType sex) {
        if (sex != null) {
            this.sexID = sex.getMasterID() + sex.getSlaveID();
        }
        this.sex = sex;
    }

    /**
     * 设置性别ID
     * 
     * @param sexID 性别ID
     */
    protected void setSexID(String sexID) {
        this.sexID = sexID;
    }

    /**
     * 设置出生日期
     * 
     * @param birthdate 出生日期
     */
    public void setBirthdate(String birthdate) {
        this.birthdate = birthdate;
    }

    /**
     * 设置婚姻状况ID
     * 
     * @param marriageStateID 婚姻状况ID
     */
    protected void setMarriageStateID(String marriageStateID) {
        this.marriageStateID = marriageStateID;
    }

    /**
     * 设置婚姻状况
     * 
     * @param marriageState 婚姻状况
     */
    public void setMarriageState(MarriageState marriageState) {
        if (marriageState != null) {
            this.marriageStateID = marriageState.getMasterID() + marriageState.getSlaveID();
        }
        this.marriageState = marriageState;
    }

    /**
     * 设置最终学历信息ID
     * 
     * @param lastCurrculumID 最终学历信息ID
     */
    protected void setLastCurrculumID(String lastCurrculumID) {
        this.lastCurrculumID = lastCurrculumID;
    }

    /**
     * 设置最终学历信息
     * 
     * @param lastCurrculum 最终学历信息
     */
    public void setLastCurrculum(Currculum lastCurrculum) {
        if (lastCurrculum != null) {
            this.lastCurrculumID = lastCurrculum.getId();
        }
        this.lastCurrculum = lastCurrculum;
    }

    /**
     * 设置国家ID
     * 
     * @param countryID 国家ID
     */
    protected void setCountryID(String countryID) {
        this.countryID = countryID;
    }

    /**
     * 设置国家
     * 
     * @param country 国家
     */
    public void setCountry(Country country) {
        if (country != null) {
            this.countryID = country.getId();
        }
        this.country = country;
    }

    /**
     * 设置民族ID
     * 
     * @param nationID 民族ID
     */
    protected void setNationID(String nationID) {
        this.nationID = nationID;
    }

    /**
     * 设置民族
     * 
     * @param nation 民族
     */
    public void setNation(Nation nation) {
        if (nation != null) {
            this.nationID = nation.getId();
        }
        this.nation = nation;
    }

    /**
     * 设置籍贯ID
     * 
     * @param nativeplaceID 籍贯ID
     */
    protected void setNativeplaceID(String nativeplaceID) {
        this.nativeplaceID = nativeplaceID;
    }

    /**
     * 设置籍贯
     * 
     * @param nativeplace 籍贯
     */
    public void setNativeplace(Nativeplace nativeplace) {
        if (nativeplace != null) {
            this.nativeplaceID = nativeplace.getId();
        }
        this.nativeplace = nativeplace;
    }

    /**
     * 设置身份标识ID
     * 
     * @param identifieationID 身份标识ID
     */
    protected void setIdentifieationID(String identifieationID) {
        this.identifieationID = identifieationID;
    }

    /**
     * 设置身份标识
     * 
     * @param identifieation 身份标识
     */
    public void setIdentifieation(Identifieation identifieation) {
        if (identifieation != null) {
            this.identifieationID = identifieation.getNo();
        }
        this.identifieation = identifieation;
    }

    /**
     * 设置联系地址
     * 
     * @param contactAddressList 联系地址
     */
    public void setContactAddressList(List<ContactAddress> contactAddressList) {
        this.contactAddressList = contactAddressList;
    }

    /**
     * 设置教育信息
     * 
     * @param educationList 教育信息
     */
    public void setEducationList(List<Education> educationList) {
        this.educationList = educationList;
    }

    /**
     * 设置培训信息
     * 
     * @param trainingList 培训信息
     */
    public void setTrainingList(List<Training> trainingList) {
        this.trainingList = trainingList;
    }

    /**
     * @see java.lang.Object#equals(Object)
     */
    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Person)) {
            return false;
        }
        Person rhs = (Person) object;
        return new EqualsBuilder().append(this.id, rhs.id).isEquals();
    }

    /**
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        return new HashCodeBuilder(1358819275, -1185650891).append(this.id).toHashCode();
    }

}
