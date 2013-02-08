package com.tjhx.web.member;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tjhx.common.utils.Encrypter;
import com.tjhx.entity.member.User;
import com.tjhx.globals.Constants;
import com.tjhx.service.ServiceException;
import com.tjhx.service.member.UserManager;
import com.tjhx.web.BaseController;

@Controller
@RequestMapping(value = "/member")
public class MemberController extends BaseController {
	@Resource
	private UserManager userManager;

	/**
	 * 用户退出
	 * 
	 * @return
	 */
	@RequestMapping(value = "logout")
	public String userLogout_Action(HttpSession session) {
		session.invalidate();
		return "redirect:/" + Constants.PAGE_REQUEST_PREFIX + "/index";
	}

	/**
	 * 用户登录
	 * 
	 * @param loginName 用户名
	 * @param passWord 密码
	 * @return
	 * @throws ServletRequestBindingException
	 */
	@RequestMapping(value = "login")
	public String userLogin_Action(HttpServletRequest request, Model model, HttpSession session)
			throws ServletRequestBindingException {
		String loginName = ServletRequestUtils.getStringParameter(request, "loginName");
		String passWord = ServletRequestUtils.getStringParameter(request, "passWord");

		User user = userManager.findByLoginName(loginName);

		// 校验用户信息
		if (checkUserInfo(user, loginName, passWord)) {
			saveUserInfo(session, user);

			if (user.getFirstLoginFlg()) {// 第一次登录,修改默认密码
				return "redirect:/" + Constants.PAGE_REQUEST_PREFIX + "/member/initModPwd";
			} else {
				return "redirect:/" + Constants.PAGE_REQUEST_PREFIX + "/member/myspace";
			}
		} else {
			addInfoMsg(model, "ERR_MSG_LOGIN_001");
			return null;
		}
	}

	/**
	 * 修改密码
	 * 
	 * @return
	 * @throws ServletRequestBindingException
	 */
	@RequestMapping(value = "modPwd")
	public String modPwd_Action(HttpServletRequest request, Model model, HttpSession session)
			throws ServletRequestBindingException {
		Integer userUuid = ServletRequestUtils.getIntParameter(request, "uuid");
		String oldPassWord = ServletRequestUtils.getStringParameter(request, "oldPassWord");
		String newPassWord = ServletRequestUtils.getStringParameter(request, "newPassWord");
		try {
			User user = userManager.modUserPwd(userUuid, oldPassWord, newPassWord);

			saveUserInfo(session, user);
		} catch (ServiceException ex) {
			// 添加错误消息
			addInfoMsg(model, ex.getMessage());

			return "member/modPwd";
		}
		return "redirect:/" + Constants.PAGE_REQUEST_PREFIX + "/member/myspace";
	}

	/**
	 * 修改密码初始化
	 * 
	 * @return
	 */
	@RequestMapping(value = "initModPwd")
	public String initModPwd_Action() {
		return "member/modPwd";
	}

	/**
	 * 校验用户信息
	 * 
	 * @param user 用户信息
	 * @param loginName 用户名
	 * @param passWord 密码
	 * @return
	 */
	private boolean checkUserInfo(User user, String loginName, String passWord) {
		if (null != user) {
			return user.getLoginName().equals(loginName) && Encrypter.decrypt(user.getPassWord()).equals(passWord);
		}
		return false;
	}

	/**
	 * 我的空间 TODO
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "myspace")
	public String myspace_Action(Model model) {
		return "member/myspace";
	}

}