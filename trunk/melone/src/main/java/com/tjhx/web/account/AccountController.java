package com.tjhx.web.account;

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
			addError(model, "ERR_MSG_001");
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
	// User u=new User();
	// u.setLoginName("sa");
	// u.setPassWord("sasasa");
	// u.setName("sa");
	// u.setFirstLoginFlg(false);
	// accountManager.saveNewUser(u, null);
}
