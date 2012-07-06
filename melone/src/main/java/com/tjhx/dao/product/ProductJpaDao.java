package com.tjhx.dao.product;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.tjhx.entity.product.Product;

public interface ProductJpaDao extends PagingAndSortingRepository<Product, Integer> {

}
