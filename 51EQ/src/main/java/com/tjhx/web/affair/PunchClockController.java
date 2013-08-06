package com.tjhx.web.affair;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tjhx.common.utils.DateUtils;
import com.tjhx.dao.member.EmployeeMyBatisDao;
import com.tjhx.entity.affair.PunchClock_List_Show;
import com.tjhx.entity.member.Employee;
import com.tjhx.service.affair.PunchClockManager;
import com.tjhx.service.struct.OrganizationManager;
import com.tjhx.web.BaseController;
import com.tjhx.web.report.ReportUtils;

@Controller
@RequestMapping(value = "/punchClock")
public class PunchClockController extends BaseController {

	@Resource
	private PunchClockManager punchClockManager;
	@Resource
	private EmployeeMyBatisDao employeeMyBatisDao;
	@Resource
	private OrganizationManager orgManager;

	@RequestMapping(value = { "list" })
	public String punchClockList_Action(Model model, HttpSession session) {

		_punchClockListAction(getUserInfo(session).getOrganization().getId(), DateUtils.getCurFormatDate("yyyy"),
				DateUtils.getCurFormatDate("MM"), model);

		return "affair/punchClockList";
	}

	@RequestMapping(value = { "list/{date}" })
	public String punchClockList_Param_Action(@PathVariable("date") String optDate, Model model, HttpSession session) {
		String optDateY = DateUtils.transDateFormat(optDate, "yyyy-MM", "yyyy");
		String optDateM = DateUtils.transDateFormat(optDate, "yyyy-MM", "MM");

		_punchClockListAction(getUserInfo(session).getOrganization().getId(), optDateY, optDateM, model);

		return "affair/punchClockList";
	}

	private void _punchClockListAction(String orgId, String optDateY, String optDateM, Model model) {
		List<Employee> _empList = employeeMyBatisDao.getEmployeeListByOrgId(orgId);
		model.addAttribute("empList", _empList);

		List<PunchClock_List_Show> _clockList = punchClockManager
				.getPunchClockList(orgId, optDateY, optDateM, _empList);
		model.addAttribute("punchClockList", _clockList);
	}

	// ######################################################################################################
	@RequestMapping(value = { "manage" })
	public String punchClockManageList_Action(Model model) {

		ReportUtils.initOrgList_All_Root(orgManager, model);

		return "affair/punchClockManageList";
	}

	@RequestMapping(value = { "manage/search" })
	public String punchClockManageSearchList_Action(HttpServletRequest request, Model model)
			throws ServletRequestBindingException {
		String orgId = ServletRequestUtils.getStringParameter(request, "orgId");
		String optDateShow = ServletRequestUtils.getStringParameter(request, "optDateShow");
		model.addAttribute("orgId", orgId);
		model.addAttribute("optDateShow", optDateShow);

		String optDateY = DateUtils.transDateFormat(optDateShow, "yyyy-MM", "yyyy");
		String optDateM = DateUtils.transDateFormat(optDateShow, "yyyy-MM", "MM");

		_punchClockListAction(orgId, optDateY, optDateM, model);

		ReportUtils.initOrgList_All_Root(orgManager, model);

		return "affair/punchClockManageList";
	}

}
