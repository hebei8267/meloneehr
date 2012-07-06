package com.tjhx.dao.sell;

import org.springframework.data.repository.CrudRepository;

import com.tjhx.entity.sell.Order;

public interface OrderJpaDao extends CrudRepository<Order, Integer> {

}
