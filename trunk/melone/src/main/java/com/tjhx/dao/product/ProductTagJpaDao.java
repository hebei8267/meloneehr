package com.tjhx.dao.product;

import org.springframework.data.domain.Sort;
import org.springframework.data.repository.CrudRepository;

import com.tjhx.entity.product.ProductTag;

public interface ProductTagJpaDao extends CrudRepository<ProductTag, Integer> {
	@SuppressWarnings("rawtypes")
	public Iterable findAll(Sort sort);
}
