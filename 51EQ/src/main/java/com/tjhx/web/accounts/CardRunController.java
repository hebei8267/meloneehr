package com.tjhx.web.accounts;

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

import com.tjhx.entity.accounts.CardRun;
import com.tjhx.globals.Constants;
import com.tjhx.service.ServiceException;
import com.tjhx.service.accounts.CardRunManager;
import com.tjhx.service.accounts.CardRunManager;
import com.tjhx.web.BaseController;

@Controller
@RequestMapping(value = "/cardRun")
public class CardRunController extends BaseController {
	@Resource
	private CardRunManager cardRunManager;

	/**
	 * 取得刷卡情况信息列表
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = { "list", "" })
	public String cardRunList_Action(Model model, HttpServletRequest request) {
		List<CardRun> cardRunList = cardRunManager.getAllCardRun();

		model.addAttribute("cardRunList", cardRunList);

		return "accounts/cardRunList";
	}

	/**
	 * 新增刷卡情况初始化
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "new")
	public String initCardRun_Action(Model model, HttpServletRequest request) {
		return "accounts/cardRunForm";
	}

	/**
	 * 编辑刷卡情况信息
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "edit/{id}")
	public String editCardRun_Action(@PathVariable("id") Integer id, Model model, HttpServletRequest request) {

		CardRun cardRun = cardRunManager.getCardRunByUuid(id);
		if (null == cardRun) {
			return "redirect:/" + Constants.PAGE_REQUEST_PREFIX + "/cardRun/list";
		} else {
			model.addAttribute("cardRun", cardRun);
			return "cardRunForm";
		}

	}

	/**
	 * 删除刷卡情况信息
	 * 
	 * @param ids
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "del")
	public String delCardRun_Action(@RequestParam("uuids") String ids, Model model, HttpServletRequest request) {
		String[] idArray = ids.split(",");
		for (int i = 0; i < idArray.length; i++) {
			cardRunManager.delCardRunByUuid(Integer.parseInt(idArray[i]));
		}

		return "redirect:/" + Constants.PAGE_REQUEST_PREFIX + "/cardRun/list";
	}

	/**
	 * 新增/修改 刷卡情况信息
	 * 
	 * @param cardRun
	 * @param model
	 * @return
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 */
	@RequestMapping(value = "save")
	public String saveCardRun_Action(@ModelAttribute("cardRun") CardRun cardRun, Model model,
			HttpServletRequest request) throws IllegalAccessException, InvocationTargetException {

		if (null == cardRun.getUuid()) {// 新增操作
			cardRunManager.addNewCardRun(cardRun);
		} else {// 修改操作
			try {
				cardRunManager.updateCardRun(cardRun);
			} catch (ServiceException ex) {
				// 添加错误消息
				addInfoMsg(model, ex.getMessage());
			}
		}

		return "redirect:/" + Constants.PAGE_REQUEST_PREFIX + "/cardRun/list";
	}
}