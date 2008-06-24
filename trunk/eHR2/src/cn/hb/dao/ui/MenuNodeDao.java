package cn.hb.dao.ui;

import java.util.List;

import org.springframework.stereotype.Component;
import cn.hb.core.dao.impl.HibernateDaoImpl;
import cn.hb.entity.ui.MenuNode;

/**
 * 菜单树结点Dao
 */
@Component("menuNodeDao")
public class MenuNodeDao extends HibernateDaoImpl<MenuNode> {

    /**
     * 根据菜单树结点ID取得菜单树结点信息
     * 
     * @param nodeID 菜单树结点ID
     * @return 菜单树结点
     */
    @SuppressWarnings("unchecked")
    public MenuNode getMenuNodeByID(String nodeID) {
        List<MenuNode> resultList = getHibernateTemplate().findByNamedQuery("MenuNode.getMenuNodeByID", nodeID);
        if (resultList.size() > 0) {
            return resultList.get(0);
        }
        return null;
    }
}
