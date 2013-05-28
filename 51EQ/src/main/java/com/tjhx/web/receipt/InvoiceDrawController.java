package com.tjhx.web.receipt;

import java.text.ParseException;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tjhx.globals.Constants;
import com.tjhx.service.receipt.InvoiceDrawManager;
import com.tjhx.web.BaseController;

@Controller
@RequestMapping(value = "/invoiceDraw")
public class InvoiceDrawController extends BaseController {
	@Resource
	private InvoiceDrawManager invoiceDrawManager;

	@RequestMapping(value = { "list", "" })
	public String invoiceDrawList_Action(Model model, HttpSession session) throws ParseException {

		return "invoice/invoiceDrawList";
	}

	@RequestMapping(value = "search")
	public String invoiceDrawSearchList_Action(Model model, HttpServletRequest request)
			throws ServletRequestBindingException {
		return "invoice/invoiceDrawList";
	}

	@RequestMapping(value = "drawInit/{id}")
	public String invoiceDrawInit_Action(@PathVariable("id") Integer id, Model model) {
		return "invoice/invoiceDrawForm";
	}

	@RequestMapping(value = "draw/{id}")
	public String invoiceDraw_Action(@PathVariable("id") Integer storeRunUuid, Model model) {
		return "redirect:/" + Constants.PAGE_REQUEST_PREFIX + "/invoiceDraw";
	}
}
