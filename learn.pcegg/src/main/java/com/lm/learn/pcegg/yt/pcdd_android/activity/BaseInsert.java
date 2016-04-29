package com.lm.learn.pcegg.yt.pcdd_android.activity;

import java.util.HashMap;
import java.util.TreeSet;

public class BaseInsert {	 
   
	protected HashMap<String,Object> args;
	protected Integer times;
	protected Integer baseBound=500;
	public TreeSet<Integer> getInsertData(Integer times, HashMap<String,Object> args){
		this.args = args;
		this.times = times;
		return getData();
	}
	
	protected TreeSet<Integer> getData(){
		return null;
	}
	
}
