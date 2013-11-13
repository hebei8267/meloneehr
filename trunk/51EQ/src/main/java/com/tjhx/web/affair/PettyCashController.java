package com.tjhx.web.affair;

import java.lang.reflect.InvocationTargetException;
import java.text.ParseException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springside.modules.utils.SpringContextHolder;

import com.tjhx.common.utils.DateUtils;
import com.tjhx.entity.affair.PettyCash;
import com.tjhx.globals.Constants;
import com.tjhx.globals.SysConfig;
import com.tjhx.service.ServiceException;
import com.tjhx.service.affair.PettyCashManager;
import com.tjhx.service.struct.OrganizationManager;
import com.tjhx.web.BaseController;
import com.tjhx.web.report.ReportUtils;

@Controller
@RequestMapping(value = "/pettyCash")
public class PettyCashController extends BaseController {
	@Resource
	private PettyCashManager pettyCashManager;
	@Resource
	private OrganizationManager orgManager;

	// ----------------------------------------------------------------------
	// 门店
	// ----------------------------------------------------------------------
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
	 * 门店备用金列表信息-明细
	 * 
	 * @param model
	 * @return
	 * @throws ParseException
	 */
	@RequestMapping(value = "view/{id}")
	public String viewPettyCash_Action(@PathVariable("id") Integer id, Model model, HttpSession session)
			throws ParseException {
		PettyCash pettyCash = pettyCashManager.getPettyCashByUuid(id);
		if (null == pettyCash) {
			return "redirect:/" + Constants.PAGE_REQUEST_PREFIX + "/pettyCash/list";
		} else {
			model.addAttribute("pettyCash", pettyCash);

			if (0 == pettyCash.getOptType()) {// 支出
				return "affair/pettyCashPayViewForm";
			} else {// 拨入
				initIncomeSourceList(model);
				return "affair/pettyCashIncomeViewForm";
			}
		}
	}

	/**
	 * 编辑门店备用金列表信息
	 * 
	 * @param model
	 * @return
	 * @throws ParseException
	 */
	@RequestMapping(value = "edit/{id}")
	public String editPettyCash_Action(@PathVariable("id") Integer id, Model model, HttpSession session)
			throws ParseException {
		PettyCash pettyCash = pettyCashManager.getPettyCashByUuid(id);
		if (null == pettyCash) {
			return "redirect:/" + Constants.PAGE_REQUEST_PREFIX + "/pettyCash/list";
		} else {
			// 初始化门店备用金可编辑日期
			initPettyCashEditDate(pettyCash);

			model.addAttribute("pettyCash", pettyCash);

			if (0 == pettyCash.getOptType()) {// 支出
				return "affair/pettyCashPayForm";
			} else {// 拨入
				initIncomeSourceList(model);
				return "affair/pettyCashIncomeForm";
			}
		}
	}

	/**
	 * 新增门店备用金初始化(支付)
	 * 
	 * @param model
	 * @return
	 * @throws ParseException
	 */
	@RequestMapping(value = "payNew")
	public String initPettyCash_Pay_Action(Model model, HttpSession session) throws ParseException {
		PettyCash pettyCash = new PettyCash();

		pettyCash.setOptAmtShow(null);
		// 初始化门店备用金可编辑日期
		initPettyCashEditDate(pettyCash);

		model.addAttribute("pettyCash", pettyCash);

		return "affair/pettyCashPayForm";
	}

	/**
	 * 初始化门店备用金可编辑日期
	 * 
	 * @param pettyCash
	 * @throws ParseException
	 */
	private void initPettyCashEditDate(PettyCash pettyCash) throws ParseException {
		SysConfig sysConfig = SpringContextHolder.getBean("sysConfig");
		// 门店备用金可编辑天数
		int editDays = sysConfig.getPettyCashEditDays() * -1;
		String nowDate = DateUtils.getCurFormatDate("yyyy-MM-dd");
		String editDate = DateUtils.getNextDateFormatDate(nowDate, editDays, "yyyy-MM-dd");
		pettyCash.setEditDate(editDate);

	}

	/**
	 * 新增门店备用金初始化(拨入)
	 * 
	 * @param model
	 * @return
	 * @throws ParseException
	 */
	@RequestMapping(value = "incomeNew")
	public String initPettyCash_Income_Action(Model model, HttpSession session) throws ParseException {
		PettyCash pettyCash = new PettyCash();

		pettyCash.setOptAmtShow(null);

		// 初始化门店备用金可编辑日期
		initPettyCashEditDate(pettyCash);

		model.addAttribute("pettyCash", pettyCash);

		initIncomeSourceList(model);

		return "affair/pettyCashIncomeForm";
	}

