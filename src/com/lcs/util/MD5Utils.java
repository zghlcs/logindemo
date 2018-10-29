package com.lcs.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * 使用MD5 对数据进行加密
 */
public class MD5Utils {
	/*
	 * 此方法是让调用者传递一个字符串进来，然后对这个字符串进行加密， 最后给调用者返回一个加密后的字符串数据
	 * 
	 * MD5它是一种算法，可以将任何的数据进行加密，加密后的数据称为密文 明文 ---md5---密文 "123" ---md5----"123efdrt34543tr435454" MD5算法，它也被称为单向不可逆的加密算法。
	 */
	public static String getMD5(String value) {

		StringBuilder sb = new StringBuilder();

		try {
			// 获取加密的对象
			MessageDigest digest = MessageDigest.getInstance("md5");

			// 对数据进行加密
			byte[] bs = digest.digest(value.getBytes());
			/*
			 * 加密后的数据是-128~127之间的数据。所以我们可以将8个二进制数位上的数字取出，然后转成十六进制， 将这些十六进制的值转成字符串返回。
			 * 
			 * 1100 1010 & 1111 1111 ------------ 1100 1010
			 */
			for (byte b : bs) {
				int x = b & 255;
				String s = Integer.toHexString(x);
				if (x >= 0 && x <= 15) {
					sb.append("0");
					sb.append(s);
				} else {
					sb.append(s);
				}
			}
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}

		return sb.toString();
	}

	public static void main(String[] args) {
		System.out.println(MD5Utils.getMD5("123"));
	}
}
