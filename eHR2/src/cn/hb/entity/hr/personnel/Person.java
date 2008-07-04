package cn.hb.entity.hr.personnel;

import cn.hb.core.bean.AbstractEntityBean;
import cn.hb.entity.dictionary.personnel.MarriageState;
import cn.hb.entity.dictionary.personnel.Currculum;
import cn.hb.entity.dictionary.communal.Country;
import cn.hb.entity.dictionary.communal.Nation;
import cn.hb.entity.dictionary.communal.Nativeplace;

/** 个人基本信息 */
public class Person {
    public Person() {
    }

    /** 编号 */
    private String id;

    /** 姓名 */
    private String name1;

    /** 英文名 */
    private String name2;

    private Integer 性别;

    /** 出生日期 */
    private String birthdate;

    /** 婚姻状况 */
    private MarriageState MarriageState;

    /** 学历信息 */
    private Currculum 最高学历;

    /** 国家 */
    private Country 国籍;

    /** 民族 */
    private Nation Nation;

    /** 籍贯 */
    private Nativeplace Nativeplace;

    /** 身份标识 */
    private Identifieation Identifieation;

    /** 联系地址 */
    private ContactAddress[] ContactAddress;

    /** 教育信息 */
    private Education[] Education;

    /** 培训信息 */
    private Training[] Training;

    /**
     * 取得编号
     * 
     * @return 编号
     */

    public String getId() {
        return id;
    }

    /**
     * 取得姓名
     * 
     * @return 姓名
     */

    public String getName1() {
        return name1;
    }

    /**
     * 取得英文名
     * 
     * @return 英文名
     */

    public String getName2() {
        return name2;
    }

    /**
     * 取得出生日期
     * 
     * @return 出生日期
     */

    public String getBirthdate() {
        return birthdate;
    }

    /**
     * 取得婚姻状况
     * 
     * @return 婚姻状况
     */

    public String getMarriageState() {
        return MarriageState;
    }

    /**
     * 取得学历信息
     * 
     * @return 学历信息
     */

    public String getCurrculum() {
        return Currculum;
    }

    /**
     * 取得国家
     * 
     * @return 国家
     */

    public String getCountry() {
        return Country;
    }

    /**
     * 取得民族
     * 
     * @return 民族
     */

    public String getNation() {
        return Nation;
    }

    /**
     * 取得籍贯
     * 
     * @return 籍贯
     */

    public String getNativeplace() {
        return Nativeplace;
    }

    /**
     * 取得身份标识
     * 
     * @return 身份标识
     */

    public String getIdentifieation() {
        return Identifieation;
    }

    /**
     * 取得地址
     * 
     * @return 地址
     */

    public String getAddress() {
        return address;
    }

    /**
     * 取得联系地址
     * 
     * @return 联系地址
     */

    public String getContactAddress() {
        return ContactAddress;
    }

    /**
     * 取得教育信息
     * 
     * @return 教育信息
     */

    public String getEducation() {
        return Education;
    }

    /**
     * 取得培训信息
     * 
     * @return 培训信息
     */

    public String getTraining() {
        return Training;
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
     * 设置出生日期
     * 
     * @param birthdate 出生日期
     */

    public void setBirthdate(String birthdate) {
        this.birthdate = birthdate;
    }

    /**
     * 设置婚姻状况
     * 
     * @param MarriageState 婚姻状况
     */

    public void setMarriageState(String MarriageState) {
        this.MarriageState = MarriageState;
    }

    /**
     * 设置学历信息
     * 
     * @param Currculum 学历信息
     */

    public void setCurrculum(String Currculum) {
        this.Currculum = Currculum;
    }

    /**
     * 设置国家
     * 
     * @param Country 国家
     */

    public void setCountry(String Country) {
        this.Country = Country;
    }

    /**
     * 设置民族
     * 
     * @param Nation 民族
     */

    public void setNation(String Nation) {
        this.Nation = Nation;
    }

    /**
     * 设置籍贯
     * 
     * @param Nativeplace 籍贯
     */

    public void setNativeplace(String Nativeplace) {
        this.Nativeplace = Nativeplace;
    }

    /**
     * 设置身份标识
     * 
     * @param Identifieation 身份标识
     */

    public void setIdentifieation(String Identifieation) {
        this.Identifieation = Identifieation;
    }

    /**
     * 设置地址
     * 
     * @param address 地址
     */

    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * 设置联系地址
     * 
     * @param ContactAddress 联系地址
     */

    public void setContactAddress(String ContactAddress) {
        this.ContactAddress = ContactAddress;
    }

    /**
     * 设置教育信息
     * 
     * @param Education 教育信息
     */

    public void setEducation(String Education) {
        this.Education = Education;
    }

    /**
     * 设置培训信息
     * 
     * @param Training 培训信息
     */

    public void setTraining(String Training) {
        this.Training = Training;
    }

}
