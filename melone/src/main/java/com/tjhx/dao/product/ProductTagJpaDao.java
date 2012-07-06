package com.tjhx.dao.product;

import org.springframework.data.repository.CrudRepository;

import com.tjhx.entity.product.ProductTag;

public interface ProductTagJpaDao extends CrudRepository<ProductTag, Integer> {

}
