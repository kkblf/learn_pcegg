package com.lm.learn.pcegg.yt.pcdd_android.activity;

import javax.naming.OperationNotSupportedException;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.TreeSet;




public class Base {	 

	public <T> T go(Class<T> type) throws Exception {
		throw new OperationNotSupportedException();
	}
	
	public static String getPubParamKeyCode() throws UnsupportedEncodingException
	  {
	    return "t=2&v=" + URLEncoder.encode(GlobalData.versionCode, "UTF-8") 
	    + "&userid=" + URLEncoder.encode(GlobalData.login.getUserid(), "UTF-8") 
	    + "&token=" + URLEncoder.encode(GlobalData.login.getToken(), "UTF-8");
	  }
	
	public static String getPubParam() throws UnsupportedEncodingException
	  {
	    return "t=2&v=" + GlobalData.versionCode 
	    		+ "&userid=" + GlobalData.login.getUserid() 
	    		+ "&token=" + GlobalData.login.getToken() 
	    		+ "&deviceid=" + GlobalData.deviceid 
	    		+ "&phonemodel=" + URLEncoder.encode(GlobalData.phonemodel, "UTF-8") 
	    		+ "&osversion=" + URLEncoder.encode(GlobalData.osversion, "UTF-8") 
	    		+ "&simtype=" + URLEncoder.encode(GlobalData.simtype, "UTF-8") 
	    		+ "&simid=" + URLEncoder.encode(GlobalData.simid, "UTF-8") 
	    		+ "&pgname=" + GlobalData.pgname;
	  }
	
	private TreeSet<Integer> InsertData;

	public TreeSet<Integer> getInsertData() {
		return InsertData;
	}

	public void setInsertData(TreeSet<Integer> insertData) {
		InsertData = insertData;
	}
	
}
