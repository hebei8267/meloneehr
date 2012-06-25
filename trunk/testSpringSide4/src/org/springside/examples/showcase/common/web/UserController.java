package org.springside.examples.showcase.common.web;

import java.util.Iterator;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springside.examples.showcase.common.entity.User;
import org.springside.examples.showcase.common.service.AccountManager;

import com.google.common.collect.Lists;

/**
 * @author 何 贝
 * 
 */
@Controller
@RequestMapping(value = "/common/user")
public class UserController {

	@Autowired
	private AccountManager accountManager;

	/**
	 * 取得用户信息列表
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = { "list", "" })
	public String list(Model model) {

		List<User> users = accountManager.getAllUser();
		model.addAttribute("users", users);
		return "common/userList";
	}

	/**
	 * 更具用户ID取得用户信息
	 * 
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "get/{id}")
	public String getUser(@PathVariable("id") Long id, Model model) {
		model.addAttribute("user", accountManager.getUser(id));
		initStatus(model);
		return "common/userForm";
	}

	/**
	 * 更具用户ID取得用户信息
	 * 
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "getByEffective/{id}")
	public String getUserEffective(@PathVariable("id") Long id, Model model) {

		model.addAttribute("user", accountManager.getUser(id, null));
		// 测试分页
		Page<User> users = accountManager.findByName("111");
		System.out.println("TotalElements " + users.getTotalElements());// 共有多少数据
		System.out.println("TotalPages " + users.getTotalPages());// 共有多少页数据
		System.out.println("Size " + users.getSize());// 每页有多少数据
		System.out.println("Number " + users.getNumber());// 第几页 数字加一
		System.out.println("NumberOfElements " + users.getNumberOfElements());// 该页有几个数据

		List<User> userList = users.getContent();
		for (Iterator iterator = userList.iterator(); iterator.hasNext();) {
			User user = (User) iterator.next();
			System.out.println("user LoginName " + user.getLoginName());
		}

		initStatus(model);
		return "common/userForm";
	}

	/**
	 * 创建一个新用户时初始化
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "create")
	public String createUser(Model model) {

		model.addAttribute("user", new User());

		initStatus(model);
		return "common/userForm";
	}

	/**
	 * 保存用户信息
	 * 
	 * @param user
	 * @param bindingResult
	 * @param model
	 * @param redirectAttributes
	 * @return
	 */
	@RequestMapping(value = "save")
	public String saveUser(@Valid @ModelAttribute("user") User user, BindingResult bindingResult, Model model,
			RedirectAttributes redirectAttributes) {
		if (bindingResult.hasErrors()) {
			initStatus(model);
			return "common/userForm";
		}

		accountManager.saveUser(user);
		redirectAttributes.addFlashAttribute("message", "保存用户成功");
		return "redirect:/sc/common/user";
	}

	/**
	 * 初始化用户状态的下拉菜单
	 * 
	 * @param model
	 */
	private void initStatus(Model model) {
		List<String> allStatus = Lists.newArrayList("enabled", "disabled");
		model.addAttribute("allStatus", allStatus);
	}
}
