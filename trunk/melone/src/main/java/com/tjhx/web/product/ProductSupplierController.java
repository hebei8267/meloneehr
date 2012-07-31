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

import com.tjhx.entity.product.ProductSupplier;
import com.tjhx.globals.Constants;
import com.tjhx.service.ServiceException;
import com.tjhx.service.product.ProductSupplierManager;
import com.tjhx.web.BaseController;

@Controller
@RequestMapping(value = "/product/productSupplier")
public class ProductSupplierController extends BaseController {
	@Autowired
	private ProductSupplierManager productSupplierManager;

	/**
	 * 取得产品供应商信息列表
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = { "list", "" })
	public String productSupplierList_Action(Model model, HttpServletRequest request) {
		List<ProductSupplier> productSupplierList = productSupplierManager.getAllProductSupplier();

		model.addAttribute("productSupplierList", productSupplierList);

		return "product/productSupplierList";
	}

	/**
	 * 编辑产品供应商信息
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "edit/{id}")
	public String editProductSupplier_Action(@PathVariable("id") Integer id, Model model, HttpServletRequest request) {

		ProductSupplier productSupplier = productSupplierManager.getProductSupplierByUuid(id);
		if (null == productSupplier) {
			return "redirect:/" + Constants.PAGE_REQUEST_PREFIX + "/product/productSupplier/list";
		} else {
			model.addAttribute("productSupplier", productSupplier);
			return "product/productSupplierForm";
		}

	}

	/**
	 * 删除产品供应商信息
	 * 
	 * @param ids
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "del")
	public String delProductSupplier_Action(@RequestParam("uuids") String ids, Model model, HttpServletRequest request) {
		String[] idArray = ids.split(",");
		for (int i = 0; i < idArray.length; i++) {
			productSupplierManager.delProductSupplierByUuid(Integer.parseInt(idArray[i]));
		}

		return "redirect:/" + Constants.PAGE_REQUEST_PREFIX + "/product/productSupplier/list";
	}

	/**
	 * 新增产品供应商初始化
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "new")
	public String initProductSupplier_Action(Model model, HttpServletRequest request) {

		ProductSupplier productSupplier = new ProductSupplier();
		model.addAttribute("productSupplier", productSupplier);

		return "product/productSupplierForm";
	}

	/**
	 * 新增/修改 产品供应商信息
	 * 
	 * @param productSupplier
	 * @param model
	 * @return
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 */
	@RequestMapping(value = "save")
	public String saveProductSupplier_Action(@ModelAttribute("productSupplier") ProductSupplier productSupplier,
			Model model, HttpServletRequest request) throws IllegalAccessException, InvocationTargetException {

		if (null == productSupplier.getUuid()) {// 新增操作
			productSupplierManager.addNewProductSupplier(productSupplier);
		} else {// 修改操作
			try {
				productSupplierManager.updateProductSupplier(productSupplier);
			} catch (ServiceException ex) {
				// 添加错误消息
				addInfoMsg(model, ex.getMessage());
			}
		}

		return "redirect:/" + Constants.PAGE_REQUEST_PREFIX + "/product/productSupplier/list";
	}
}