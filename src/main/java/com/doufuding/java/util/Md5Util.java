package com.doufuding.java.util;

import java.security.MessageDigest;

public class Md5Util {
	private static MessageDigest md5 = null;
	
	static {
		try {
			md5 = MessageDigest.getInstance("MD5");
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}
	}
	
	/*
	 * 用于获取String的MD5值
	 * @param string
	 * @return
	 * 
	 */
	public static String getMd5(String str) {
		byte[] bs = md5.digest(str.getBytes());
		StringBuilder sBuilder = new StringBuilder(40);
		for(byte x:bs) {
			if ((x & 0xff)>>4 == 0) {
				sBuilder.append("0").append(Integer.toHexString(x & 0xff));
			} else {
				sBuilder.append(Integer.toHexString(x & 0xff));
			}
		}
		return sBuilder.toString();
	}
	
}
