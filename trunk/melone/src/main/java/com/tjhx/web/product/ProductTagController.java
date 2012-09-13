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

import com.tjhx.entity.product.ProductTag;
import com.tjhx.globals.Constants;
import com.tjhx.service.ServiceException;
import com.tjhx.service.product.ProductTagManager;
import com.tjhx.web.BaseController;

@Controller
@RequestMapping(value = "/product/productTag")
public class ProductTagController extends BaseController {
	@Resource
	private ProductTagManager productTagManager;

	/**
	 * 取得商品标签信息列表
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = { "list", "" })
	public String productTagList_Action(Model model, HttpServletRequest request) {

		List<ProductTag> productTagList = productTagManager.getAllProductTag();

		model.addAttribute("productTagList", productTagList);

		return "product/productTagList";
	}

	/**
	 * 编辑商品标签信息
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "edit/{id}")
	public String editProductTag_Action(@PathVariable("id") Integer id, Model model, HttpServletRequest request) {

		ProductTag productTag = productTagManager.getProductTagByUuid(id);
		if (null == productTag) {
			return "redirect:/" + Constants.PAGE_REQUEST_PREFIX + "/product/productTag/list";
		} else {
			model.addAttribute("productTag", productTag);
			return "product/productTagForm";
		}

	}

	/**
	 * 删除商品标签信息
	 * 
	 * @param ids
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "del")
	public String delProductTag_Action(@RequestParam("uuids") String ids, Model model, HttpServletRequest request) {
		String[] idArray = ids.split(",");
		for (int i = 0; i < idArray.length; i++) {
			productTagManager.delProductTagByUuid(Integer.parseInt(idArray[i]));
		}

		return "redirect:/" + Constants.PAGE_REQUEST_PREFIX + "/product/productTag/list";
	}

	/**
	 * 新增商品标签初始化
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "new")
	public String initProductTag_Action(Model model, HttpServletRequest request) {

		ProductTag productTag = new ProductTag();
		model.addAttribute("productTag", productTag);

		return "product/productTagForm";
	}

	/**
	 * 新增/修改 商品标签信息
	 * 
	 * @param productTag
	 * @param model
	 * @return
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 */
	@RequestMapping(value = "save")
	public String saveProductTag_Action(@ModelAttribute("productTag") ProductTag productTag, Model model,
			HttpServletRequest request) throws IllegalAccessException, InvocationTargetException {

		if (null == productTag.getUuid()) {// 新增操作
			productTagManager.addNewProductTag(productTag);
		} else {// 修改操作
			try {
				productTagManager.updateProductTag(productTag);
			} catch (ServiceException ex) {
				// 添加错误消息
				addInfoMsg(model, ex.getMessage());
			}
		}

		return "redirect:/" + Constants.PAGE_REQUEST_PREFIX + "/product/productTag/list";
	}
}