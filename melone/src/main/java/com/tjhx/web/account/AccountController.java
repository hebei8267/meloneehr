package com.tjhx.web.account;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.tjhx.entity.account.Role;
import com.tjhx.entity.account.User;
import com.tjhx.entity.shop.Shop;
import com.tjhx.globals.Constants;
import com.tjhx.service.ServiceException;
import com.tjhx.service.account.AccountManager;
import com.tjhx.service.account.RoleManager;
import com.tjhx.service.shop.ShopManager;
import com.tjhx.web.BaseController;

@Controller
@RequestMapping(value = "/account")
public class AccountController extends BaseController {
	@Autowired
	private AccountManager accountManager;
	@Autowired
	private RoleManager roleManager;
	@Autowired
	private ShopManager shopManager;

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
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 */
	@RequestMapping(value = { "user/list", "user" })
	public String userList_Action(Model model, HttpServletRequest request) throws IllegalAccessException,
			InvocationTargetException {

		User user = new User();
		// 取得Request中的查询参数
		BeanUtils.populate(user, request.getParameterMap());
		// 取得用户信息(根据参数)
		List<User> userList = accountManager.getUserList(user);

		model.addAttribute("user", user);
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

		// 初始化[角色信息]/[门店信息]下拉菜单
		initUserSelect(model);

		return "account/userForm";
	}

	/**
	 * 初始化[角色信息]/[门店信息]下拉菜单
	 * 
	 * @param model
	 */
	private void initUserSelect(Model model) {
		// 取得角色信息
		List<Role> roleList = roleManager.getAllRole();
		model.addAttribute("roleList", roleList);

		// 取得所有门店信息
		List<Shop> shopList = shopManager.getAllShop();
		model.addAttribute("shopList", shopList);
	}

	/**
	 * 编辑用户信息
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "user/edit/{id}")
	public String editUser_Action(@PathVariable("id") Integer id, Model model, HttpServletRequest request) {

		User user = accountManager.getUserByUuid(id);
		if (null == user) {
			return "redirect:/" + Constants.PAGE_REQUEST_PREFIX + "/user/list";
		} else {
			model.addAttribute("user", user);

			// 初始化[角色信息]/[门店信息]下拉菜单
			initUserSelect(model);

			return "account/userForm";
		}

	}

	/**
	 * 新增/修改 用户信息
	 * 
	 * @param user
	 * @param model
	 * @return
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 */
	@RequestMapping(value = "user/save")
	public String saveUser_Action(@ModelAttribute("user") User user, Model model, HttpServletRequest request) {

		if (null == user.getUuid()) {// 新增操作

			try {
				// 转型为MultipartHttpRequest：
				MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
				// 获得文件
				MultipartFile imgFile = multipartRequest.getFile("imgFile");

				accountManager.addNewUser(user, imgFile);
			} catch (ServiceException ex) {
				// 添加错误消息
				addInfoMsg(model, ex.getMessage());

				// 新增用户初始化-清空登录名称
				user.setLoginName(null);
				model.addAttribute("user", user);

				// 初始化[角色信息]/[门店信息]下拉菜单
				initUserSelect(model);

				return "account/userForm";
			}
		} else {// 修改操作
			try {
				// 转型为MultipartHttpRequest：
				MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
				// 获得文件
				MultipartFile imgFile = multipartRequest.getFile("imgFile");

				accountManager.updateUser(user, imgFile);
			} catch (ServiceException ex) {
				// 添加错误消息
				addInfoMsg(model, ex.getMessage());
			}
		}

		return "redirect:/" + Constants.PAGE_REQUEST_PREFIX + "/account/user/list";
	}

}
