package com.lm.learn.pcegg.yt.pcdd_android.activity;


import java.util.Random;
import java.util.TreeSet;

public class OneThirdInsert extends BaseInsert {
    private int base;

	public int getBase() {
		return base;
	}

	public void setBase(int base) {
		this.base = base;
	}

	protected TreeSet<Integer> getData() {
		super.baseBound = 700;
		TreeSet<Integer> set = new TreeSet<Integer>();
		for(int i=0;i<27;i++){
			if((i % 3)==(this.base % 3))
				set.add(i);
		}
		return set;
	}
	
	public static TreeSet<Integer> getData(int base) {
		OneThirdInsert insert = new OneThirdInsert();
		insert.setBase(base);
		return insert.getData();
	}
	
	

}
