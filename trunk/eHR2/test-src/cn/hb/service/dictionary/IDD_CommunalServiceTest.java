package cn.hb.service.dictionary;

import cn.hb.core.test.service.ServiceTestCase;
import cn.hb.entity.dictionary.communal.Country;

public class IDD_CommunalServiceTest extends ServiceTestCase {

    public void test_AddCountryInfo_Service() {
        Country c = new Country();
        c.setName("阿富汗");
        c.setShortName("AF");
        c.setNote("Afghanistan");

        int result = DD_CommunalService.addCountryInfo_Service(c);

        assertEquals(1, result);
    }

    private IDD_CommunalService DD_CommunalService;

    public IDD_CommunalService getDD_CommunalService() {
        return DD_CommunalService;
    }

    public void setDD_CommunalService(IDD_CommunalService communalService) {
        DD_CommunalService = communalService;
    }

}
