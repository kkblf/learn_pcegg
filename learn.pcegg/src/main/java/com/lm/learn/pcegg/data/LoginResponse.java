package com.lm.learn.pcegg.data;

public class LoginResponse {
	private Integer status;
	private String userid;
	private String token;
	private String isrelogin;
	private String mobile;
	private String msg;

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getIsrelogin() {
		return isrelogin;
	}

	public void setIsrelogin(String isrelogin) {
		this.isrelogin = isrelogin;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}
	
	public boolean isSuccess(){
		return this.msg.equals("登录成功！");
	}
}
