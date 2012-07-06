package com.tjhx.dao.sell;

import org.springframework.data.repository.CrudRepository;

import com.tjhx.entity.sell.OrderItem;

public interface OrderItemJpaDao extends CrudRepository<OrderItem, Integer> {

}
