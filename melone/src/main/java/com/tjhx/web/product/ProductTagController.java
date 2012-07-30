package com.tjhx.web.product;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
	@Autowired
	private ProductTagManager productTagManager;

	/**
	 * 取得产品标签信息列表
	 * 
	 * @param model
	 * @return
	 */
	//TODO ?????????????
	@RequestMapping(value = { "list", "" })
	public String productTagList_Action(Model model,
			@RequestParam(value = "__SESSION_ERR_MSG_LIST", required = false) String msg) {
	
		String str=null;
		try {if(null!=msg){
			str = new String(msg.getBytes("ISO8859_1"),"UTF-8");
		}
		
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("######################=" + msg);
		System.out.println("######################=" + str);
		List<ProductTag> productTagList = productTagManager.getAllProductTag();

		model.addAttribute("productTagList", productTagList);

		return "product/productTagList";
	}

	/**
	 * 编辑产品标签信息
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "edit/{id}")
	public String editProductTag_Action(@PathVariable("id") Integer id, Model model) {

		ProductTag productTag = productTagManager.getProductTagByUuid(id);
		if (null == productTag) {
			return "redirect:/" + Constants.PAGE_REQUEST_PREFIX + "/product/productTag/list";
		} else {
			model.addAttribute("productTag", productTag);
			return "product/productTagForm";
		}

	}

	/**
	 * 删除产品标签信息
	 * 
	 * @param ids
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "del")
	public String delProductTag_Action(@RequestParam("uuids") String ids, Model model) {
		String[] idArray = ids.split(",");
		for (int i = 0; i < idArray.length; i++) {
			productTagManager.delProductTagByUuid(Integer.parseInt(idArray[i]));
		}

		return "redirect:/" + Constants.PAGE_REQUEST_PREFIX + "/product/productTag/list";
	}

	/**
	 * 新增产品标签初始化
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "new")
	public String initProductTag_Action(Model model) {

		ProductTag productTag = new ProductTag();
		model.addAttribute("productTag", productTag);

		return "product/productTagForm";
	}

	/**
	 * 新增/修改 产品标签信息
	 * 
	 * @param productTag
	 * @param model
	 * @return
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 */
	@RequestMapping(value = "save")
	public String saveProductTag_Action(@ModelAttribute("productTag") ProductTag productTag, Model model)
			throws IllegalAccessException, InvocationTargetException {

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