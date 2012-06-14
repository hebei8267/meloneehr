package org.freedom.web.modules;

import org.freedom.core.bean.BaseBean;

/**
 * select标签用显示内容
 * 
 * @author 何贝
 * @since JDK1.5
 */
public class LabelValueBean extends BaseBean {

    private static final long serialVersionUID = 3159316111926429441L;
    /**
     * 显示内容
     */
    private String label;
    /**
     * 对应值
     */
    private String value;

    public LabelValueBean() {

    }

    public LabelValueBean(String label, String value) {
        this.label = label;
        this.value = value;
    }

    /**
     * 取得显示内容
     * 
     * @return 显示内容
     */
    public String getLabel() {
        return label;
    }

    /**
     * 设置显示内容
     * 
     * @param label 显示内容
     */
    public void setLabel(String label) {
        this.label = label;
    }

    /**
     * 取得对应值
     * 
     * @return 对应值
     */
    public String getValue() {
        return value;
    }

    /**
     * 设置对应值
     * 
     * @param value 对应值
     */
    public void setValue(String value) {
        this.value = value;
    }
}
