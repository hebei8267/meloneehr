package com.tjhx.entity.sell;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.tjhx.entity.IdEntity;

/**
 * 购物单-子项
 */
@Entity
@Table(name = "T_ORDER_ITEM")
// 默认的缓存策略.
// @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class OrderItem extends IdEntity {

	private static final long serialVersionUID = -232667289821344511L;

}
