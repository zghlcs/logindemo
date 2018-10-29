package com.lcs.util;

import javax.servlet.http.Cookie;

public class CookieUtils {
	public static Cookie getCookieByName(Cookie[] cookies, String cookieName){
		for (int i = 0; i < cookies.length; i++) {
			if (cookieName.equals(cookies[i].getName())) {
				return cookies[i];
			}
		}
		return null;
	}
}
