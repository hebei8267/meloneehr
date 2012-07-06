package com.tjhx.entity.sell;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.tjhx.entity.IdEntity;

/**
 * 购物单
 */
@Entity
@Table(name = "T_ORDER")
// 默认的缓存策略.
// @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Order extends IdEntity {

	private static final long serialVersionUID = 3814446669299310703L;

}
