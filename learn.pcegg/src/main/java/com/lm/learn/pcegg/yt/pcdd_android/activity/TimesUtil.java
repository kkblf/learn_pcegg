package com.lm.learn.pcegg.yt.pcdd_android.activity;

import com.lm.learn.pcegg.data.InsertType;
import java.lang.Math;

public class TimesUtil {

	public static int getTimes(int totalEggs, InsertType insertType) {
		int ret = 1;
		switch (insertType) {
		case eumSimpleFullRandom:
		case eumMoniFullRandom:
		case eumContainsMiddleRandom:
			double eggs = Double.valueOf(totalEggs);
			ret = (int) (eggs / 10000);
			if (ret > 100000)
				ret = 100000;
			break;
		case eumOneThird:
			if (GlobalData.lostCount%10==0) 
				GlobalData.baseTimes =  ((int) (totalEggs / 3400000))+1;
			
			ret = (int) Math.pow(2, (GlobalData.lostCount%10));
			ret = ret * GlobalData.baseTimes;
			break;
		default:
			ret = 1;
			break;
		}

		return ret;
	}

}
