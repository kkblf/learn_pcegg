package com.lm.learn.pcegg.data;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class VolumeItem {
	private String lid;
	private String number;
	private String openTime;
	private String countDown;
	private String OpenDown;
	private String isInvest;
	private String tmoney;
	private String isOpen;
	private String openResultDetail;
	private String openResult;

	public String getLid() {
		return lid;
	}

	public void setLid(String lid) {
		this.lid = lid;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getOpenTime() {
		return openTime;
	}

	public void setOpenTime(String openTime) {
		this.openTime = openTime;
	}

	public String getCountDown() {
		return countDown;
	}

	public void setCountDown(String countDown) {
		this.countDown = countDown;
	}

	public String getOpenDown() {
		return OpenDown;
	}

	public void setOpenDown(String openDown) {
		OpenDown = openDown;
	}

	public String getIsInvest() {
		return isInvest;
	}

	public void setIsInvest(String isInvest) {
		this.isInvest = isInvest;
	}

	public String getTmoney() {
		return tmoney;
	}

	public void setTmoney(String tmoney) {
		this.tmoney = tmoney;
	}

	public String getIsOpen() {
		return isOpen;
	}

	public void setIsOpen(String isOpen) {
		this.isOpen = isOpen;
	}

	public String getOpenResultDetail() {
		return openResultDetail;
	}

	public void setOpenResultDetail(String openResultDetail) {
		this.openResultDetail = openResultDetail;
	}

	public String getOpenResult() {
		return openResult;
	}

	public void setOpenResult(String openResult) {
		this.openResult = openResult;
	}

}
