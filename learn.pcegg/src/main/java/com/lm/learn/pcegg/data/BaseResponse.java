package com.lm.learn.pcegg.data;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;;


@JsonIgnoreProperties(ignoreUnknown=true)
public class BaseResponse {
	private Integer status;
	private String msg;

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}
}
