package com.tjhx.dao.shop;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.tjhx.entity.shop.Shop;

public interface ShopJpaDao extends PagingAndSortingRepository<Shop, Integer> {

}
