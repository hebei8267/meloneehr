package com.tjhx.web.accounts;

import java.lang.reflect.InvocationTargetException;
import java.text.ParseException;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.tjhx.common.utils.DateUtils;
import com.tjhx.entity.accounts.CardRun;
import com.tjhx.globals.Constants;
import com.tjhx.service.ServiceException;
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
	 * @throws ParseException
	 */
	@RequestMapping(value = { "list", "" })
	public String cardRunList_Action(Model model, HttpSession session) throws ParseException {
		List<CardRun> cardRunList = cardRunManager.getAllCardRunByOrgId_1(getUserInfo(session).getOrganization()
				.getId(), DateUtils.getCurrentDateShortStr());
		model.addAttribute("cardRunList", cardRunList);

		CardRun totalCardRun = cardRunManager.calTotal(cardRunList);
		model.addAttribute("totalCardRun", totalCardRun);

		return "accounts/cardRunList";
	}

	/**
	 * 取得刷卡情况信息列表
	 * 
	 * @param model
	 * @return
	 * @throws ParseException
	 */
	@RequestMapping(value = { "list/{date}" })
	public String cardRunList_Date_Action(@PathVariable("date") String date, Model model, HttpSession session)
			throws ParseException {
		List<CardRun> cardRunList = cardRunManager.getAllCardRunByOrgId_2(getUserInfo(session).getOrganization()
				.getId(), date);
		model.addAttribute("cardRunList", cardRunList);

		CardRun totalCardRun = cardRunManager.calTotal(cardRunList);
		model.addAttribute("totalCardRun", totalCardRun);

		return "accounts/cardRunList";
	}

	/**
	 * 新增刷卡情况初始化
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "new")
	public String initCardRun_Action(Model model) {
		CardRun cardRun = new CardRun();
		model.addAttribute("cardRun", cardRun);

		return "accounts/cardRunForm";
	}

	/**
	 * 编辑刷卡情况信息
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "edit/{id}")
	public String editCardRun_Action(@PathVariable("id") Integer id, Model model) {

		CardRun cardRun = cardRunManager.getCardRunByUuid(id);
		if (null == cardRun) {
			return "redirect:/" + Constants.PAGE_REQUEST_PREFIX + "/cardRun/list";
		} else {
			model.addAttribute("cardRun", cardRun);
			return "accounts/cardRunForm";
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
	public String delCardRun_Action(@RequestParam("uuids") String ids, Model model) {
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
	public String saveCardRun_Action(@ModelAttribute("cardRun") CardRun cardRun, Model model, HttpSession session)
			throws IllegalAccessException, InvocationTargetException {

		if (null == cardRun.getUuid()) {// 新增操作
			try {
				cardRunManager.addNewCardRun(cardRun, getUserInfo(session));
			} catch (ServiceException ex) {
				// 添加错误消息
				addInfoMsg(model, ex.getMessage());
				return "accounts/cardRunForm";
			}
		} else {// 修改操作
			try {
				cardRunManager.updateCardRun(cardRun, getUserInfo(session));
			} catch (ServiceException ex) {
				// 添加错误消息
				addInfoMsg(model, ex.getMessage());
				return "accounts/cardRunForm";
			}
		}
		return "redirect:/" + Constants.PAGE_REQUEST_PREFIX + "/cardRun/list";
	}
}