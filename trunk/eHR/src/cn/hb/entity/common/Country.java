package cn.hb.entity.common;

import cn.hb.core.bean.AbstractEntityBean;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 * @author kaka
 * 
 * 国家
 */
public class Country extends AbstractEntityBean {

    private static final long serialVersionUID = -8552348640289521607L;

    public Country() {
    }

    /** 编号 */
    private String number;

    /** 名称 */
    private String name;

    /** 简称 */
    private String shortName;

    /** 详细描述 */
    private String description;

    /**
     * 设置编号
     * 
     * @param number 编号
     */

    public String getNumber() {
        return number;
    }

    /**
     * 设置名称
     * 
     * @param name 名称
     */

    public String getName() {
        return name;
    }

    /**
     * 设置简称
     * 
     * @param shortName 简称
     */

    public String getShortName() {
        return shortName;
    }

    /**
     * 设置详细描述
     * 
     * @param description 详细描述
     */

    public String getDescription() {
        return description;
    }

    /**
     * 取得编号
     * 
     * @return 编号
     */

    public void setNumber(String number) {
        this.number = number;
    }

    /**
     * 取得名称
     * 
     * @return 名称
     */

    public void setName(String name) {
        this.name = name;
    }

    /**
     * 取得简称
     * 
     * @return 简称
     */

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    /**
     * 取得详细描述
     * 
     * @return 详细描述
     */

    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @see java.lang.Object#equals(Object)
     */
    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Country)) {
            return false;
        }
        Country rhs = (Country) object;
        return new EqualsBuilder().append(this.number, rhs.number).isEquals();
    }

    /**
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        return new HashCodeBuilder(661786397, 2037344837).append(this.number).toHashCode();
    }

}
