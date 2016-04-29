package com.lm.learn.pcegg.yt.pcdd_android.activity;

import java.net.URLEncoder;

import org.apache.http.util.Args;
import org.apache.http.util.EntityUtils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.Strings;
import com.lm.learn.pcegg.yt.pcdd_android.tools.PCMd5;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.client.CloseableHttpClient;




public class Login extends Base {
	 

	public <T> T go(Class<T> type) throws Exception
	  {
		  Args.notEmpty(GlobalData.username, "账号不能为空");
		  Args.notEmpty(GlobalData.password, "密码不能为空");
	      
	      Object localObject = "t=2&v=" 
	      + GlobalData.versionCode 
	      + "&uid=" + GlobalData.username 
	      + "&pwd=" + URLEncoder.encode(GlobalData.password, "UTF-8") 
	      + "&deviceid=" + GlobalData.deviceid 
	      + "&cid=" + GlobalData.cid 
	      + "&phonemodel=" + URLEncoder.encode(GlobalData.phonemodel, "UTF-8") 
	      + "&osversion=" + URLEncoder.encode(GlobalData.osversion, "UTF-8") 
	      + "&simtype=" + URLEncoder.encode(GlobalData.simtype, "UTF-8") 
	      + "&simid=" + URLEncoder.encode(GlobalData.simid, "UTF-8") 
	      + "&pgname=" + GlobalData.pgname;
          String str1 = "t=2&v=" + URLEncoder.encode(GlobalData.versionCode, "UTF-8") 
          + "&uid=" + URLEncoder.encode(GlobalData.username, "UTF-8") 
          + "&pwd=" + URLEncoder.encode(GlobalData.password, "UTF-8") 
          + "&deviceid=" + URLEncoder.encode(GlobalData.deviceid, "UTF-8") 
          + "&cid=" + URLEncoder.encode(GlobalData.cid, "UTF-8");
          String str2 = PCMd5.MD5(str1 + "fsdf3423556sdfwe");
          String str3 = localObject + "&keycode=" + str2;
          localObject = str3;
         
          HttpPost request = new HttpPost("http://apptran.dandanz.com/webservice/base/LoginPlus.ashx?"+localObject);
          request.addHeader("User-Agent", "Dalvik/1.6.0 (Linux; U; Android 4.4.2; SM-G900F Build/KOT49H)");
          request.addHeader("Connection", "keep-Alive");
          request.addHeader("Content-Type", "application/x-www-form-urlencoded");
          CloseableHttpClient client =  HttpClients.createDefault();
          CloseableHttpResponse response = client.execute(request);
          String responseEntity = EntityUtils.toString(response.getEntity(), "utf-8");
          if(Strings.isNullOrEmpty(responseEntity))
        	  return null;
          return new ObjectMapper().readValue(responseEntity, type);
	      
	  }
	
}
