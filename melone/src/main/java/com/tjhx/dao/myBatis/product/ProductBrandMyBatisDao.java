package com.tjhx.dao.myBatis.product;

import java.util.List;

import com.tjhx.entity.product.ProductBrand;

public interface ProductBrandMyBatisDao {

	public List<ProductBrand> getProductBrandList(ProductBrand productBrand);

}
