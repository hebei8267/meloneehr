/*
 * Copyright 2008 by hebei, All rights reserved.
 */
package org.freedom.services.privilege;

import org.freedom.core.test.BaseTestCase;
import org.freedom.services.permit.IMenuNodeService;
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

    // @Test
    // public void addMenuNodeCase1() throws Exception {
    // MenuNode node = new MenuNode();
    // node.setNodeTxt("nodeTxt");
    // node.setNodeType(MenuNodeType.AREA_NODE_TYPE);
    // node.setActionContent("actionContent1");
    //
    // int result = menuNodeService.addMenuNodeInfoService(node);
    // assertEquals(true, 0);
    // }
    //
    // @Test
    // public void err_AddMenuNodeCase2() throws Exception {
    // MenuNode node = new MenuNode();
    // node.setNodeTxt("nodeTxt1");
    // node.setNodeType(MenuNodeType.ROOT_NODE_TYPE);
    // node.setActionContent("actionContent1");
    //
    // int result = menuNodeService.addMenuNodeInfoService(node);
    // assertEquals(false, 1);
    // }
    //
    // @Test
    // public void err_AddMenuNodeCase3() throws Exception {
    // MenuNode node = new MenuNode();
    // node.setNodeTxt("nodeTxt1");
    // node.setNodeType(MenuNodeType.FOLDER_NODE_TYPE);
    // node.setActionContent("actionContent1");
    //
    // boolean result = menuNodeService.addMenuNodeInfoService(node);
    // assertEquals(false, 1);
    // }
    //
    // @Test
    // public void err_AddMenuNodeCase4() throws Exception {
    // MenuNode node = new MenuNode();
    // node.setNodeTxt("nodeTxt1");
    // node.setNodeType(MenuNodeType.LEAF_NODE_TYPE);
    // node.setActionContent("actionContent1");
    //
    // boolean result = menuNodeService.addMenuNodeInfoService(node);
    // assertEquals(false, 1);
    // }
    //
    // @Test
    // public void addMenuNodeCase5() throws Exception {
    // MenuNode node = new MenuNode();
    // node.setNodeTxt("nodeTxt1");
    // node.setNodeType(MenuNodeType.FOLDER_NODE_TYPE);
    // node.setActionContent("actionContent2");
    // boolean result = menuNodeService.addMenuNodeInfoService(node,
    // "00000011");
    // assertEquals(true, 0);
    // }
    //
    // @Test
    // public void addMenuNodeCase6() throws Exception {
    // MenuNode node = new MenuNode();
    // node.setNodeTxt("nodeTxt2");
    // node.setNodeType(MenuNodeType.LEAF_NODE_TYPE);
    // node.setActionContent("actionContent3");
    // boolean result = menuNodeService.addMenuNodeInfoService(node,
    // "00000011");
    // assertEquals(true, 0);
    // }
    //
    // @Test
    // public void addMenuNodeCase9() throws Exception {
    // MenuNode node = new MenuNode();
    // node.setNodeTxt("nodeTxt3");
    // node.setNodeType(MenuNodeType.LEAF_NODE_TYPE);
    // node.setActionContent("actionContent4");
    // node.setIndex(1);
    // boolean result = menuNodeService.addMenuNodeInfoService(node,
    // "00000011");
    // assertEquals(true, 0);
    // }
    //
    // @Test
    // public void addMenuNodeCase10() throws Exception {
    // MenuNode node = new MenuNode();
    // node.setNodeTxt("nodeTxt4");
    // node.setNodeType(MenuNodeType.LEAF_NODE_TYPE);
    // node.setActionContent("actionContent5");
    // node.setIndex(2);
    // boolean result = menuNodeService.addMenuNodeInfoService(node,
    // "00000011");
    // assertEquals(true, 0);
    // }
    //
    // @Test
    // public void addMenuNodeCase11() throws Exception {
    // MenuNode node = new MenuNode();
    // node.setNodeTxt("nodeTxt5");
    // node.setNodeType(MenuNodeType.LEAF_NODE_TYPE);
    // node.setActionContent("actionContent6");
    // node.setIndex(4);
    // boolean result = menuNodeService.addMenuNodeInfoService(node,
    // "00000011");
    // assertEquals(true, 0);
    // }
    //
    // @Test
    // public void addMenuNodeCase12() throws Exception {
    // MenuNode node = new MenuNode();
    // node.setNodeTxt("nodeTxt6");
    // node.setNodeType(MenuNodeType.LEAF_NODE_TYPE);
    // node.setActionContent("actionContent7");
    // node.setIndex(50);
    // boolean result = menuNodeService.addMenuNodeInfoService(node,
    // "00000011");
    // assertEquals(true, 0);
    // }
    //
    // @Test
    // public void err_AddMenuNodeCase7() throws Exception {
    // MenuNode node = new MenuNode();
    // node.setNodeTxt("nodeTxt2");
    // node.setNodeType(MenuNodeType.AREA_NODE_TYPE);
    // node.setActionContent("actionContent2");
    // boolean result = menuNodeService.addMenuNodeInfoService(node,
    // "00000011");
    // assertEquals(false, 1);
    // }
    //
    // @Test
    // public void err_AddMenuNodeCase8() throws Exception {
    // MenuNode node = new MenuNode();
    // node.setNodeTxt("nodeTxt2");
    // node.setNodeType(MenuNodeType.ROOT_NODE_TYPE);
    // node.setActionContent("actionContent2");
    // int result = menuNodeService.addMenuNodeInfoService(node, "00000011");
    // assertEquals(false, 1);
    // }
    //
    // @Test
    // public void addMenuNodeCase13() throws Exception {
    // MenuNode node = new MenuNode();
    // node.setNodeTxt("xxxxxx");
    // node.setNodeType(MenuNodeType.LEAF_NODE_TYPE);
    // node.setActionContent("xxxxxxx");
    // node.setIndex(3);
    // int result = menuNodeService.addMenuNodeInfoService(node, "00000011");
    // assertEquals(true, 0);
    // }
}
