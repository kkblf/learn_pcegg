package com.lm.learn.pcegg.data;

import java.util.List;

public class VolumeList {
	private Integer status;
	private List<VolumeItem> list;
	private String totalSize;
	private String openwait;
	private String msg;

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public List<VolumeItem> getList() {
		return list;
	}

	public void setList(List<VolumeItem> list) {
		this.list = list;
	}

	public String getTotalSize() {
		return totalSize;
	}

	public void setTotalSize(String totalSize) {
		this.totalSize = totalSize;
	}

	public String getOpenwait() {
		return openwait;
	}

	public void setOpenwait(String openwait) {
		this.openwait = openwait;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}
}
