/*
 * Copyright 2008 by hebei, All rights reserved.
 */
package org.freedom.services.privilege;

import org.freedom.core.test.BaseTestCase;
import org.freedom.entity.ui.MenuNode;
import org.freedom.entity.ui.MenuNodeType;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author 何贝
 * @since JDK1.5
 */
public class MenuNodeServiceTest extends BaseTestCase {
    @Autowired
    private IMenuNodeService menuNodeService = null;

    public IMenuNodeService getMenuNodeService() {
        return menuNodeService;
    }

    public void setMenuNodeService(IMenuNodeService menuNodeService) {
        this.menuNodeService = menuNodeService;
    }

    @Test
    public void addMenuNodeCase1() throws Exception {
        MenuNode node = new MenuNode();
        node.setNodeTxt("nodeTxt1");
        node.setNodeType(MenuNodeType.AREA_NODE_TYPE);
        node.setActionContent("actionContent1");

        boolean result = menuNodeService.addMenuNodeInfoService(node);
        assertEquals(true, result);
    }

    @Test
    public void err_AddMenuNodeCase2() throws Exception {
        MenuNode node = new MenuNode();
        node.setNodeTxt("nodeTxt1");
        node.setNodeType(MenuNodeType.ROOT_NODE_TYPE);
        node.setActionContent("actionContent1");

        boolean result = menuNodeService.addMenuNodeInfoService(node);
        assertEquals(false, result);
    }

    @Test
    public void err_AddMenuNodeCase3() throws Exception {
        MenuNode node = new MenuNode();
        node.setNodeTxt("nodeTxt1");
        node.setNodeType(MenuNodeType.FOLDER_NODE_TYPE);
        node.setActionContent("actionContent1");

        boolean result = menuNodeService.addMenuNodeInfoService(node);
        assertEquals(false, result);
    }

    @Test
    public void err_AddMenuNodeCase4() throws Exception {
        MenuNode node = new MenuNode();
        node.setNodeTxt("nodeTxt1");
        node.setNodeType(MenuNodeType.LEAF_NODE_TYPE);
        node.setActionContent("actionContent1");

        boolean result = menuNodeService.addMenuNodeInfoService(node);
        assertEquals(false, result);
    }

    @Test
    public void addMenuNodeCase5() throws Exception {
        MenuNode node = new MenuNode();
        node.setNodeTxt("nodeTxt2");
        node.setNodeType(MenuNodeType.FOLDER_NODE_TYPE);
        node.setActionContent("actionContent2");
        boolean result = menuNodeService.addMenuNodeInfoService(node, "00000011");
        assertEquals(true, result);
    }

    @Test
    public void addMenuNodeCase6() throws Exception {
        MenuNode node = new MenuNode();
        node.setNodeTxt("nodeTxt3");
        node.setNodeType(MenuNodeType.LEAF_NODE_TYPE);
        node.setActionContent("actionContent3");
        boolean result = menuNodeService.addMenuNodeInfoService(node, "00000011");
        assertEquals(true, result);
    }

    @Test
    public void err_AddMenuNodeCase7() throws Exception {
        MenuNode node = new MenuNode();
        node.setNodeTxt("nodeTxt2");
        node.setNodeType(MenuNodeType.AREA_NODE_TYPE);
        node.setActionContent("actionContent2");
        boolean result = menuNodeService.addMenuNodeInfoService(node, "00000011");
        assertEquals(false, result);
    }

    @Test
    public void err_AddMenuNodeCase8() throws Exception {
        MenuNode node = new MenuNode();
        node.setNodeTxt("nodeTxt2");
        node.setNodeType(MenuNodeType.ROOT_NODE_TYPE);
        node.setActionContent("actionContent2");
        boolean result = menuNodeService.addMenuNodeInfoService(node, "00000011");
        assertEquals(false, result);
    }
}
