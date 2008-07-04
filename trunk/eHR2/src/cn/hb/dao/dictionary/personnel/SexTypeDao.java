package cn.hb.dao.dictionary.personnel;

import org.springframework.stereotype.Component;

import cn.hb.core.dao.impl.HibernateDaoImpl;
import cn.hb.entity.dictionary.personnel.SexType;

/**
 * 性别类型Dao
 */
@Component("sexTypeDao")
public class SexTypeDao extends HibernateDaoImpl<SexType> {

}
