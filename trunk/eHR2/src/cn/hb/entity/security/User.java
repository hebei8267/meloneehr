package cn.hb.entity.security;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import cn.hb.core.bean.AbstractEntityBean;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.hibernate.annotations.NaturalId;

/**
 * @author kaka
 * 
 * 登录用户
 */
@Entity
@Table(name = "W_LOGIN_USER")
@NamedQueries( { @NamedQuery(name = "User.getUserByID", query = "select obj from User obj where obj.id = ? ") })
public class User extends AbstractEntityBean {

    private static final long serialVersionUID = -4154347867887707861L;

    public User() {
    }

    /** 编号 */
    private String id;

    /** 名称 */
    private String name;

    /** 密码 */
    private String password;

    /** 第一次登录标记 */
    private Boolean firstLoginFlag = Boolean.FALSE;

    /** 详细描述 */
    private String note;

    /**
     * 取得编号
     * 
     * @return 编号
     */
    @NaturalId
    @Column(name = "USER_ID", nullable = false, length = 20)
    public String getId() {
        return id;
    }

    /**
     * 取得名称
     * 
     * @return 名称
     */
    @Basic
    @Column(name = "NAME", nullable = false, length = 20)
    public String getName() {
        return name;
    }

    /**
     * 取得密码
     * 
     * @return 密码
     */
    @Basic
    @Column(name = "PASS_WORD", length = 20)
    public String getPassword() {
        return password;
    }

    /**
     * 取得第一次登录标记
     * 
     * @return 第一次登录标记
     */
    @Basic
    @Column(name = "FIRST_LOGIN_FLAG", nullable = false)
    public Boolean getFirstLoginFlag() {
        return firstLoginFlag;
    }

    /**
     * 取得详细描述
     * 
     * @return 详细描述
     */
    @Basic
    @Column(name = "NOTE")
    public String getNote() {
        return note;
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
     * 设置名称
     * 
     * @param name 名称
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 设置密码
     * 
     * @param password 密码
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * 设置第一次登录标记
     * 
     * @param firstLoginFlag 第一次登录标记
     */
    public void setFirstLoginFlag(Boolean firstLoginFlag) {
        this.firstLoginFlag = firstLoginFlag;
    }

    /**
     * 设置详细描述
     * 
     * @param note 详细描述
     */
    public void setNote(String note) {
        this.note = note;
    }

    /**
     * @see java.lang.Object#equals(Object)
     */
    @Override
    public boolean equals(Object object) {
        if (!(object instanceof User)) {
            return false;
        }
        User rhs = (User) object;
        return new EqualsBuilder().append(this.id, rhs.id).isEquals();
    }

    /**
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        return new HashCodeBuilder(-1023797237, 1200053429).append(this.id).toHashCode();
    }

}
