package cn.hb.dao.common;

import org.springframework.stereotype.Component;
import cn.hb.core.dao.impl.HibernateDaoImpl;
import cn.hb.entity.common.Nativeplace;

/**
 * 籍贯Dao
 */
@Component("nativeplaceDao")
public class NativeplaceDao extends HibernateDaoImpl<Nativeplace> {
}
