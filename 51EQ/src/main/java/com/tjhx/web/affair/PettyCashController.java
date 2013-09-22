package com.tjhx.web.affair;

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

import com.tjhx.entity.affair.PettyCash;
import com.tjhx.globals.Constants;
import com.tjhx.service.ServiceException;
import com.tjhx.service.affair.PettyCashManager;
import com.tjhx.web.BaseController;

@Controller
@RequestMapping(value = "/pettyCash")
public class PettyCashController extends BaseController {
	@Resource
	private PettyCashManager pettyCashManager;

	/**
	 * 取得门店备用金列表
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = { "list", "" })
	public String pettyCashList_Action(Model model, HttpSession session) {
		List<PettyCash> pettyCashList = pettyCashManager.getPettyCashListByOrgId(getUserInfo(session).getOrganization()
				.getId());
		model.addAttribute("pettyCashList", pettyCashList);

		return "affair/pettyCashList";
	}

	/**
	 * 编辑门店备用金列表信息
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "edit/{id}")
	public String editPettyCash_Action(@PathVariable("id") Integer id, Model model, HttpSession session) {
		PettyCash pettyCash = pettyCashManager.getPettyCashByUuid(id);
		if (null == pettyCash) {
			return "redirect:/" + Constants.PAGE_REQUEST_PREFIX + "/pettyCash/list";
		} else {
			model.addAttribute("pettyCash", pettyCash);

			if (0 == pettyCash.getOptType()) {// 支出
				return "affair/pettyCashPayForm";
			} else {// 拨入
				// TODO ????
				return "accounts/cashRunForm";
			}
		}
	}

	/**
	 * 新增门店备用金初始化(支付)
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "payNew")
	public String initPettyCash_Action(Model model, HttpSession session) {
		PettyCash pettyCash = new PettyCash();

		pettyCash.setOptAmtShow(null);
		model.addAttribute("pettyCash", pettyCash);

		return "affair/pettyCashPayForm";
	}

	/**
	 * 删除门店备用金信息
	 * 
	 * @param ids
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "del")
	public String delPettyCash_Action(@RequestParam("uuids") String ids, Model model, HttpSession session) {
		String[] idArray = ids.split(",");
		for (int i = 0; i < idArray.length; i++) {
			pettyCashManager.delPettyCashByUuid(Integer.parseInt(idArray[i]));
		}

		return "redirect:/" + Constants.PAGE_REQUEST_PREFIX + "/pettyCash/list";
	}

	/**
	 * 新增/修改 销售流水信息
	 * 
	 * @param pettyCash
	 * @param model
	 * @return
	 * @throws ParseException
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 */
	@RequestMapping(value = "save")
	public String savePettyCash_Action(@ModelAttribute("pettyCash") PettyCash pettyCash, Model model,
			HttpSession session) throws ParseException {
		// 操作类型0-支出1-拨入
		if (0 == pettyCash.getOptType()) {// 支出
			if (null == pettyCash.getUuid()) {// 新增操作
				try {
					pettyCashManager.addNewPettyCash_Pay(pettyCash, getUserInfo(session));
				} catch (ServiceException ex) {
					// 添加错误消息
					addInfoMsg(model, ex.getMessage());

					return "affair/pettyCashPayForm";
				}
			} else {// 修改操作
				try {
					pettyCashManager.updateNewPettyCash_Pay(pettyCash);
				} catch (ServiceException ex) {
					// 添加错误消息
					addInfoMsg(model, ex.getMessage());

					return "redirect:/" + Constants.PAGE_REQUEST_PREFIX + "/pettyCash/list";
				}
			}
		} else {// 拨入

		}

		return "redirect:/" + Constants.PAGE_REQUEST_PREFIX + "/pettyCash/list";
	}
}
