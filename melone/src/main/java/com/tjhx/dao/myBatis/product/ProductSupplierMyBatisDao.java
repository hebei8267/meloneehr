package com.tjhx.dao.myBatis.product;

import java.util.List;

import com.tjhx.entity.product.ProductSupplier;

public interface ProductSupplierMyBatisDao {

	public List<ProductSupplier> getProductSupplierList(ProductSupplier productSupplier);

}