	/**
	 * 初始化 操作类型下拉列表
	 * 
	 * @param model
	 */
	private void initIncomeSourceList(Model model) {

		Map<String, String> _incomeSourceList = new LinkedHashMap<String, String>();

		_incomeSourceList.put("", "");
		_incomeSourceList.put("1", "正常拨入");
		_incomeSourceList.put("2", "非常拨入");
		_incomeSourceList.put("4", "会计调帐");

		model.addAttribute("incomeSourceList", _incomeSourceList);
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

		pettyCashManager.delPettyCashByUuid(idArray, getUserInfo(session));

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

					// 初始化门店备用金可编辑日期
					initPettyCashEditDate(pettyCash);

					return "affair/pettyCashPayForm";
				}
			} else {// 修改操作
				try {
					pettyCashManager.updateNewPettyCash_Pay(pettyCash, getUserInfo(session));
				} catch (ServiceException ex) {
					// 添加错误消息
					addInfoMsg(model, ex.getMessage());

					return "redirect:/" + Constants.PAGE_REQUEST_PREFIX + "/pettyCash/list";
				}
			}
		} else {// 拨入
			if (null == pettyCash.getUuid()) {// 新增操作
				pettyCashManager.addNewPettyCash_Income(pettyCash, getUserInfo(session));
			} else {// 修改操作
				try {
					pettyCashManager.updateNewPettyCash_Income(pettyCash, getUserInfo(session));
				} catch (ServiceException ex) {
					// 添加错误消息
					addInfoMsg(model, ex.getMessage());

					return "redirect:/" + Constants.PAGE_REQUEST_PREFIX + "/pettyCash/list";
				}
			}
		}

		return "redirect:/" + Constants.PAGE_REQUEST_PREFIX + "/pettyCash/list";
	}

	// ----------------------------------------------------------------------
	// 总部
	// ----------------------------------------------------------------------
	/**
	 * 取得门店备用金列表
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "manageList")
	public String pettyCashManageList_Action(Model model, HttpSession session) {
		ReportUtils.initOrgList_Null_NoNRoot(orgManager, model);

		return "affair/pettyCashManageList";
	}

	@RequestMapping(value = "search")
	public String pettyCashSearch_Action(Model model, HttpServletRequest request) throws ServletRequestBindingException {
		ReportUtils.initOrgList_Null_NoNRoot(orgManager, model);

		String orgId = ServletRequestUtils.getStringParameter(request, "orgId");
		String optDateShow_start = ServletRequestUtils.getStringParameter(request, "optDateShow_start");
		String optDateShow_end = ServletRequestUtils.getStringParameter(request, "optDateShow_end");
		model.addAttribute("orgId", orgId);
		model.addAttribute("optDateShow_start", optDateShow_start);
		model.addAttribute("optDateShow_end", optDateShow_end);

		List<PettyCash> _pettyCashList = pettyCashManager.searchPettyCashList(orgId,
				DateUtils.transDateFormat(optDateShow_start, "yyyy-MM-dd", "yyyyMMdd"),
				DateUtils.transDateFormat(optDateShow_end, "yyyy-MM-dd", "yyyyMMdd"));

		for (PettyCash pettyCash : _pettyCashList) {
			pettyCash.setCreateDateStr(DateUtils.transDateFormat(pettyCash.getCreateDate(), "yyyy-MM-dd"));
		}
		model.addAttribute("pettyCashList", _pettyCashList);

		return "affair/pettyCashManageList";
	}

	// ----------------------------------------------------------------------
	// 总部-备用金结转
	// ----------------------------------------------------------------------
	/**
	 * 备用金结转初始化
	 * 
	 * @param model
	 * @param request
	 * @return
	 * @throws ServletRequestBindingException
	 */
	@RequestMapping(value = "carryOverInit")
	public String pettyCashCarryOverInit_Action(Model model, HttpServletRequest request)
			throws ServletRequestBindingException {
		ReportUtils.initOrgList_Null_NoNRoot(orgManager, model);

		String orgId = ServletRequestUtils.getStringParameter(request, "orgId");
		model.addAttribute("orgId", orgId);

		return "affair/pettyCashCarryOverList";
	}

	/**
	 * 备用金结转初始化
	 * 
	 * @param model
	 * @param request
	 * @return
	 * @throws ServletRequestBindingException
	 */
	@RequestMapping(value = "carryOverSearch")
	public String pettyCashCarryOverSearch_Action(Model model, HttpServletRequest request)
			throws ServletRequestBindingException {
		ReportUtils.initOrgList_Null_NoNRoot(orgManager, model);

		String orgId = ServletRequestUtils.getStringParameter(request, "orgId");
		model.addAttribute("orgId", orgId);

		List<PettyCash> _pettyCashList = pettyCashManager.searchPettyCashList_noCarryOver(orgId);
		for (PettyCash pettyCash : _pettyCashList) {
			pettyCash.setCreateDateStr(DateUtils.transDateFormat(pettyCash.getCreateDate(), "yyyy-MM-dd"));
		}
		model.addAttribute("pettyCashList", _pettyCashList);

		return "affair/pettyCashCarryOverList";
	}

	/**
	 * 备用金结转
	 * 
	 * @param model
	 * @param request
	 * @return
	 * @throws ServletRequestBindingException
	 */
	@RequestMapping(value = "carryOverConfirm")
	public String pettyCashCarryOverConfirm_Action(Model model, HttpServletRequest request)
			throws ServletRequestBindingException {
		ReportUtils.initOrgList_Null_NoNRoot(orgManager, model);
		Integer uuid = ServletRequestUtils.getIntParameter(request, "uuid");
		String orgId = ServletRequestUtils.getStringParameter(request, "orgId");
		String inspectTrsId = ServletRequestUtils.getStringParameter(request, "inspectTrsId");
		try {
			pettyCashManager.pettyCashCarryOver(orgId, uuid, inspectTrsId);
		} catch (ServiceException ex) {
			// 添加错误消息
			addInfoMsg(model, ex.getMessage());
		}
		return "redirect:/" + Constants.PAGE_REQUEST_PREFIX + "/pettyCash/carryOverSearch?orgId=" + orgId;
	}
}