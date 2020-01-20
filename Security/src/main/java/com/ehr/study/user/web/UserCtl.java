package com.ehr.study.user.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ehr.study.user.UserVO;

@Controller
public class UserCtl {
	
	@RequestMapping(value = "/auth")
	public String login(UserVO vo) {

		System.out.println(vo);
		System.out.println(vo);System.out.println(vo);
		return "login2";
	}
	
	@RequestMapping(value = "/admin")
	public void login2() {}
	
	@RequestMapping(value = "/login")
	public void login3() {}
}
