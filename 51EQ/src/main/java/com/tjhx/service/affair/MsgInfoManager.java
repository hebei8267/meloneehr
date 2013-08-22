package com.tjhx.service.affair;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tjhx.common.utils.DateUtils;
import com.tjhx.dao.affair.MsgInfoJpaDao;
import com.tjhx.dao.affair.MsgInfoMyBatisDao;
import com.tjhx.dao.member.UserMyBatisDao;
import com.tjhx.entity.affair.MsgInfo;
import com.tjhx.entity.member.User;
import com.tjhx.entity.struct.Organization;
import com.tjhx.service.member.UserManager;
import com.tjhx.service.struct.OrganizationManager;

@Service
@Transactional(readOnly = true)
public class MsgInfoManager {
	@Resource
	private MsgInfoJpaDao msgInfoJpaDao;
	@Resource
	private UserMyBatisDao userMyBatisDao;
	@Resource
	private MsgInfoMyBatisDao msgInfoMyBatisDao;
	@Resource
	private OrganizationManager orgManager;
	@Resource
	private UserManager userManager;

	/**
	 * 删除 公告/消息 信息
	 * 
	 * @param uuid 公告/消息 编号
	 */
	@Transactional(readOnly = false)
	public void delMsgInfoByUuid(Integer uuid) {
		msgInfoJpaDao.delete(uuid);
	}

	/**
	 * 添加 公告/消息 信息
	 * 
	 * @param msgInfo 公告/消息 信息
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 */
	public void addMsgInfo(MsgInfo msgInfo, String sendUserLoginName) throws IllegalAccessException,
			InvocationTargetException {
		// 消息批次号
		msgInfo.setMsgBatchId(DateUtils.getCurFormatDate("yyyy-MM-dd-HH-mm-ss-ms"));
		// 日期-显示--不用设置
		String optDateShow = msgInfo.getOptDateShow();
		// 日期
		msgInfo.setOptDate(DateUtils.transDateFormat(optDateShow, "yyyy-MM-dd", "yyyyMMdd"));
		// 日期-年
		msgInfo.setOptDateY(DateUtils.transDateFormat(optDateShow, "yyyy-MM-dd", "yyyy"));
		// 日期-月
		msgInfo.setOptDateM(DateUtils.transDateFormat(optDateShow, "yyyy-MM-dd", "MM"));
		// 星期--不用设置
		// 发送人
		msgInfo.setSendUserLoginName(sendUserLoginName);

		if ("1".equals(msgInfo.getAcceptType())) {// 机构
			addMsgInfo_OrgAcceptType(msgInfo);
		} else {// 人员
			addMsgInfo_PerAcceptType(msgInfo);
		}

		// 主题--不用设置
		// 消息内容--不用设置

	}

	/**
	 * 添加 公告/消息 信息(人员模式-指定的人员)
	 * 
	 * @param msgInfo
	 */
	private void addMsgInfo_PerAcceptType(MsgInfo msgInfo) {
		StringBuffer acceptNameBuf = new StringBuffer();
		for (String userLoginName : msgInfo.getAcceptUserIds()) {
			MsgInfo _msgInfo = new MsgInfo();
			BeanUtils.copyProperties(msgInfo, _msgInfo);

			// 接收人
			_msgInfo.setAcceptUserLoginName(userLoginName);

			_msgInfo.setMsgType("2");
			msgInfoJpaDao.save(_msgInfo);

			User user = userManager.getUserByUuidLoginNameInCache(userLoginName);
			acceptNameBuf.append(user.getName() + "- (" + user.getOrgName() + " ); ");
		}

		MsgInfo _msgInfo = new MsgInfo();
		BeanUtils.copyProperties(msgInfo, _msgInfo);
		// 接收人名字集合
		_msgInfo.setAcceptNameSet(acceptNameBuf.toString());
		_msgInfo.setMsgType("1");
		msgInfoJpaDao.save(_msgInfo);
	}

	/**
	 * 添加 公告/消息 信息(机构模式-该机构下面的所有人员)
	 * 
	 * @param msgInfo
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 */
	private void addMsgInfo_OrgAcceptType(MsgInfo msgInfo) throws IllegalAccessException, InvocationTargetException {

		StringBuffer acceptNameBuf = new StringBuffer();
		for (String orgId : msgInfo.getAcceptOrgIds()) {

			List<User> userList = userMyBatisDao.getUserListByOrgUuid(Integer.valueOf(orgId));

			for (User user : userList) {
				MsgInfo _msgInfo = new MsgInfo();
				BeanUtils.copyProperties(msgInfo, _msgInfo);
				// 接收人
				_msgInfo.setAcceptUserLoginName(user.getLoginName());

				_msgInfo.setMsgType("2");
				msgInfoJpaDao.save(_msgInfo);
			}
			Organization _org = orgManager.getOrganizationByUuidInCache(Integer.valueOf(orgId));
			acceptNameBuf.append(_org.getName() + "; ");
		}

		MsgInfo _msgInfo = new MsgInfo();
		BeanUtils.copyProperties(msgInfo, _msgInfo);
		// 接收人名字集合
		_msgInfo.setAcceptNameSet(acceptNameBuf.toString());
		_msgInfo.setMsgType("1");
		msgInfoJpaDao.save(_msgInfo);
	}

	/**
	 * 取得 公告/消息 信息列表（根据用户编号取得未读状态的）
	 * 
	 * @param loginName
	 */
	public List<MsgInfo> getDefaultMsgInfoList(String loginName) {
		List<MsgInfo> _msgInfoList = msgInfoMyBatisDao.getMsgInfoList_UnreadStatus(loginName);
		return _msgInfoList;
	}

	/**
	 * 取得 公告/消息 信息列表
	 * 
	 * @param loginName
	 */
	public List<MsgInfo> getMsgInfoList(MsgInfo msgInfo) {
		List<MsgInfo> _msgInfoList = msgInfoMyBatisDao.getMsgInfoList(msgInfo);
		return _msgInfoList;
	}
}
