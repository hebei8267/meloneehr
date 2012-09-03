package com.tjhx.dao.myBatis.product;

import java.util.List;

import com.tjhx.entity.product.Product;

public interface ProductMyBatisDao {

	public List<Product> getProductList(Product product);

}
