package com.tjhx.dao.jpa.product;

import org.springframework.data.domain.Sort;
import org.springframework.data.repository.CrudRepository;

import com.tjhx.entity.product.ProductType;

//Repository<T, ID>----可能暴露了你不希望暴露给业务层的方法。比如某些接口你只希望提供增加的操作而不希望提供删除的方法
//CrudRepository<T, ID>----自动为域对象创建增删改查方法
//PagingAndSortingRepository<T, ID>----在 CrudRepository 基础上新增了两个与分页有关的方法
//JpaRepository<T, ID>----JpaRepository 是继承自 PagingAndSortingRepository 的针对 JPA 技术提供的接口，它在父接口的基础上，提供了其他一些方法，比如 flush()，saveAndFlush()，deleteInBatch() 等

public interface ProductTypeJpaDao extends CrudRepository<ProductType, Integer> {
	@SuppressWarnings("rawtypes")
	public Iterable findAll(Sort sort);
}
