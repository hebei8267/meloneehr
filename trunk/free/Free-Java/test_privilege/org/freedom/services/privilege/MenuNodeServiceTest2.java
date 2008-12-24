/*
 * Copyright 2008 by hebei, All rights reserved.
 */
package org.freedom.services.privilege;

import org.freedom.core.test.BaseTestCase;
import org.freedom.entity.ui.MenuNode;
import org.freedom.services.permit.IMenuNodeService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author 何贝
 * @since JDK1.5
 */
public class MenuNodeServiceTest2 extends BaseTestCase {
    @Autowired
    private IMenuNodeService menuNodeService = null;

    public IMenuNodeService getMenuNodeService() {
        return menuNodeService;
    }

    public void setMenuNodeService(IMenuNodeService menuNodeService) {
        this.menuNodeService = menuNodeService;
    }

    @Test
    public void modMenuNodeCase1() throws Exception {
        MenuNode node = new MenuNode();

        node.setId("00000016");
        node.setNodeTxt("mmmmmmmm");
        node.setActionContent("mmmmmmmm");
        node.setIndex(1);

        boolean result = menuNodeService.modMenuNodeInfoService(node);
        assertEquals(true, result);
    }

}
