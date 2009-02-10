/*
 * Copyright 2009 by hebei, All rights reserved.
 */
package org.freedom.services.dictionary;

import org.freedom.core.test.BaseTestCase;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author 何贝
 * @since JDK1.5
 */
public class CountryServiceTest extends BaseTestCase {
    @Autowired
    private ICountryService countryService;

    @Test
    public void err_DelCountryInfoCase1() throws Exception {
        int result = countryService.delCountryInfoService("US", 2);
        System.out.println(result);
    }
}
