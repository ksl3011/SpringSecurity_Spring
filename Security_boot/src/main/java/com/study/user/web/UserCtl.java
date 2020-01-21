package com.study.user.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class UserCtl {

	@RequestMapping(value = "/")
	public void main() {}
	
	@RequestMapping(value = "/admin")
	public void admin() {}
	
	@RequestMapping(value = "/user")
	public void user() {}
	
	@RequestMapping(value = "/login")
	public void login() {}
}
