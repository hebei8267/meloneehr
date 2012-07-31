package com.tjhx.web.syscfg;

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

import com.tjhx.entity.shop.Shop;
import com.tjhx.entity.shop.Store;
import com.tjhx.globals.Constants;
import com.tjhx.service.ServiceException;
import com.tjhx.service.shop.ShopManager;
import com.tjhx.service.shop.StoreManager;
import com.tjhx.web.BaseController;

@Controller
@RequestMapping(value = "/syscfg/shop")
public class ShopController extends BaseController {
	@Autowired
	private ShopManager shopManager;
	@Autowired
	private StoreManager storeManager;

	/**
	 * 取得门店信息列表
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = { "list", "" })
	public String shopList_Action(Model model, HttpServletRequest request) {
		List<Shop> shopList = shopManager.getAllShop();

		model.addAttribute("shopList", shopList);

		return "syscfg/shopList";
	}

	/**
	 * 编辑门店信息
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "edit/{id}")
	public String editShop_Action(@PathVariable("id") Integer id, Model model, HttpServletRequest request) {

		Shop shop = shopManager.getShopByUuid(id);
		if (null == shop) {
			return "redirect:/" + Constants.PAGE_REQUEST_PREFIX + "/syscfg/shop/list";
		} else {
			model.addAttribute("shop", shop);

			// 初始化仓库信息下拉菜单
			initStoreSelect(model);

			return "syscfg/shopForm";
		}

	}

	/**
	 * 删除门店信息
	 * 
	 * @param ids
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "del")
	public String delShop_Action(@RequestParam("uuids") String ids, Model model, HttpServletRequest request) {
		String[] idArray = ids.split(",");
		for (int i = 0; i < idArray.length; i++) {
			shopManager.delShopByUuid(Integer.parseInt(idArray[i]));
		}

		return "redirect:/" + Constants.PAGE_REQUEST_PREFIX + "/syscfg/shop/list";
	}

	/**
	 * 新增门店初始化
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "new")
	public String initShop_Action(Model model, HttpServletRequest request) {

		Shop shop = new Shop();
		model.addAttribute("shop", shop);

		// 初始化仓库信息下拉菜单
		initStoreSelect(model);

		return "syscfg/shopForm";
	}

	/**
	 * 初始化仓库信息下拉菜单
	 * 
	 * @param model
	 */
	private void initStoreSelect(Model model) {
		List<Store> storeList = storeManager.getAllStore();

		model.addAttribute("storeList", storeList);
	}

	/**
	 * 新增/修改 门店信息
	 * 
	 * @param shop 门店
	 * @param model
	 * @return
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 */
	@RequestMapping(value = "save")
	public String saveShop_Action(@ModelAttribute("shop") Shop shop, Model model, HttpServletRequest request)
			throws IllegalAccessException, InvocationTargetException {

		if (null == shop.getUuid()) {// 新增操作
			try {
				shopManager.addNewShop(shop);
			} catch (ServiceException ex) {
				// 添加错误消息
				addInfoMsg(model, ex.getMessage());

				// 新增门店初始化-清空重复门店编号
				shop.setId(null);
				model.addAttribute("shop", shop);

				// 初始化仓库信息下拉菜单
				initStoreSelect(model);

				return "syscfg/shopForm";
			}
		} else {// 修改操作
			try {
				shopManager.updateShop(shop);
			} catch (ServiceException ex) {
				// 添加错误消息
				addInfoMsg(model, ex.getMessage());
			}
		}

		return "redirect:/" + Constants.PAGE_REQUEST_PREFIX + "/syscfg/shop/list";
	}
}