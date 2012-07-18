package com.tjhx.web;

import org.springframework.ui.Model;

public class BaseController {
	protected void addTip(Model model, String msgId) {
		addTip(model, msgId, null);
	}

	protected void addTip(Model model, String msgId, String... msgParam) {
		model.addAttribute("_BASE_CONTROLLER_ERR_INFO", getMsg(msgId, msgParam));
	}

	protected void addWarn(Model model, String msgId) {
		addWarn(model, msgId, null);
	}

	protected void addWarn(Model model, String msgId, String... msgParam) {
		model.addAttribute("_BASE_CONTROLLER_ERR_INFO", getMsg(msgId, msgParam));
	}

	protected void addError(Model model, String msgId) {
		addError(model, msgId, null);
	}

	protected void addError(Model model, String msgId, String... msgParam) {
		model.addAttribute("_BASE_CONTROLLER_ERR_INFO", getMsg(msgId, msgParam));
	}

	private String getMsg(String msgId, String... msgParam) {
		// TODO hebei
		return null;
	}
}
