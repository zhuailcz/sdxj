package com.ms.util;

import java.security.GeneralSecurityException;
import java.security.MessageDigest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * MD5Utils 类 这是MD5散列算法的工具类
 * 
 * 
 */

public class MD5Utils {

	private static Log log = LogFactory.getLog(MD5Utils.class);
	/**
	 * 方法 hashed 对原文进行MD5散列
	 * 
	 * @param plainText
	 *            原文
	 * @return String 散列的结果
	 */
	public static String hashed(String plainText) {

		byte[] temp = (plainText + "{1#2$3%4(5)6@7!poeeww$3%4(5)djjkkldss}")
				.getBytes();
		// byte[] temp = (plainText).getBytes();
		MessageDigest md;
		// 返回结果
		StringBuffer buffer = new StringBuffer();
		try {
			// 进行MD5散列
			md = MessageDigest.getInstance("md5");
			md.update(temp);
			temp = md.digest();

			// 将散列的结果转换为Hex字符串
			int i = 0;
			for (int offset = 0; offset < temp.length; offset++) {
				i = temp[offset];
				if (i < 0)
					i += 256;
				if (i < 16)
					buffer.append("0");
				buffer.append(Integer.toHexString(i));
			}
		} catch (GeneralSecurityException e) {
			if(log.isTraceEnabled()) log.trace("MD5散列时错误",e);
		}

		// 返回
		return buffer.toString();
	}

	/**
	 * 方法 main 对该类进行测试
	 */
	public static void main(String[] args) {
		System.out.println(MD5Utils.hashed("1"));
		System.out.println(MD5Utils.hashed("1"));
	}
}
