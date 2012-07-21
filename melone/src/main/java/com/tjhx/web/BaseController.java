package com.tjhx.web;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.springframework.context.MessageSource;
import org.springframework.ui.Model;
import org.springside.modules.utils.SpringContextHolder;

import com.tjhx.globals.Constants;

public class BaseController {
	/** 提示消息 */
	private List<String> tipMsgList;
	/** 警告消息 */
	private List<String> warnMsgList;
	/** 错误消息 */
	private List<String> errMsgList;
	/** 表单对象 */
	private Model _model;

	/**
	 * 添加提示消息
	 * 
	 * @param model 页面模型对象
	 * @param msgId 消息编号
	 */
	protected void addTip(Model model, String msgId) {
		addTip(model, msgId, (String[]) null);
	}

	/**
	 * 添加提示消息
	 * 
	 * @param model 页面模型对象
	 * @param msgId 消息编号
	 * @param msgParam 消息参数
	 */
	protected void addTip(Model model, String msgId, String... msgParam) {
		if (null == tipMsgList) {
			tipMsgList = new ArrayList<String>();
		}
		tipMsgList.add(getMsg(msgId, msgParam));
		this._model = model;
	}

	/**
	 * 添加警告消息
	 * 
	 * @param model 页面模型对象
	 * @param msgId 消息编号
	 */
	protected void addWarn(Model model, String msgId) {
		addWarn(model, msgId, (String[]) null);
	}

	/**
	 * 添加警告消息
	 * 
	 * @param model 页面模型对象
	 * @param msgId 消息编号
	 * @param msgParam 消息参数
	 */
	protected void addWarn(Model model, String msgId, String... msgParam) {
		if (null == warnMsgList) {
			warnMsgList = new ArrayList<String>();
		}
		warnMsgList.add(getMsg(msgId, msgParam));
		this._model = model;
	}

	/**
	 * 添加错误消息
	 * 
	 * @param model 页面模型对象
	 * @param msgId 消息编号
	 */
	protected void addError(Model model, String msgId) {
		addError(model, msgId, (String[]) null);
	}

	/**
	 * 添加错误消息
	 * 
	 * @param model 页面模型对象
	 * @param msgId 消息编号
	 * @param msgParam 消息参数
	 */
	protected void addError(Model model, String msgId, String... msgParam) {
		if (null == errMsgList) {
			errMsgList = new ArrayList<String>();
		}
		errMsgList.add(getMsg(msgId, msgParam));
		this._model = model;
	}

	/**
	 * 取得消息内容
	 * 
	 * @param msgId 消息编号
	 * @param msgParam 消息参数
	 * @return 消息内容
	 */
	private String getMsg(String msgId, String... msgParam) {
		MessageSource mesResources = SpringContextHolder.getBean("messageSource");
		return mesResources.getMessage(msgId, msgParam, Locale.CHINESE);
	}

	/**
	 * 初始化消息列表
	 */
	public void initMsgList() {
		tipMsgList = null;
		warnMsgList = null;
		errMsgList = null;
	}

	/**
	 * 将消息保存至表单中
	 */
	public void insertMsgListToPageMode() {
		if (null != _model) {
			if (null != tipMsgList) {
				_model.addAttribute(Constants.SESSION_TIP_MSG_LIST, tipMsgList);
			}
			if (null != warnMsgList) {
				_model.addAttribute(Constants.SESSION_WARN_MSG_LIST, warnMsgList);
			}
			if (null != errMsgList) {
				_model.addAttribute(Constants.SESSION_ERR_MSG_LIST, errMsgList);
			}
		}
	}
}
