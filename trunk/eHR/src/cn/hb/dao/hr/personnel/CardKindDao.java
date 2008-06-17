package cn.hb.dao.hr.personnel;

import org.springframework.stereotype.Component;
import cn.hb.core.dao.impl.HibernateDaoImpl;
import cn.hb.entity.hr.personnel.CardKind;

/**
 * 证件类型Dao
 */
@Component("cardKindDao")
public class CardKindDao extends HibernateDaoImpl<CardKind> {
}
