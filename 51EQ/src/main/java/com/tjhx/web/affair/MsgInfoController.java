package com.tjhx.web.affair;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.tjhx.common.utils.DateUtils;
import com.tjhx.entity.affair.MsgInfo;
import com.tjhx.entity.member.User;
import com.tjhx.entity.struct.Organization;
import com.tjhx.globals.Constants;
import com.tjhx.service.ServiceException;
import com.tjhx.service.affair.MsgInfoManager;
import com.tjhx.service.member.UserManager;
import com.tjhx.service.struct.OrganizationManager;
import com.tjhx.web.BaseController;

@Controller
@RequestMapping(value = "/msgInfo")
public class MsgInfoController extends BaseController {
	@Resource
	private OrganizationManager orgnManager;
	@Resource
	private UserManager userManager;
	@Resource
	private MsgInfoManager msgInfoManager;

	@RequestMapping(value = { "list", "" })
	public String msgInfoList_Action(Model model) {
		return "affair/msgInfoList";
	}

	/**
	 * 删除 公告/消息 信息
	 * 
	 * @param ids
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "del")
	public String delMsgInfo_Action(@RequestParam("uuids") String ids, Model model, HttpSession session) {
		String[] idArray = ids.split(",");
		for (int i = 0; i < idArray.length; i++) {
			msgInfoManager.delMsgInfoByUuid(Integer.parseInt(idArray[i]));
		}

		return "redirect:/" + Constants.PAGE_REQUEST_PREFIX + "/msgInfo/list";
	}

	/**
	 * 新增公告/消息初始化
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "new")
	public String initMsgInfo_Action(Model model, HttpSession session) {
		User user = getUserInfo(session);
		String orgId = user.getOrganization().getId();

		MsgInfo _msgInfo = new MsgInfo();
		_msgInfo.setOptDateShow(DateUtils.getCurFormatDate("yyyy-MM-dd"));
		_msgInfo.setWeek(DateUtils.getWeekOfDate(DateUtils.getCurrentDate()));
		_msgInfo.setSendUserName(user.getName());

		model.addAttribute("msgInfo", _msgInfo);

		if (Constants.ROOT_ORG_ID.equals(orgId)) {// 总部机构
			List<Organization> _orgList = orgnManager.getAllOrganization();
			model.addAttribute("orgList", _orgList);

			List<User> _userList = userManager.getAllUserByCache();
			model.addAttribute("userList", _userList);

			return "affair/msgInfoForm_Root";
		} else {// 门店机构
			return "affair/msgInfoForm";
		}
	}

	/**
	 * 新增/修改 销售流水信息
	 * 
	 * @param cashRun
	 * @param model
	 * @return
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 */
	@RequestMapping(value = "save")
	public String saveMsgInfo_Action(@ModelAttribute("msgInfo") MsgInfo msgInfo, Model model, HttpSession session)
			throws IllegalAccessException, InvocationTargetException {
		User user = getUserInfo(session);
		String orgId = user.getOrganization().getId();

		if (null == msgInfo.getUuid()) {// 新增操作
			try {
				msgInfoManager.addMsgInfo(msgInfo, user.getLoginName());

			} catch (ServiceException ex) {
				// 添加错误消息
				addInfoMsg(model, ex.getMessage());

				if (Constants.ROOT_ORG_ID.equals(orgId)) {// 总部机构
					return "affair/msgInfoForm_Root";
				} else {// 门店机构
					return "affair/msgInfoForm";
				}
			}
		} else {// 修改操作
			try {
				msgInfoManager.updateMsgInfo(msgInfo);
			} catch (ServiceException ex) {
				// 添加错误消息
				addInfoMsg(model, ex.getMessage());

				if (Constants.ROOT_ORG_ID.equals(orgId)) {// 总部机构
					return "affair/msgInfoForm_Root";
				} else {// 门店机构
					return "affair/msgInfoForm";
				}
			}
		}

		return "redirect:/" + Constants.PAGE_REQUEST_PREFIX + "/msgInfo/list";
	}
}
