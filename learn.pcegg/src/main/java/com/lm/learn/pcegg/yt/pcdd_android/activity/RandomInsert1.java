package com.lm.learn.pcegg.yt.pcdd_android.activity;


import java.util.Random;
import java.util.TreeSet;

public class RandomInsert1 extends BaseInsert {

	protected TreeSet<Integer> getData() {
		super.baseBound = 700;
		// 随机选出最大下注对象
		TreeSet<Integer> set = new TreeSet<Integer>();
		long l = 0L;
		Random random = new Random();
		//控制模拟结果的阈值
		while (set.size()<14 ) {
			//模拟快乐28 得出20个数字
			TreeSet<Integer> happy28MoniSet = new TreeSet<Integer>();
			while (happy28MoniSet.size() < 20) {
				happy28MoniSet.add(random.nextInt(80));
			}
			int counter = 0;
			int result = 0;
			int addItem = 0;
			//根据快乐28获取幸运28的模拟结果
			for (Integer i : happy28MoniSet) {
				counter++;
				addItem = addItem + i;
				if ((counter % 6) == 0) {
					result = result + (addItem % 10);
					addItem = 0;
				}
			}
			l += GlobalData.baseBet[result];
			set.add(result);

		}
		return set;
	}

}
