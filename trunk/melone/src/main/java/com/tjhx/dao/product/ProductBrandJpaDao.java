package com.tjhx.dao.product;

import org.springframework.data.repository.CrudRepository;

import com.tjhx.entity.product.ProductBrand;

public interface ProductBrandJpaDao extends CrudRepository<ProductBrand, Integer> {

}
