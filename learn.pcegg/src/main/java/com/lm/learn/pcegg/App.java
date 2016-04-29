package com.lm.learn.pcegg;

import java.io.File;
import java.net.UnknownHostException;
import java.util.LinkedHashSet;
import java.util.Random;
import java.util.TreeSet;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.Strings;
import com.lm.learn.pcegg.data.BaseResponse;
import com.lm.learn.pcegg.data.ConfigData;
import com.lm.learn.pcegg.data.GetEggsInfoResponse;
import com.lm.learn.pcegg.data.InsertType;
import com.lm.learn.pcegg.data.LoginResponse;
import com.lm.learn.pcegg.data.VersionCheckResponse;
import com.lm.learn.pcegg.data.VolumeList;
import com.lm.learn.pcegg.yt.pcdd_android.activity.Base;
import com.lm.learn.pcegg.yt.pcdd_android.activity.GlobalData;
import com.lm.learn.pcegg.yt.pcdd_android.activity.Login;
import com.lm.learn.pcegg.yt.pcdd_android.activity.TimesUtil;
import com.lm.learn.pcegg.yt.pcdd_android.activity.xy28.GetEggsInfo;
import com.lm.learn.pcegg.yt.pcdd_android.activity.xy28.GetInvestInfo;
import com.lm.learn.pcegg.yt.pcdd_android.activity.xy28.VersionCheck;
import com.lm.learn.pcegg.yt.pcdd_android.activity.xy28.XY28Insert;
import com.lm.learn.pcegg.yt.pcdd_android.activity.xy28.XY28VolumeList;

/**
 * Hello world!
 *
 */
public class App {
	public static void main(String[] args) {

		GlobalData.username = "pcegg_kkblf";
		GlobalData.password = "kkblf11539688";
		//初始化失败次数
		GlobalData.lostCount = 0;
		int sleepMillisecond = 1000;
		Base login = new Login();
		try {
			GlobalData.login = login.go(LoginResponse.class);
			System.out.println(new ObjectMapper().writeValueAsString(GlobalData.login));
			if (GlobalData.login != null && GlobalData.login.isSuccess()) {
				while (true) {
					//获取配置文件
					ConfigData config = new ConfigData();
					config.setInsertType(InsertType.eumOneThird);
					System.out.println(App.class.getResource("/").toString());
					File resultFile = new File("");
					(new ObjectMapper()).writeValue(resultFile, config);
					// 检查版本
					VersionCheck versionCheck = new VersionCheck();
					VersionCheckResponse versionCheckResponse = versionCheck.go(VersionCheckResponse.class);
					if (versionCheckResponse.isSuccess())
						GlobalData.versionCode = versionCheckResponse.getVersion();

					VolumeList volumeList = new XY28VolumeList().go(VolumeList.class);
					System.out.println(new ObjectMapper().writeValueAsString(volumeList));
					
					if (volumeList != null && volumeList.getStatus() == 0&&versionCheckResponse.isSuccess()) {
						// 获取金蛋数量
						GetEggsInfoResponse getEggsInfoResponse = new GetEggsInfo().go(GetEggsInfoResponse.class);
						// 计算下注期次
						String volumeId = "";
						for (int i = volumeList.getList().size()-1; i >-1; i--) {
							String isInvest = volumeList.getList().get(i).getIsInvest();
							String isOpen = volumeList.getList().get(i).getIsOpen();
							//是否投注
							if(isOpen.equals("0")&&isInvest.equals("1")){
							  break;
							}
							if(isOpen.equals("0")&&isInvest.equals("0")){
								//未投注期数
								volumeId = volumeList.getList().get(i).getNumber();
								//计算是否投注
								//计算失败次数
								
								break;
							}
						}
						
						// 计算下注倍数
						int times = TimesUtil.getTimes(Integer.valueOf(getEggsInfoResponse.getEggs()), InsertType.eumOneThird);
						if (!Strings.isNullOrEmpty(volumeId)) {
							// 获取某期
							GetInvestInfo getInvestInfo = new GetInvestInfo();
							getInvestInfo.setVolume(volumeId);
							BaseResponse getInvestInfoResponse = getInvestInfo.go(BaseResponse.class);
							// 下注
							XY28Insert insert = new XY28Insert();
							insert.setVolume(volumeId);
							insert.setTimes(times);
							BaseResponse insertResponse = insert.go(BaseResponse.class);
							System.out.println(new ObjectMapper().writeValueAsString(insertResponse));
							GlobalData.lastInvestData = insert.getInsertData();
						}
						
						//设置等待时间
						int openDown = Integer.valueOf(volumeList.getList().get(0).getOpenDown()==null?"0":volumeList.getList().get(0).getOpenDown());
						int cutDown = Integer.valueOf(volumeList.getList().get(0).getCountDown()==null?"0":volumeList.getList().get(0).getCountDown());
						System.out.println(openDown+"--"+cutDown);
						if(cutDown>2000)
						  sleepMillisecond = cutDown * 1000;
						else 
						  sleepMillisecond = 1000 * 60 * 4;

					} else {
						System.out.println("获取列表出错");
						GlobalData.login = login.go(LoginResponse.class);
						System.out.println(new ObjectMapper().writeValueAsString(GlobalData.login));
						sleepMillisecond = 1000;
					}
					Thread.sleep(sleepMillisecond);
				}

			} else {
				System.out.println("登录出错");
			}

		} catch (UnknownHostException e) {
			System.out.println("网络出错");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
