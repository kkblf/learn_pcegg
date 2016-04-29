package com.lm.learn.pcegg.yt.pcdd_android.activity;


import java.util.Random;
import java.util.TreeSet;

public class OneThirdInsert extends BaseInsert {

	protected TreeSet<Integer> getData() {
		super.baseBound = 700;
		// 随机选出最大下注对象
		TreeSet<Integer> set = new TreeSet<Integer>();
		set.add(0);
		set.add(3);
		set.add(6);
		set.add(9);
		set.add(12);
		set.add(15);
		set.add(18);
		set.add(21);
		set.add(24);
		set.add(27);
		return set;
	}

}
