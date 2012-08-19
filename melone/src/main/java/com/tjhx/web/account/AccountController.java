package com.tjhx.web.account;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.tjhx.entity.account.User;
import com.tjhx.globals.Constants;
import com.tjhx.service.account.AccountManager;
import com.tjhx.web.BaseController;

@Controller
@RequestMapping(value = "/account")
public class AccountController extends BaseController {
	@Autowired
	private AccountManager accountManager;

	/**
	 * 用户登录
	 * 
	 * @param loginName 用户名
	 * @param passWd 密码
	 * @return
	 */
	// PathVariable
	@RequestMapping(value = "login")
	public String userLogin_Action(Model model, @RequestParam("name") String loginName,
			@RequestParam("passWd") String passWd, HttpSession session) {

		User user = accountManager.findByLoginName(loginName);

		// 校验用户信息
		if (checkUserInfo(user, loginName, passWd)) {
			session.setAttribute(Constants.SESSION_USER_INFO, user);
			// TODO hebei
			return "redirect:/" + Constants.PAGE_REQUEST_PREFIX + "/syscfg/storeType/list";
		} else {
			addInfoMsg(model, "ERR_MSG_ACC_001");
			return null;
		}
	}

	/**
	 * 校验用户信息
	 * 
	 * @param user 用户信息
	 * @param loginName 用户名
	 * @param passWd 密码
	 * @return
	 */
	private boolean checkUserInfo(User user, String loginName, String passWd) {
		if (null != user) {
			return user.getLoginName().equals(loginName) && user.getPassWord().equals(passWd);
		}
		return false;
	}

	/**
	 * 取得用户信息列表
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = { "user/list", "user" })
	public String userList_Action(Model model, HttpServletRequest request) {
		List<User> userList = accountManager.getAllUser();

		model.addAttribute("userList", userList);

		return "account/userList";
	}

	/**
	 * 删除用户信息
	 * 
	 * @param ids
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "user/del")
	public String delUser_Action(@RequestParam("uuids") String ids, Model model, HttpServletRequest request) {
		String[] idArray = ids.split(",");
		for (int i = 0; i < idArray.length; i++) {
			accountManager.delUserByUuid(Integer.parseInt(idArray[i]));
		}

		return "redirect:/" + Constants.PAGE_REQUEST_PREFIX + "/account/user/list";
	}

	/**
	 * 新增用户初始化
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "user/new")
	public String initUser_Action(Model model, HttpServletRequest request) {

		User user = new User();
		model.addAttribute("user", user);

		return "account/userForm";
	}
}
