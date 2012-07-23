package com.tjhx.web.syscfg;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tjhx.web.BaseController;

@Controller
@RequestMapping(value = "/syscfg/storeType")
public class StoreTypeController extends BaseController {

	@RequestMapping(value = "list")
	public String storeTypeList_Action() {

		return "syscfg/storeTypeList";
	}
}
