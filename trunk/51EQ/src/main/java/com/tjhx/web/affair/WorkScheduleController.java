package com.tjhx.web.affair;

import java.text.ParseException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springside.modules.mapper.JsonMapper;

import com.tjhx.entity.affair.WorkSchedule_List_Show;
import com.tjhx.entity.affair.WorkType;
import com.tjhx.entity.member.Employee;
import com.tjhx.service.affair.WorkScheduleManager;
import com.tjhx.service.affair.WorkTypeManager;
import com.tjhx.service.member.EmployeeManager;
import com.tjhx.web.BaseController;

@Controller
@RequestMapping(value = "/workSchedule")
public class WorkScheduleController extends BaseController {
	@Resource
	private EmployeeManager empManager;
	@Resource
	private WorkScheduleManager workScheduleManager;
	@Resource
	private WorkTypeManager workTypeManager;

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

		List<WorkSchedule_List_Show> wsList = workScheduleManager.getWorkScheduleList(orgId, _empList);
		model.addAttribute("wsList", wsList);

		initWorkTypeList(orgId, model);

		return "affair/workScheduleList";
	}

	/**
	 * 取得指定机构的上班类型信息列表(开启状态)
	 * 
	 * @param orgId 机构编号
	 */
	private void initWorkTypeList(String orgId, Model model) {
		List<WorkType> _wtList = workTypeManager.getValidWorkTypeByOrgId(orgId);

		Map<String, String> wtList = new LinkedHashMap<String, String>();

		wtList.put("", "");
		for (WorkType wt : _wtList) {
			wtList.put(wt.getUuid().toString(), wt.getName());
		}
		model.addAttribute("wtList", wtList);

		Map<String, String> _wt_data = new LinkedHashMap<String, String>();
		for (WorkType wt : _wtList) {
			_wt_data.put(wt.getUuid().toString(),
					formatDate(wt.getStartDate().substring(0, 2), wt.getStartDate().substring(2, 4)) + " - "
							+ formatDate(wt.getEndDate().substring(0, 2), wt.getEndDate().substring(2, 4)));
		}
		JsonMapper mapper = new JsonMapper();
		model.addAttribute("_wt_data_set", mapper.toJson(_wt_data));

	}

	private String formatDate(String dateHr, String dateMin) {
		return String.format(String.format("%02d:%02d", Integer.valueOf(dateHr), Integer.valueOf(dateMin)));
	}
}
