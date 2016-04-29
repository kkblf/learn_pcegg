package com.lm.learn.pcegg.yt.pcdd_android.tools;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class PCMd5
{
  public static String MD5(String paramString) throws NoSuchAlgorithmException
  {
	  char[] arrayOfChar = paramString.toCharArray();
	  byte[] arrayOfByte1 = new byte[arrayOfChar.length];
	  int i=0;
	  while(i<arrayOfChar.length){
		  arrayOfByte1[i]= (byte)arrayOfChar[i];
		  i++;
	  }
	  MessageDigest localMessageDigest = MessageDigest.getInstance("MD5");
	  byte[] arrayOfByte2 = localMessageDigest.digest(arrayOfByte1);
	  StringBuffer localStringBuffer = new StringBuffer();
	  int j=0;
	  while(j<arrayOfByte2.length){
		  int k = 0xFF & arrayOfByte2[j];
          if (k < 16)
            localStringBuffer.append("0");
          localStringBuffer.append(Integer.toHexString(k));
		  j++;
	  }
	  
	  return localStringBuffer.toString();
  }
}