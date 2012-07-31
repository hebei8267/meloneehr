package com.tjhx.dao.product;

import org.springframework.data.domain.Sort;
import org.springframework.data.repository.CrudRepository;

import com.tjhx.entity.product.ProductType;

public interface ProductTypeJpaDao extends CrudRepository<ProductType, Integer> {
	@SuppressWarnings("rawtypes")
	public Iterable findAll(Sort sort);
}
