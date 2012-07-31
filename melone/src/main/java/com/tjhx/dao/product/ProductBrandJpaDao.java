package com.tjhx.dao.product;

import org.springframework.data.domain.Sort;
import org.springframework.data.repository.CrudRepository;

import com.tjhx.entity.product.ProductBrand;

public interface ProductBrandJpaDao extends CrudRepository<ProductBrand, Integer> {
	@SuppressWarnings("rawtypes")
	public Iterable findAll(Sort sort);
}
