package com.ehr.study.security;

import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity
public class SSConfig extends WebSecurityConfigurerAdapter {

	/*
	패스에 보안을 적용하기 위한 메소드 (.antMatchers(). ..., .regexMatchers(). ...)
	access(String) 				주어진 SpEL 표현식의 평가 결과가 true이면 접근을 허용
	anonymous()					익명의 사용자의 접근을 허용
	authenticated()				인증된 사용자의 접근을 허용
	denyAll()					무조건 접근을 허용하지 않음
	fullyAuthenticated()		사용자가 완전히 인증되면 접근을 허용(기억되지 않음)
	hasAnyAuthority(String...)	사용자가 주어진 권한 중 어떤 것이라도 있다면 접근을 허용
	hasAnyRole(String...)		사용자가 주어진 역할 중 어떤 것이라도 있다면 접근을 허용
	hasAuthority(String)		사용자가 주어진 권한이 있다면 접근을 허용
	hasIpAddress(String)		주어진 IP로부터 요청이 왔다면 접근을 허용
	hasRole(String)				사용자가 주어진 역할이 있다면 접근을 허용
	not()						다른 접근 방식의 효과를 무효화
	permitAll()					무조건 접근을 허용
	rememberMe()				기억하기를 통해 인증된 사용자의 접근을 허용
	
	스프링 시큐리티에서 사용 가능한 SpEL
	authentication				사용자의 인증 객체
	denyAll						항상 거짓으로 평가함
	hasAnyRole(역할 목록)			사용자가 역할 목록 중 하나라도 역할이 있는 경우 참
	hasRole(역할)					사용자가 주어진 역할이 있는 경우 참
	hasIpAddress(IP 주소)			주어진 IP 주소로부터 요청이 오는 경우 참
	isAnonymous()				사용자가 익명인 경우 참
	isAuthenticated()			사용자가 인증된 경우 참
	isFullyAuthenticated()		사용자가 완전히 인증된 경우 참 (기억하기(remember-me)로는 인증되지 않음)
	isRememberMe()				사용자가 기억하기(remember-me)로 인증된 경우 참
	permitAll					항상 참으로 평가함 
	principal					사용자의 주체 객체
	*/
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.httpBasic()			//사용자는 HTTP기반 인증으로 인증가능
			.and()
			.formLogin()			//사용자는 폼기반 로그인으로 인증가능
			.and()
			.authorizeRequests()	//사용자 인증이 된 요청에 대해서만 요청을 허용
				.antMatchers("/login/{userId}").authenticated()			//패스가 ".." 인 요청은 인증되어야 함을 명시
																		//여러 패스를 명시하는 것도 가능("/spitters/**", "/spittles/mine")
				//.regexMatchers("/spitters/.*").authenticated()		//요청 패스에 정규 표현식을 사용할 수 있는 regexMatchers() 메소드도 있다.
				
				.antMatchers(HttpMethod.POST,".login").authenticated()	//모든 ".."에 대한 HTTP POST 요청이 인증되어야 함
				
				//.anyRequest().permitAll()								//다른 모든 요청들을 인증이나 권한 없이 허용
				
				.antMatchers("/users/{userId}").access("hasRole('ADMIN_MASTER')")
				
				.and()
				.formLogin()
					.loginPage("/login")
					.usernameParameter("id")
					.passwordParameter("pw")
					//.successHandler(successHandler())
					//.failureHandler(failureHandler())
					.permitAll();
	}

	/*
	 WebSecurityConfigurerAdapter를 확장하여 보안 설정을 했으므로
	 가장 쉽게 사용자 저장소를 설정하는 방법은 AuthenticationManagerBuilder를 인자로 갖는
	 configure() 메소드를 오버라이딩하는 것이다.
	
	사용자 상세 정보를 설정하는 메소드
	accountExpired(boolean)							계정이 만료되었는지 아닌지를 정의
	accountLocked(boolean)							계정이 잠겨 있는지 아닌지를 정의
	and()											설정을 연결하기 위해 사용
	authorities(GrantedAuthority...)				사용자에게 부여된 권한들을 명시
	authorities(List<? extends GrantedAuthority>)	사용자에게 부여된 권한들을 명시
	authorities(String...)							사용자에게 부여된 권한들을 명시
	credentialsExpired(boolean)						자격이 만료되었는지 아닌지를 정의
	disabled(boolean)								계정이 비활성화되었는지 아닌지를 정의
	password(String)								사용자의 암호를 명시
	roles(String...)								사용자에게 부여된 역할을 명시
	 */
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		//인메모리 사용자 저장소로 작업
		auth
		.inMemoryAuthentication()
			.withUser("user").password("123").roles("USER");
			
	}
	
	

}
