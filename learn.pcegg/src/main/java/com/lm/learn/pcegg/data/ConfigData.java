package com.lm.learn.pcegg.data;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ConfigData {
	private InsertType insertType;

	public InsertType getInsertType() {
		return insertType;
	}

	public void setInsertType(InsertType insertType) {
		this.insertType = insertType;
	}
	
	public void Load(){
		
	}
	
}
