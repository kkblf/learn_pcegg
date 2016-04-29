package com.lm.learn.pcegg;

import java.net.UnknownHostException;
import java.util.LinkedHashSet;
import java.util.Random;
import java.util.TreeSet;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.Strings;
import com.lm.learn.pcegg.data.BaseResponse;
import com.lm.learn.pcegg.data.LoginResponse;
import com.lm.learn.pcegg.data.VolumeList;
import com.lm.learn.pcegg.yt.pcdd_android.activity.Base;
import com.lm.learn.pcegg.yt.pcdd_android.activity.GlobalData;
import com.lm.learn.pcegg.yt.pcdd_android.activity.Login;
import com.lm.learn.pcegg.yt.pcdd_android.activity.xy28.GetInvestInfo;
import com.lm.learn.pcegg.yt.pcdd_android.activity.xy28.XY28Insert;
import com.lm.learn.pcegg.yt.pcdd_android.activity.xy28.XY28VolumeList;

/**
 * Hello world!
 *
 */
public class test {
	public static void main(String[] args) {
		while (true) {
			GlobalData.username = "13361861219";
			GlobalData.password = "kkblf11539688";
			Base login = new Login();
			try {
				GlobalData.login = login.go(LoginResponse.class);
				System.out.println(new ObjectMapper().writeValueAsString(GlobalData.login));
				if (GlobalData.login != null && GlobalData.login.isSuccess()) {
					while(true){
					VolumeList volumeList = new XY28VolumeList().go(VolumeList.class);
					System.out.println(new ObjectMapper().writeValueAsString(volumeList));
					if (volumeList != null && volumeList.getStatus() == 0) {
						String volumeId = "";
						for (int i = 0; i < volumeList.getList().size(); i++) {
							if (volumeList.getList().get(i).getIsOpen().equals("1"))
								break;
							if (volumeList.getList().get(i).getIsInvest().equals("0"))
								volumeId = volumeList.getList().get(i).getNumber();
						}
						if(!Strings.isNullOrEmpty(volumeId)){
							GetInvestInfo getInvestInfo = new GetInvestInfo();
							getInvestInfo.setVolume(volumeId);
							BaseResponse getInvestInfoResponse = getInvestInfo.go(BaseResponse.class);
	
							XY28Insert insert = new XY28Insert();
							insert.setVolume(volumeId);
							BaseResponse insertResponse = insert.go(BaseResponse.class);
							System.out.println(new ObjectMapper().writeValueAsString(insertResponse));
						}
					

					} else {
						System.out.println("获取列表出错");
					}
					Thread.sleep(1000*60*3);
					}
				} else {
					System.out.println("登录出错");
				}
				Thread.sleep(1000*60*3);
			} catch (UnknownHostException e) {
				System.out.println("网络出错");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}

	}
}
