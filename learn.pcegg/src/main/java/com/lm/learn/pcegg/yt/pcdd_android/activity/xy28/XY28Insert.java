package com.lm.learn.pcegg.yt.pcdd_android.activity.xy28;

import java.net.URLEncoder;
import java.util.Random;
import java.util.TreeSet;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.Strings;
import com.lm.learn.pcegg.yt.pcdd_android.activity.Base;
import com.lm.learn.pcegg.yt.pcdd_android.activity.GlobalData;
import com.lm.learn.pcegg.yt.pcdd_android.activity.OneThirdInsert;
import com.lm.learn.pcegg.yt.pcdd_android.activity.RandomInsert1;
import com.lm.learn.pcegg.yt.pcdd_android.activity.RandomOneThirdInsert;
import com.lm.learn.pcegg.yt.pcdd_android.tools.PCMd5;

public class XY28Insert extends Base {
	protected static Integer pageNum = 1;
	protected static Integer pageSize = 20;
	private String volume = "";
	private int times = 1;

	public int getTimes() {
		return times;
	}

	public void setTimes(int times) {
		this.times = times;
	}

	public String getVolume() {
		return volume;
	}

	public void setVolume(String volume) {
		this.volume = volume;
	}

	public <T> T go(Class<T> type) throws Exception {
		// 随机选出最大下注对象
		TreeSet<Integer> set = RandomOneThirdInsert.getData(GlobalData.LastOpenResult);
		super.setInsertData(set);
		StringBuilder sb = new StringBuilder();
		long l = 0L;
		for (int i = 0; i < 28; i++) {
			if (set.contains(i)) {
				sb.append(GlobalData.baseBet[i] * times);
				l = l + GlobalData.baseBet[i] * times;
			} else {
				sb.append("0");
			}
			sb.append(",");
		}
		String str1 = sb.toString();
		System.out.println(sb);

		String localObject = "t=2&v=" + GlobalData.versionCode + "&userid=" + GlobalData.login.getUserid() + "&token="
				+ GlobalData.login.getToken() + "&volume=" + this.volume + "&eggs=" + str1 + "&totaleggs=" + l;
		String str3 = "t=2&v=" + URLEncoder.encode(GlobalData.versionCode, "UTF-8") + "&userid="
				+ URLEncoder.encode(GlobalData.login.getUserid(), "UTF-8") + "&token="
				+ URLEncoder.encode(GlobalData.login.getToken(), "UTF-8") + "&volume="
				+ URLEncoder.encode(this.volume, "UTF-8") + "&eggs=" + URLEncoder.encode(str1, "UTF-8") + "&totaleggs="
				+ URLEncoder.encode(new StringBuilder(String.valueOf(l)).toString(), "UTF-8");

		String str4 = PCMd5.MD5(str3 + "fsdf3423556sdfwe");
		String str5 = localObject + "&keycode=" + str4;
		localObject = str5;

		HttpPost request = new HttpPost("http://apptran61.dandanz.com/webservice/play/28insert.ashx?" + localObject);
		request.addHeader("User-Agent", "Dalvik/1.6.0 (Linux; U; Android 4.4.2; SM-G900F Build/KOT49H)");
		request.addHeader("Connection", "keep-Alive");
		request.addHeader("Content-Type", "application/x-www-form-urlencoded");
		CloseableHttpClient client = HttpClients.createDefault();
		CloseableHttpResponse response = client.execute(request);
		String responseEntity = EntityUtils.toString(response.getEntity(), "utf-8");
		if (Strings.isNullOrEmpty(responseEntity))
			return null;
		return new ObjectMapper().readValue(responseEntity, type);

	}

}
