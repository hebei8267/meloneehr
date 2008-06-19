package cn.hb.services.ui.impl;

import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import cn.hb.dao.ui.MenuNodeDao;
import cn.hb.entity.ui.MenuNode;
import cn.hb.services.ui.IMenuTreeService;
import cn.hb.view.domain.ui.UIMenuTreeNodeBean;
import static cn.hb.constant.Constant.DEFAULT_ID;

@Component("menuTreeService")
@Scope("prototype")
public class MenuTreeServiceImpl implements IMenuTreeService {
    // ---------------------------------------------------------------------------
    // 接口实现
    // ---------------------------------------------------------------------------
    @Override
    public UIMenuTreeNodeBean getMenuTreeRootNode_Service(String userID) {
        // TODO 权限没有校验
        MenuNode node = menuNodeDao.getMenuNodeByID(DEFAULT_ID);
        if (node != null) {
            UIMenuTreeNodeBean rootNode = new UIMenuTreeNodeBean(node.getId(), node.getNodeTxt(), node.getNodeType(),
                    node.getActionContent());

            buildSubMenuTree(rootNode, node.getSubNodeList());

            return rootNode;
        }
        return null;
    }

    private void buildSubMenuTree(UIMenuTreeNodeBean parentNode, List<MenuNode> subNodeList) {
        // TODO 权限没有校验
        if (subNodeList != null && !subNodeList.isEmpty()) {
            for (MenuNode node : subNodeList) {
                if (node != null) {
                    UIMenuTreeNodeBean treeNode = new UIMenuTreeNodeBean(node.getId(), node.getNodeTxt(), node
                            .getNodeType(), node.getActionContent());
                    treeNode.setParent(parentNode);

                    parentNode.addChild(node.getId(), treeNode);

                    buildSubMenuTree(treeNode, node.getSubNodeList());
                }
            }
        }

    }

    // ---------------------------------------------------------------------------
    // DAO
    // ---------------------------------------------------------------------------
    private MenuNodeDao menuNodeDao = null;

    public MenuNodeDao getMenuNodeDao() {
        return menuNodeDao;
    }

    public void setMenuNodeDao(MenuNodeDao menuNodeDao) {
        this.menuNodeDao = menuNodeDao;
    }
}
