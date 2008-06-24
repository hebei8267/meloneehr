package cn.hb.service.dictionary;

import cn.hb.core.test.service.ServiceTestCase;
import cn.hb.entity.dictionary.communal.Country;
import cn.hb.entity.dictionary.communal.Nation;
import cn.hb.entity.dictionary.communal.Nativeplace;

public class IDD_CommunalServiceTest extends ServiceTestCase {

    /**
     * 正常系
     */
    public void test_AddCountryInfo_Service1() {
        Country obj = new Country();
        obj.setName("阿富汗");
        obj.setShortName("AF");
        obj.setNote("Afghanistan");

        int result = DD_CommunalService.addCountryInfo_Service(obj);

        assertEquals(0, result);
    }

    /**
     * 异常系 【名字】和【简称】不能重复
     */
    public void test_AddCountryInfo_Service2() {
        Country obj = new Country();
        obj.setName("阿富汗");
        obj.setShortName("AF");
        obj.setNote("Afghanistan");

        int result = DD_CommunalService.addCountryInfo_Service(obj);

        assertEquals(1, result);
    }

    /**
     * 正常系
     */
    public void test_AddNationInfo_Service1() {
        Nation obj = new Nation();
        obj.setName("楚汉族");
        obj.setNote("楚国的汉族");

        int result = DD_CommunalService.addNationInfo_Service(obj);

        assertEquals(0, result);
    }

    /**
     * 异常系 【名字】不能重复
     */
    public void test_AddNationInfo_Service2() {
        Nation obj = new Nation();
        obj.setName("楚汉族");
        obj.setNote("楚国的汉族");

        int result = DD_CommunalService.addNationInfo_Service(obj);

        assertEquals(1, result);

    }

    /**
     * 正常系
     */
    public void test_AddNativeplaceInfo_Service1() {
        Nativeplace obj = new Nativeplace();
        obj.setName("武汉市");

        int result = DD_CommunalService.addNativeplaceInfo_Service(obj, "00000002");
        assertEquals(0, result);
    }

    /**
     * 异常系 【名字】不能重复-在同级节点上
     */
    public void test_AddNativeplaceInfo_Service2() {
        Nativeplace obj = new Nativeplace();
        obj.setName("武汉市");

        int result = DD_CommunalService.addNativeplaceInfo_Service(obj, "00000002");
        assertEquals(1, result);
    }

    /**
     * 正常系 【名字】重复-在不同级节点上
     */
    public void test_AddNativeplaceInfo_Service3() {
        Nativeplace obj = new Nativeplace();
        obj.setName("武汉市");

        int result = DD_CommunalService.addNativeplaceInfo_Service(obj, "00000003");
        assertEquals(0, result);
    }

    /**
     * 正常系
     */
    public void test_DelCountryInfo_Service1() {
        int result = DD_CommunalService.delCountryInfo_Service("00000001");

        assertEquals(0, result);
    }

    /**
     * 异常系 该对象不存在
     */
    public void test_DelCountryInfo_Service2() {
        int result = DD_CommunalService.delCountryInfo_Service("00000001");

        assertEquals(1, result);
    }

    /**
     * 正常系
     */
    public void test_DelNationInfo_Service1() {
        int result = DD_CommunalService.delNationInfo_Service("00000001");

        assertEquals(0, result);
    }

    /**
     * 异常系 该对象不存在
     */
    public void test_DelNationInfo_Service2() {
        int result = DD_CommunalService.delNationInfo_Service("00000001");

        assertEquals(1, result);
    }

    /**
     * 正常系
     */
    public void test_DelNativeplaceInfo_Service1() {
        int result = DD_CommunalService.delNativeplaceInfo_Service("00000005", "00000002");

        assertEquals(0, result);
    }

    /**
     * 异常系 该对象不存在
     */
    public void test_DelNativeplaceInfo_Service2() {
        int result = DD_CommunalService.delNativeplaceInfo_Service("00000005", "00000002");

        assertEquals(1, result);
    }

    /**
     * 正常系
     */
    public void test_GetCountryInfoList_Service() {
        int result = DD_CommunalService.getCountryInfoList_Service().size();

        assertEquals(3, result);
    }

    /**
     * 正常系
     */
    public void test_GetNationInfoList_Service() {
        int result = DD_CommunalService.getNationInfoList_Service().size();

        assertEquals(3, result);
    }

    /**
     * 正常系
     */
    public void test_GetNativeplaceInfoList_Service() {
        int result = DD_CommunalService.getNativeplaceInfoList_Service().size();

        assertEquals(12, result);
    }

    private IDD_CommunalService DD_CommunalService;

    public IDD_CommunalService getDD_CommunalService() {
        return DD_CommunalService;
    }

    public void setDD_CommunalService(IDD_CommunalService communalService) {
        DD_CommunalService = communalService;
    }

}
