package com.study.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		
		//admin 123으로 로그인시 ADMIN부여
		auth.inMemoryAuthentication().withUser("admin").password("123").roles("ADMIN");
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.authorizeRequests()//루트권한설정
				.antMatchers("/admin/**").hasRole("ADMIN")
				.antMatchers("/**").permitAll();
		
		//접근권한 필요시 폼로그인으로 접근하도록 설정, 만들어둔 로그인폼 없을시 자동생성된 페이지 등장
		http
			.formLogin();
		//접근권한 필요시 http기본폼으로 접근하도록 설정
		//http
		//	.httpBasic();
	}

	
	

	
	
}
