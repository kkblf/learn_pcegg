package com.lm.learn.pcegg;

import java.io.File;
import java.net.UnknownHostException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
		String s = "";
		s += s;
		final Logger logger = LoggerFactory.getLogger(App.class);
		logger.info("启动应用...");

		GlobalData.username = "pcegg_kkblf";
		GlobalData.password = "kkblf11539688";
		//初始化失败次数
		GlobalData.lostCount = 0;
		int sleepMillisecond = 1000;
		Base login = new Login();
		try {
			logger.info("准备登录");
			GlobalData.login = login.go(LoginResponse.class);
			logger.info(new ObjectMapper().writeValueAsString(GlobalData.login));
			if (GlobalData.login != null && GlobalData.login.isSuccess()) {
				while (true) {
					//获取配置文件
					ConfigData config = new ConfigData();
					config.setInsertType(InsertType.eumOneThird);
					//System.out.println(App.class.getResource("/").toString());
					//File resultFile = new File("");
					//(new ObjectMapper()).writeValue(resultFile, config);
					// 检查版本
					VersionCheck versionCheck = new VersionCheck();
					logger.info("准备检查版本");
					VersionCheckResponse versionCheckResponse = versionCheck.go(VersionCheckResponse.class);
					logger.info(new ObjectMapper().writeValueAsString(versionCheckResponse));
					if (versionCheckResponse.isSuccess())
						GlobalData.versionCode = versionCheckResponse.getVersion();

					logger.info("准备获取投注列表");
					VolumeList volumeList = new XY28VolumeList().go(VolumeList.class);
					logger.info(new ObjectMapper().writeValueAsString(volumeList));
					
					int openDown=0;
					int cutDown=0;
					int retry = 0;
					if (volumeList != null && volumeList.getStatus() == 0&&versionCheckResponse.isSuccess()) {
						// 获取金蛋数量
						logger.info("准备获取用户信息");
						GetEggsInfoResponse getEggsInfoResponse = new GetEggsInfo().go(GetEggsInfoResponse.class);
						logger.info(new ObjectMapper().writeValueAsString(getEggsInfoResponse));
						// 计算下注期次
						String volumeId = "";
						for (int i = volumeList.getList().size()-1; i >-1; i--) {
							String isInvest = volumeList.getList().get(i).getIsInvest();
							String isOpen = volumeList.getList().get(i).getIsOpen();
							//是否投注
							if(isOpen.equals("0")&&isInvest.equals("1")){
								openDown = Integer.valueOf(volumeList.getList().get(i).getOpenDown()==null?"0":volumeList.getList().get(i).getOpenDown());
								cutDown = Integer.valueOf(volumeList.getList().get(i).getCountDown()==null?"0":volumeList.getList().get(i).getCountDown());
								break;
							}
							if(isOpen.equals("0")&&isInvest.equals("0")){
								//未投注期数
								volumeId = volumeList.getList().get(i).getNumber();
								logger.info("准备投注【"+volumeId+"】期");
								//获取上期的开奖结果
								String lastOpenResult ="0";
								lastOpenResult =volumeList.getList().get(i+1).getOpenResult();
								GlobalData.LastOpenResult = Integer.parseInt(lastOpenResult);
								logger.info("上期【"+volumeList.getList().get(i+1).getNumber()+"】开奖结果 "+ lastOpenResult);
								//计算是否投注
								if(GlobalData.LastInvestVolumeId+1!=Integer.parseInt(volumeId)){
									logger.info("上期未投注");
									logger.info("设置失败次数为0");
									GlobalData.lostCount = 0;
								} else if(GlobalData.lastInvestData==null){
									logger.info("获取不到上次投注数据");
									logger.info("设置失败次数为0");
									GlobalData.lostCount = 0;
								} else {
								//计算失败次数
									if(GlobalData.lastInvestData.contains(GlobalData.LastOpenResult)){
										logger.info("上期投注成功");
										logger.info("设置失败次数为0");
										GlobalData.lostCount = 0;
									} else {
										logger.info("上期投注失败");
										logger.info("设置失败次数+1");
										GlobalData.lostCount = GlobalData.lostCount +1;
										logger.info("设置失败次数 "+GlobalData.lostCount);
									}
								}
								openDown = Integer.valueOf(volumeList.getList().get(i).getOpenDown()==null?"0":volumeList.getList().get(i).getOpenDown());
								cutDown = Integer.valueOf(volumeList.getList().get(i).getCountDown()==null?"0":volumeList.getList().get(i).getCountDown());
								break;
							}
						}
						
						// 计算下注倍数
						logger.info("计算投注倍数");
						int times = TimesUtil.getTimes(Integer.valueOf(getEggsInfoResponse.getEggs()), InsertType.eumOneThird);
						logger.info(times+" 倍");
						if (!Strings.isNullOrEmpty(volumeId)) {
							// 获取某期
							GetInvestInfo getInvestInfo = new GetInvestInfo();
							getInvestInfo.setVolume(volumeId);
							logger.info("获取【"+volumeId+"】准备投注");
							BaseResponse getInvestInfoResponse = getInvestInfo.go(BaseResponse.class);
							logger.info(new ObjectMapper().writeValueAsString(getInvestInfoResponse));
							// 下注
							XY28Insert insert = new XY28Insert();
							insert.setVolume(volumeId);
							insert.setTimes(times);
							logger.info("准备下注【"+volumeId+"】");
							BaseResponse insertResponse = insert.go(BaseResponse.class);
							logger.info(new ObjectMapper().writeValueAsString(insertResponse));
							retry = 3;
							if(insertResponse.isSuccess()){
								logger.info(new ObjectMapper().writeValueAsString(insert.getInsertData()));
								GlobalData.lastInvestData = insert.getInsertData();
								GlobalData.LastInvestVolumeId = Integer.parseInt(volumeId);
								retry = 0;
							} else if(insertResponse.getMsg().equals("请先去快速任务赚取足够的收入，兑换金蛋就可以玩了！")){
								  GlobalData.lostCount = 0;
								  logger.info("金额不足，重置失败次数为0");
							}
							
						}
						
						//设置等待时间
						logger.info("开奖时间【"+openDown+"】");
						logger.info("截止投注时间【"+cutDown+"】");
						logger.info("重试时间【"+retry+"】");
						if(retry>0){
						  sleepMillisecond = retry * 1000;	
						} else if(cutDown>0)
						  sleepMillisecond = (cutDown+120) * 1000;
						else if(openDown>0)
						  sleepMillisecond = 1000 * openDown;
						else 
						  sleepMillisecond = 1000 * 60;

					} else {
						System.out.println("获取列表出错");
						GlobalData.login = login.go(LoginResponse.class);
						System.out.println(new ObjectMapper().writeValueAsString(GlobalData.login));
						sleepMillisecond = 1000;
					}
					logger.info("设置等待时间【"+sleepMillisecond+"】");
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
