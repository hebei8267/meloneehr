package com.tjhx.web.affair;

import java.text.ParseException;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tjhx.entity.affair.WorkSchedule_List_Show;
import com.tjhx.entity.member.Employee;
import com.tjhx.service.affair.WorkScheduleManager;
import com.tjhx.service.member.EmployeeManager;
import com.tjhx.web.BaseController;

@Controller
@RequestMapping(value = "/workSchedule")
public class WorkScheduleController extends BaseController {
	@Resource
	private EmployeeManager empManager;
	@Resource
	private WorkScheduleManager wsManager;

	/**
	 * @param model
	 * @param session
	 * @return
	 * @throws ParseException
	 */
	@RequestMapping(value = { "list" })
	public String workScheduleList_Action(Model model, HttpSession session) throws ParseException {
		String orgId = getUserInfo(session).getOrganization().getId();
		List<Employee> _empList = empManager.getEmployeeListByOrgId(orgId);
		List<Employee> _tmpEmpList = empManager.getTmpEmployeeByOrgId_WorkFlg(orgId);
		// 取得本机构正式员工和兼职员工
		_empList.addAll(_tmpEmpList);
		model.addAttribute("empList", _empList);

		List<WorkSchedule_List_Show> wsList = wsManager.getWorkScheduleList();
		model.addAttribute("wsList", wsList);

		return "affair/workScheduleList";
	}

}
