package com.tjhx.web.product;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.tjhx.entity.product.ProductBrand;
import com.tjhx.globals.Constants;
import com.tjhx.service.ServiceException;
import com.tjhx.service.product.ProductBrandManager;
import com.tjhx.web.BaseController;

@Controller
@RequestMapping(value = "/product/productBrand")
public class ProductBrandController extends BaseController {
	@Resource
	private ProductBrandManager productBrandManager;

	/**
	 * 取得商品品牌信息列表
	 * 
	 * @param model
	 * @return
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 */
	@RequestMapping(value = { "list", "" })
	public String productBrandList_Action(Model model, HttpServletRequest request) throws IllegalAccessException,
			InvocationTargetException {

		ProductBrand productBrand = new ProductBrand();
		// 取得Request中的查询参数
		BeanUtils.populate(productBrand, request.getParameterMap());
		List<ProductBrand> productBrandList = productBrandManager.getProductBrandList(productBrand);

		model.addAttribute("productBrand", productBrand);
		model.addAttribute("productBrandList", productBrandList);

		return "product/productBrandList";
	}

	/**
	 * 编辑商品品牌信息
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "edit/{id}")
	public String editProductBrand_Action(@PathVariable("id") Integer id, Model model, HttpServletRequest request) {

		ProductBrand productBrand = productBrandManager.getProductBrandByUuid(id);
		if (null == productBrand) {
			return "redirect:/" + Constants.PAGE_REQUEST_PREFIX + "/product/productBrand/list";
		} else {
			model.addAttribute("productBrand", productBrand);
			return "product/productBrandForm";
		}

	}

	/**
	 * 删除商品品牌信息
	 * 
	 * @param ids
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "del")
	public String delProductBrand_Action(@RequestParam("uuids") String ids, Model model, HttpServletRequest request) {
		String[] idArray = ids.split(",");
		for (int i = 0; i < idArray.length; i++) {
			productBrandManager.delProductBrandByUuid(Integer.parseInt(idArray[i]));
		}

		return "redirect:/" + Constants.PAGE_REQUEST_PREFIX + "/product/productBrand/list";
	}

	/**
	 * 新增商品品牌初始化
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "new")
	public String initProductBrand_Action(Model model, HttpServletRequest request) {

		ProductBrand productBrand = new ProductBrand();
		model.addAttribute("productBrand", productBrand);

		return "product/productBrandForm";
	}

	/**
	 * 新增/修改 商品品牌信息
	 * 
	 * @param productBrand
	 * @param model
	 * @return
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 */
	@RequestMapping(value = "save")
	public String saveProductBrand_Action(@ModelAttribute("productBrand") ProductBrand productBrand, Model model,
			HttpServletRequest request) throws IllegalAccessException, InvocationTargetException {

		if (null == productBrand.getUuid()) {// 新增操作
			productBrandManager.addNewProductBrand(productBrand);
		} else {// 修改操作
			try {
				productBrandManager.updateProductBrand(productBrand);
			} catch (ServiceException ex) {
				// 添加错误消息
				addInfoMsg(model, ex.getMessage());
			}
		}

		return "redirect:/" + Constants.PAGE_REQUEST_PREFIX + "/product/productBrand/list";
	}
}