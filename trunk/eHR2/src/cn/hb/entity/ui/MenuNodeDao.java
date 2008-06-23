package cn.hb.dao.ui;

import org.springframework.stereotype.Component;
import cn.hb.core.dao.impl.HibernateDaoImpl;
import cn.hb.entity.ui.MenuNode;

/**
 * 菜单树结点Dao
 */
@Component("menuNodeDao")
public class MenuNodeDao extends HibernateDaoImpl<MenuNode> {
}
