package com.lm.learn.pcegg.data;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown=true)
public class VersionCheckResponse {
	private Integer status;
	private String needUpdate;
	private String releaseNote;
	private String updateUrl;
	private String version;
	private String msg;
	private String SP_ISOPEN;

	public String getSP_ISOPEN() {
		return SP_ISOPEN;
	}

	public void setSP_ISOPEN(String SP_ISOPEN) {
		this.SP_ISOPEN = SP_ISOPEN;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getNeedUpdate() {
		return needUpdate;
	}

	public void setNeedUpdate(String needUpdate) {
		this.needUpdate = needUpdate;
	}

	public String getReleaseNote() {
		return releaseNote;
	}

	public void setReleaseNote(String releaseNote) {
		this.releaseNote = releaseNote;
	}

	public String getUpdateUrl() {
		return updateUrl;
	}

	public void setUpdateUrl(String updateUrl) {
		this.updateUrl = updateUrl;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	
	public boolean isSuccess(){
		return this.msg.equals("成功");
	}
}
