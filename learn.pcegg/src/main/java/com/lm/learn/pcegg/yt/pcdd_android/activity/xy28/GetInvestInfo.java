package com.lm.learn.pcegg.yt.pcdd_android.activity.xy28;

import java.net.URLEncoder;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.Strings;
import com.lm.learn.pcegg.yt.pcdd_android.activity.Base;
import com.lm.learn.pcegg.yt.pcdd_android.activity.GlobalData;
import com.lm.learn.pcegg.yt.pcdd_android.tools.PCMd5;

public class GetInvestInfo extends Base {
	private String volume="";

	public String getVolume() {
		return volume;
	}

	public void setVolume(String volume) {
		this.volume = volume;
	}

	public <T> T go(Class<T> type) throws Exception {
		 Object localObject = super.getPubParam() + "&volume=" + this.volume;
	     String str1 = super.getPubParamKeyCode() + "&volume=" + URLEncoder.encode(this.volume, "UTF-8");
	     String str2 = PCMd5.MD5(str1 + "fsdf3423556sdfwe");
         String str3 = localObject + "&keycode=" + str2;
         localObject = str3;

		HttpPost request = new HttpPost(
				"http://apptran61.dandanz.com/webservice/play/getInvestInfo.ashx?" + localObject);
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
