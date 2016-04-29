package com.lm.learn.pcegg.data;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;;


@JsonIgnoreProperties(ignoreUnknown=true)
public class GetEggsInfoResponse {
	private Integer status;
	private String msg;
	private String eggs;
	private String cash;
	private String hasmobile;
	private String unreadMsg;

	public String getEggs() {
		return eggs;
	}

	public void setEggs(String eggs) {
		this.eggs = eggs;
	}

	public String getCash() {
		return cash;
	}

	public void setCash(String cash) {
		this.cash = cash;
	}

	public String getHasmobile() {
		return hasmobile;
	}

	public void setHasmobile(String hasmobile) {
		this.hasmobile = hasmobile;
	}

	public String getUnreadMsg() {
		return unreadMsg;
	}

	public void setUnreadMsg(String unreadMsg) {
		this.unreadMsg = unreadMsg;
	}

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
