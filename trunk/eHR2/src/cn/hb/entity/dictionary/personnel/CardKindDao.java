package cn.hb.dao.dictionary.personnel;

import org.springframework.stereotype.Component;
import cn.hb.core.dao.impl.HibernateDaoImpl;
import cn.hb.entity.dictionary.personnel.CardKind;

/**
 * 证件类型Dao
 */
@Component("cardKindDao")
public class CardKindDao extends HibernateDaoImpl<CardKind> {
}
