package com.tjhx.service.affair;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tjhx.common.utils.DateUtils;
import com.tjhx.dao.affair.MsgInfoJpaDao;
import com.tjhx.dao.member.UserMyBatisDao;
import com.tjhx.entity.affair.MsgInfo;
import com.tjhx.entity.member.User;
import com.tjhx.entity.struct.Organization;
import com.tjhx.service.struct.OrganizationManager;

@Service
@Transactional(readOnly = true)
public class MsgInfoManager {
	@Resource
	private MsgInfoJpaDao msgInfoJpaDao;
	@Resource
	private UserMyBatisDao userMyBatisDao;
	@Resource
	private OrganizationManager orgManager;

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

		}

		// 主题--不用设置
		// 消息内容--不用设置

	}

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
	 * 更新 公告/消息 信息
	 * 
	 * @param msgInfo 公告/消息 信息
	 */
	public void updateMsgInfo(MsgInfo msgInfo) {
		// TODO Auto-generated method stub

	}
}
