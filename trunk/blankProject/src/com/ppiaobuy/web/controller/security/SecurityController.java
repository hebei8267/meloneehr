package com.ppiaobuy.web.controller.security;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/security")
public class SecurityController {
	@RequestMapping(value = "/login")
	public void login() {
		// DO NOTHIING
		System.out.println("login");
	}
	
	@RequestMapping(value = "/loginok")
	public void loginok() {
		// DO NOTHIING
		System.out.println("loginok");
	}
}
