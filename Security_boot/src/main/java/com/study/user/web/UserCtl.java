package com.study.user.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class UserCtl {

	@RequestMapping(value = "/")
	public String main() {
		return "index";
	}
	
	@RequestMapping(value = "/admin")
	public String admin() {
		return "index";
	}
	
	@RequestMapping(value = "/userLogin")
	public String user() {
		return "index";
	}
}