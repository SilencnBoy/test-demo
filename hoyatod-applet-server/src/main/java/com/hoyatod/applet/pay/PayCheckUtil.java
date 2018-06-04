package com.hoyatod.applet.pay;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PayCheckUtil {
	
	/**
	 * 	@deprecated:判断是否为中文	
	 *  @param string
	 *  @return boolean
	 */
	public static boolean isChinese(String string){
	    int n = 0;
	    for(int i = 0; i < string.length(); i++) {
	        n = (int)string.charAt(i);
	        if(!(19968 <= n && n <40869)) {
	            return false;
	        }
	    }
	    return true;
	}
	
	/**
	 * 	@deprecated:判断字符串是否为空
	 *  @param string
	 *  @return boolean
	 */
	public static boolean isNull(String string){
	    if(string == null){
	    	return true;
	    }else if(string !=null && string.trim().equals("") || string.trim().equals("null") || string.trim().equals("undefined")){
	    	return true;
	    }else {
			return false;
		}
	}
	
	/**
	 * 	@deprecated:验证字符串纯数字
	 *  @param str
	 *  @return boolean
	 */
	public static boolean isNumber(String str) {
		Pattern pattern = Pattern.compile("[0-9]*");
		Matcher match = pattern.matcher(str);
		if (match.matches() == false) {
			return false;
		} else {
			return true;
		}
	}
}
