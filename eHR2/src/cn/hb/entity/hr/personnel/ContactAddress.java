package cn.hb.entity.hr.personnel;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import cn.hb.core.bean.AbstractEntityBean;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.hibernate.annotations.NaturalId;

/**
 * @author kaka
 * 
 * 联系地址
 */
@Entity
@Table(name = "W_P_CONTACT_ADDRESS")
public class ContactAddress extends AbstractEntityBean {

    private static final long serialVersionUID = 1357295683103607173L;

    public ContactAddress() {

    }

    /** 编号 */
    private String id;

    /** 联系信息名称 */
    private String contactInfoName;

    /** 联系地址 */
    private String contactAddress;

    /** 邮政编号 */
    private String postID;

    /** 移动电话号码 */
    private String mobileTelephone;

    /** 电话号码 */
    private String telephone;

    /** EMail */
    private String email;

    /** 个人基本信息ID */
    private String personID;

    /** 个人基本信息 */
    private Person person;

    /**
     * 取得编号
     * 
     * @return 编号
     */
    @NaturalId
    @Column(name = "CONTACT_ADDRESS_ID", nullable = false, length = 20)
    public String getId() {
        return id;
    }

    /**
     * 取得联系信息名称
     * 
     * @return 联系信息名称
     */
    @Basic
    @Column(name = "CONTACT_INFO_NAME", nullable = false, length = 20)
    public String getContactInfoName() {
        return contactInfoName;
    }

    /**
     * 取得联系地址
     * 
     * @return 联系地址
     */
    @Basic
    @Column(name = "CONTACT_ADDRESS", nullable = false, length = 40)
    public String getContactAddress() {
        return contactAddress;
    }

    /**
     * 取得邮政编号
     * 
     * @return 邮政编号
     */
    @Basic
    @Column(name = "POST_ID", length = 20)
    public String getPostID() {
        return postID;
    }

    /**
     * 取得移动电话号码
     * 
     * @return 移动电话号码
     */
    @Basic
    @Column(name = "MOBILE_TELEPHONE_NUM", length = 20)
    public String getMobileTelephone() {
        return mobileTelephone;
    }

    /**
     * 取得电话号码
     * 
     * @return 电话号码
     */
    @Basic
    @Column(name = "TELEPHONE_NUM", length = 20)
    public String getTelephone() {
        return telephone;
    }

    /**
     * 取得EMail
     * 
     * @return EMail
     */
    @Basic
    @Column(name = "EMAIL", length = 40)
    public String getEmail() {
        return email;
    }

    /**
     * 取得个人基本信息ID
     * 
     * @return 个人基本信息ID
     */
    @Basic
    @Column(name = "PERSON_ID", length = 20)
    public String getPersonID() {
        return personID;
    }

    /**
     * 取得个人基本信息
     * 
     * @return 个人基本信息
     */
    @ManyToOne
    @JoinColumn(name = "PERSON_H_ID")
    public Person getPerson() {
        return person;
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
     * 设置联系信息名称
     * 
     * @param contactInfoName 联系信息名称
     */
    public void setContactInfoName(String contactInfoName) {
        this.contactInfoName = contactInfoName;
    }

    /**
     * 设置联系地址
     * 
     * @param contactAddress 联系地址
     */
    public void setContactAddress(String contactAddress) {
        this.contactAddress = contactAddress;
    }

    /**
     * 设置邮政编号
     * 
     * @param postID 邮政编号
     */
    public void setPostID(String postID) {
        this.postID = postID;
    }

    /**
     * 设置移动电话号码
     * 
     * @param mobileTelephone 移动电话号码
     */
    public void setMobileTelephone(String mobileTelephone) {
        this.mobileTelephone = mobileTelephone;
    }

    /**
     * 设置电话号码
     * 
     * @param telephone 电话号码
     */
    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    /**
     * 设置EMail
     * 
     * @param email EMail
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * 设置个人基本信息ID
     * 
     * @param personID 个人基本信息ID
     */
    protected void setPersonID(String personID) {
        this.personID = personID;
    }

    /**
     * 设置个人基本信息
     * 
     * @param person 个人基本信息
     */
    public void setPerson(Person person) {
        if (person != null) {
            this.personID = person.getId();
        }
        this.person = person;
    }

    /**
     * @see java.lang.Object#equals(Object)
     */
    @Override
    public boolean equals(Object object) {
        if (!(object instanceof ContactAddress)) {
            return false;
        }
        ContactAddress rhs = (ContactAddress) object;
        return new EqualsBuilder().append(this.id, rhs.id).isEquals();
    }

    /**
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        return new HashCodeBuilder(195349375, -680671821).append(this.id).toHashCode();
    }

}
