package com.ehr.study.user;

public class UserVO {

	private String userId;
	private String pw;
	
	public UserVO() {}

	public String getuserId() {
		return userId;
	}

	public void setuserId(String userId) {
		this.userId = userId;
	}

	public String getPw() {
		return pw;
	}

	public void setPw(String pw) {
		this.pw = pw;
	}

	@Override
	public String toString() {
		return "UserVO [userId=" + userId + ", pw=" + pw + "]";
	}

}
