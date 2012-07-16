package com.tjhx.dao.product;

import org.springframework.data.repository.CrudRepository;

import com.tjhx.entity.product.ProductType;

public interface ProductTypeJpaDao extends CrudRepository<ProductType, Integer> {

}
