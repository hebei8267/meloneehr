package com.tjhx.web.product;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.tjhx.entity.product.ProductType;
import com.tjhx.globals.Constants;
import com.tjhx.service.ServiceException;
import com.tjhx.service.product.ProductTypeManager;
import com.tjhx.web.BaseController;

@Controller
@RequestMapping(value = "/product/productType")
public class ProductTypeController extends BaseController {
	@Resource
	private ProductTypeManager productTypeManager;

	/**
	 * 取得ProductType信息列表
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = { "list", "" })
	public String productTypeList_Action(Model model, HttpServletRequest request) {
		List<ProductType> productTypeList = productTypeManager.getAllProductType();

		model.addAttribute("productTypeList", productTypeList);

		return "product/productTypeList";
	}

	/**
	 * 编辑ProductType信息
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "edit/{id}")
	public String editProductType_Action(@PathVariable("id") Integer id, Model model, HttpServletRequest request) {

		ProductType productType = productTypeManager.getProductTypeByUuid(id);
		if (null == productType) {
			return "redirect:/" + Constants.PAGE_REQUEST_PREFIX + "/product/productType/list";
		} else {
			model.addAttribute("productType", productType);
			return "product/productTypeForm";
		}

	}

	/**
	 * 删除ProductType信息
	 * 
	 * @param ids
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "del")
	public String delProductType_Action(@RequestParam("uuids") String ids, Model model, HttpServletRequest request) {
		String[] idArray = ids.split(",");
		for (int i = 0; i < idArray.length; i++) {
			productTypeManager.delProductTypeByUuid(Integer.parseInt(idArray[i]));
		}

		return "redirect:/" + Constants.PAGE_REQUEST_PREFIX + "/product/productType/list";
	}

	/**
	 * 新增ProductType初始化
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "new")
	public String initProductType_Action(Model model, HttpServletRequest request) {

		ProductType productType = new ProductType();
		model.addAttribute("productType", productType);

		return "product/productTypeForm";
	}

	/**
	 * 新增/修改 ProductType信息
	 * 
	 * @param productType
	 * @param model
	 * @return
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 */
	@RequestMapping(value = "save")
	public String saveProductType_Action(@ModelAttribute("productType") ProductType productType, Model model,
			HttpServletRequest request) throws IllegalAccessException, InvocationTargetException {

		if (null == productType.getUuid()) {// 新增操作
			productTypeManager.addNewProductType(productType);
		} else {// 修改操作
			try {
				productTypeManager.updateProductType(productType);
			} catch (ServiceException ex) {
				// 添加错误消息
				addInfoMsg(model, ex.getMessage());
			}
		}

		return "redirect:/" + Constants.PAGE_REQUEST_PREFIX + "/product/productType/list";
	}
}