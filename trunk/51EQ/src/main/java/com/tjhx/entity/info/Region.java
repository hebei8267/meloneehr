package com.tjhx.entity.info;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.tjhx.entity.IdEntity;

/**
 * 区域
 */
@Entity
@Table(name = "T_REGION")
// 默认的缓存策略.
// @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Region extends IdEntity {

	private static final long serialVersionUID = -3761908507234693948L;

}
