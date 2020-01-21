package com.study.security;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
//https://docs.spring.io/spring-security/site/docs/current/reference/htmlsingle
	
	@Autowired
	private DataSource dataSource;
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		
		//admin 123으로 로그인시 ADMIN부여
		//auth.inMemoryAuthentication().withUser("admin").password("123").roles("ADMIN");
		auth.jdbcAuthentication().dataSource(dataSource);
		/*
		 mysql
		 CREATE TABLE USERS (
		    USERNAME NVARCHAR(128) PRIMARY KEY,
		    PASSWORD NVARCHAR(128) NOT NULL,
		    ENABLED CHAR(1) CHECK (ENABLED IN ('Y','N') ) NOT NULL
		);
		CREATE TABLE AUTHORITIES (
		    USERNAME NVARCHAR(128) NOT NULL,
		    AUTHORITY NVARCHAR(128) NOT NULL
		);
		ALTER TABLE AUTHORITIES ADD CONSTRAINT AUTHORITIES_UNIQUE UNIQUE (USERNAME, AUTHORITY);
		ALTER TABLE AUTHORITIES ADD CONSTRAINT AUTHORITIES_FK1 FOREIGN KEY (USERNAME) REFERENCES USERS (USERNAME);
		
		hasRole ADMIN입력시 AUTHORITIES에 ROLE_ADMIN으로 등록해야 인식
		*/
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.authorizeRequests()//루트권한설정
				.antMatchers("/admin/**").hasRole("ADMIN")		
				.antMatchers("/**").permitAll();
		
		http
			.logout()
				.logoutUrl("/logout")
				.logoutSuccessUrl("/")
				.invalidateHttpSession(true);
		
		//접근권한 필요시 폼로그인으로 접근하도록 설정, 만들어둔 로그인폼 없을시 자동생성된 페이지 등장
		http
			.formLogin()
				.loginPage("/login").permitAll();
		//접근권한 필요시 http기본폼으로 접근하도록 설정
		//http
		//	.httpBasic();
	}

	
	

	
	
}
