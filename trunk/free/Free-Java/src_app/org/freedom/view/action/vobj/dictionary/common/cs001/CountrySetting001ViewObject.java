/*
 * Copyright 2009 by hebei, All rights reserved.
 */
package org.freedom.view.action.vobj.dictionary.common.cs001;

import org.freedom.core.view.vo.jsp.AbstractViewObject;

/**
 * 国家信息(数据字典)
 * 
 * @author 何贝
 * @since JDK1.5
 */
public class CountrySetting001ViewObject extends AbstractViewObject {

    /**
     * 
     */
    private static final long serialVersionUID = 9128439614246626819L;

    /** 国家编号 */
    private String countryID;
    /** 国家名称 */
    private String countryTxt;
    /** 国家详细描述 */
    private String countryDetail;

    /**
     * 取得国家编号
     * 
     * @return 国家编号
     */
    public String getCountryID() {
        return countryID;
    }

    /**
     * 设置国家编号
     * 
     * @param countryID 国家编号
     */
    public void setCountryID(String countryID) {
        this.countryID = countryID;
    }

    /**
     * 取得国家名称
     * 
     * @return 国家名称
     */
    public String getCountryTxt() {
        return countryTxt;
    }

    /**
     * 设置国家名称
     * 
     * @param countryTxt 国家名称
     */
    public void setCountryTxt(String countryTxt) {
        this.countryTxt = countryTxt;
    }

    /**
     * 取得国家详细描述
     * 
     * @return 国家详细描述
     */
    public String getCountryDetail() {
        return countryDetail;
    }

    /**
     * 设置国家详细描述
     * 
     * @param countryDetail 国家详细描述
     */
    public void setCountryDetail(String countryDetail) {
        this.countryDetail = countryDetail;
    }

}
