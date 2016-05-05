package com.lm.learn.pcegg.yt.pcdd_android.activity;

import java.util.TreeSet;

import com.lm.learn.pcegg.data.LoginResponse;




public class GlobalData {
	public static String username;
	public static String password;
	public static LoginResponse login;
	public static int lostCount=0;	
	public static Integer LastOpenResult=-1;
	public static String versionCode="3.1.8";
	public static int baseTimes = 1;
	public static TreeSet<Integer> lastInvestData = null;
	public static Integer LastInvestVolumeId = 0;
	
	public static final String deviceid = "99000559836016";
	public static final String cid = "";
	public static final String phonemodel = "Coolpad 8675-A";
	public static final String osversion = "4.4.4";
	public static final String simtype = "中国电信";
	public static final String simid = "460110130166402";
	public static final String pgname = "com.yt.pcdd_android.activity";
	
	public static final int[] baseBet={1,3,6,10,15,21,28,36,45,55,63,69,73,75,75,73,69,63,55,45,36,28,21,15,10,6,3,1};
	
	 
	
}
