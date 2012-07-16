package com.tjhx.dao.product;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.tjhx.entity.product.ProductSupplier;

public interface ProductSupplierJpaDao extends PagingAndSortingRepository<ProductSupplier, Integer> {

}
