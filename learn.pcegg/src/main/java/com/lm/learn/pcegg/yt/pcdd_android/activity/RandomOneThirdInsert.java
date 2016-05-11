package com.lm.learn.pcegg.yt.pcdd_android.activity;


import java.util.Random;
import java.util.TreeSet;

public class RandomOneThirdInsert extends BaseInsert {
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
		if(GlobalData.lostCount==0){
			for(int i=0;i<27;i++){
				if((i % 3)==(this.base % 3))
					set.add(i);
			}
		} else if(GlobalData.lostCount==1) {
			int randValue =new Random().nextInt(3);
			for(int i=0;i<27;i++){
				if((i % 3)==(this.base % 3))
					set.add(i);
			}
			
		} else {
			for(Integer v:GlobalData.lastInvestData){
				set.add(v);
			}
		}
		return set;
	}
	
	public static TreeSet<Integer> getData(int base) {
		RandomOneThirdInsert insert = new RandomOneThirdInsert();
		insert.setBase(base);
		return insert.getData();
	}
	
	

}
