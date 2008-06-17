package cn.hb.entity.common;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import cn.hb.core.bean.AbstractEntityBean;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.hibernate.annotations.IndexColumn;
import org.hibernate.annotations.NaturalId;

/**
 * @author kaka
 * 
 * 籍贯
 */
@Entity
@Table(name = "M_NATIVE_PLACE")
@NamedQueries( {
        @NamedQuery(name = "Nativeplace.getNativeplaceByID", query = "select obj from Nativeplace obj where obj.id = ? "),
        @NamedQuery(name = "Nativeplace.getMaxNativeplaceID", query = "select max(obj.id) from Nativeplace obj "),
        @NamedQuery(name = "Nativeplace.existLikenessNativeplace", query = "select obj from Nativeplace obj where obj.name = ? ") })
public class Nativeplace extends AbstractEntityBean {

    private static final long serialVersionUID = 5513801424090133479L;

    public Nativeplace() {
    }

    /** 编号 */
    private String id;

    /** 名称 */
    private String name;

    /** 详细描述 */
    private String description;

    /** Index */
    private Integer index;

    /** 子籍贯 */
    private List<Nativeplace> subNativeplaceList = new ArrayList<Nativeplace>();

    /** 父籍贯 */
    private Nativeplace parentNativeplace;

    /** 父籍贯 ID */
    private String parentNativeplaceID;

    /**
     * 取得编号
     * 
     * @return 编号
     */
    @NaturalId
    @Column(name = "NATIVE_PLACE_ID", nullable = false, length = 20)
    public String getId() {
        return id;
    }

    /**
     * 取得名称
     * 
     * @return 名称
     */
    @Basic
    @Column(name = "NAME", nullable = false, length = 20, unique = true)
    public String getName() {
        return name;
    }

    /**
     * 取得详细描述
     * 
     * @return 详细描述
     */
    @Basic
    @Column(name = "DESCRIPTION")
    public String getDescription() {
        return description;
    }

    @Basic
    @Column(name = "_INDEX")
    public Integer getIndex() {
        return index;
    }

    @OneToMany(mappedBy = "parentNativeplace", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @IndexColumn(name = "_INDEX", base = 1)
    public List<Nativeplace> getSubNativeplaceList() {
        return subNativeplaceList;
    }

    @ManyToOne
    @JoinColumn(name = "PARENT_NATIVE_PLACE_H_ID")
    public Nativeplace getParentNativeplace() {
        return parentNativeplace;
    }

    @Basic
    @Column(name = "PARENT_NATIVE_PLACE_ID", length = 20)
    public String getParentNativeplaceID() {
        return parentNativeplaceID;
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
     * 设置详细描述
     * 
     * @param description 详细描述
     */

    public void setDescription(String description) {
        this.description = description;
    }

    protected void setIndex(Integer index) {
        this.index = index;
    }

    public void addSubNativeplace(Nativeplace nativeplace) {
        int index = 1;
        for (Nativeplace _nativeplace : subNativeplaceList) {
            if (_nativeplace.getIndex() != null) {
                _nativeplace.setIndex(index);
                index++;
            }
        }

        nativeplace.setIndex(index);
        this.subNativeplaceList.add(nativeplace);
    }

    public void removeSubNativeplace(Nativeplace nativeplace) {
        if (!subNativeplaceList.contains(nativeplace)) {
            return;
        }
        subNativeplaceList.remove(nativeplace);

        int index = 1;
        for (Nativeplace _nativeplace : subNativeplaceList) {
            if (_nativeplace.getIndex() != null) {
                _nativeplace.setIndex(index);
                index++;
            }
        }
    }

    public void setSubNativeplaceList(List<Nativeplace> subNativeplaceList) {
        this.subNativeplaceList = subNativeplaceList;
    }

    public void setParentNativeplace(Nativeplace parentNativeplace) {
        if (parentNativeplace != null) {
            this.parentNativeplaceID = parentNativeplace.getId();
        }

        this.parentNativeplace = parentNativeplace;
    }

    protected void setParentNativeplaceID(String parentNativeplaceID) {
        this.parentNativeplaceID = parentNativeplaceID;
    }

    /**
     * @see java.lang.Object#equals(Object)
     */
    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Nativeplace)) {
            return false;
        }
        Nativeplace rhs = (Nativeplace) object;
        return new EqualsBuilder().append(this.id, rhs.id).isEquals();
    }

    /**
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        return new HashCodeBuilder(-1721257893, 180695295).append(this.id).toHashCode();
    }

}
