package com.itheima.utils;

import javax.servlet.http.Cookie;

/**
 * 根据key获得目标cookie
 * cookie特点:key:value
 * 
 * @author yp
 *
 */
public class CookieUtils {

	public static Cookie getTargetCookie(Cookie[] cookies , String key){
		if(cookies == null){
			return null;
		}
		
		for (Cookie cookie : cookies) {
			//根据key,获得目标cookie
			if(key.equals(cookie.getName())){
				return  cookie;
			}
		}
		
		return null;
		
	}
}
