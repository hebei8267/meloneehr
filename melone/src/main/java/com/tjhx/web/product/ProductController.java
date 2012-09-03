package com.tjhx.web.product;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.tjhx.entity.product.Product;
import com.tjhx.entity.product.ProductBrand;
import com.tjhx.entity.product.ProductSupplier;
import com.tjhx.entity.product.ProductType;
import com.tjhx.globals.Constants;
import com.tjhx.service.ServiceException;
import com.tjhx.service.product.ProductBrandManager;
import com.tjhx.service.product.ProductManager;
import com.tjhx.service.product.ProductSupplierManager;
import com.tjhx.service.product.ProductTagManager;
import com.tjhx.service.product.ProductTypeManager;
import com.tjhx.web.BaseController;

@Controller
@RequestMapping(value = "/product")
public class ProductController extends BaseController {
	@Autowired
	private ProductManager productManager;
	@Autowired
	private ProductBrandManager productBrandManager;
	@Autowired
	private ProductTypeManager productTypeManager;
	@Autowired
	private ProductSupplierManager productSupplierManager;
	@Autowired
	private ProductTagManager productTagManager;

	/**
	 * 取得商品信息列表
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = { "list", "" })
	public String productList_Action(Model model, HttpServletRequest request) {
		List<Product> productList = productManager.getAllProduct();

		model.addAttribute("productList", productList);

		return "product/productList";
	}

	/**
	 * 编辑商品信息
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "edit/{id}")
	public String editProduct_Action(@PathVariable("id") Integer id, Model model, HttpServletRequest request) {

		Product product = productManager.getProductByUuid(id);
		if (null == product) {
			return "redirect:/" + Constants.PAGE_REQUEST_PREFIX + "/product/list";
		} else {
			model.addAttribute("product", product);
			return "product/productForm";
		}

	}

	/**
	 * 删除商品信息
	 * 
	 * @param ids
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "del")
	public String delProduct_Action(@RequestParam("uuids") String ids, Model model, HttpServletRequest request) {
		String[] idArray = ids.split(",");
		for (int i = 0; i < idArray.length; i++) {
			productManager.delProductByUuid(Integer.parseInt(idArray[i]));
		}

		return "redirect:/" + Constants.PAGE_REQUEST_PREFIX + "/product/list";
	}

	/**
	 * 新增商品初始化
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "new")
	public String initProduct_Action(Model model, HttpServletRequest request) {

		Product product = new Product();
		model.addAttribute("product", product);

		// 初始化下拉菜单
		initSelectMenu(model);

		return "product/productForm";
	}

	/**
	 * 初始化下拉菜单
	 * 
	 * @param model
	 */
	private void initSelectMenu(Model model) {
		// 商品类型
		List<ProductType> productTypeList = productTypeManager.getAllProductType();
		model.addAttribute("productTypeList", productTypeList);

		// 商品品牌
		List<ProductBrand> productBrandList = productBrandManager.getAllProductBrand();
		model.addAttribute("productBrandList", productBrandList);

		// 商品供应商
		List<ProductSupplier> productSupplierList = productSupplierManager.getAllProductSupplier();
		model.addAttribute("productSupplierList", productSupplierList);

	}

	/**
	 * 新增/修改 商品信息
	 * 
	 * @param 商品
	 * @param model
	 * @return
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 */
	@RequestMapping(value = "save")
	public String saveProduct_Action(@ModelAttribute("product") Product product, Model model, HttpServletRequest request)
			throws IllegalAccessException, InvocationTargetException {

		if (null == product.getUuid()) {// 新增操作
			productManager.addNewProduct(product);
		} else {// 修改操作
			try {
				productManager.updateProduct(product);
			} catch (ServiceException ex) {
				// 添加错误消息
				addInfoMsg(model, ex.getMessage());
			}
		}

		return "redirect:/" + Constants.PAGE_REQUEST_PREFIX + "/product/list";
	}
}