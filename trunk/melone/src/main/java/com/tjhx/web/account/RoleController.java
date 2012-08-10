package com.tjhx.web.account;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.tjhx.entity.account.Role;
import com.tjhx.entity.shop.Shop;
import com.tjhx.globals.Constants;
import com.tjhx.service.ServiceException;
import com.tjhx.service.account.RoleManager;
import com.tjhx.web.BaseController;

@Controller
@RequestMapping(value = "/account/role")
public class RoleController extends BaseController {
	@Autowired
	private RoleManager roleManager;
	
	/**
	 * 取得Role信息列表
	 * 
	 * @param model
	 * @return
	 * @throws InvocationTargetException 
	 * @throws IllegalAccessException 
	 */
	@RequestMapping(value = { "list", "" })
	public String roleList_Action(Model model, HttpServletRequest request) throws IllegalAccessException, InvocationTargetException {
		Role role = new Role();
		// 取得Request中的查询参数
		BeanUtils.populate(role, request.getParameterMap());
		List<Role> roleList = roleManager.getRoleList(role);

		model.addAttribute("role", role);
		model.addAttribute("roleList", roleList);

		return "account/roleList";
	}
	
	/**
	 * 编辑Role信息
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "edit/{id}")
	public String editRole_Action(@PathVariable("id") Integer id, Model model, HttpServletRequest request) {

		Role role = roleManager.getRoleByUuid(id);
		if (null == role) {
			return "redirect:/" + Constants.PAGE_REQUEST_PREFIX + "/role/list";
		} else {
			model.addAttribute("role", role);
			return "account/roleForm";
		}

	}

//	/**
//	 * 删除Role信息
//	 * 
//	 * @param ids
//	 * @param model
//	 * @return
//	 */
//	@RequestMapping(value = "del")
//	public String delRole_Action(@RequestParam("uuids") String ids, Model model, HttpServletRequest request) {
//		String[] idArray = ids.split(",");
//		for (int i = 0; i < idArray.length; i++) {
//			roleManager.delRoleByUuid(Integer.parseInt(idArray[i]));
//		}
//
//		return "redirect:/" + Constants.PAGE_REQUEST_PREFIX + "/role/list";
//	}

	/**
	 * 新增Role初始化
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "new")
	public String initRole_Action(Model model, HttpServletRequest request) {
		// TODO 修改点
		Role role = new Role();
		model.addAttribute("role", role);
		
		return "account/roleForm";
	}

//	/**
//	 * 新增/修改 Role信息
//	 * 
//	 * @param role
//	 * @param model
//	 * @return
//	 * @throws IllegalAccessException
//	 * @throws InvocationTargetException
//	 */
//	@RequestMapping(value = "save")
//	public String saveRole_Action(@ModelAttribute("role") Role role, Model model, HttpServletRequest request)
//			throws IllegalAccessException, InvocationTargetException {
//
//		if (null == role.getUuid()) {// 新增操作
//			roleManager.addNewRole(role);
//		} else {// 修改操作
//			try {
//				roleManager.updateRole(role);
//			} catch (ServiceException ex) {
//				// 添加错误消息
//				addInfoMsg(model, ex.getMessage());
//			}
//		}
//
//		return "redirect:/" + Constants.PAGE_REQUEST_PREFIX + "/role/list";
//	}
}