package com.tjhx.web.member;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tjhx.service.member.EmployeeManager;
import com.tjhx.service.struct.OrganizationManager;
import com.tjhx.web.BaseController;
import com.tjhx.web.report.ReportUtils;

@Controller
@RequestMapping(value = "/employee")
public class EmployeeController extends BaseController {

	@Resource
	private EmployeeManager empManager;
	@Resource
	private OrganizationManager orgManager;

	@RequestMapping(value = { "" })
	public String initEmployeeList_Action(Model model, HttpSession session) {
		ReportUtils.initOrgList_All_Null(orgManager, model);

		return "member/employeeList";
	}

}
