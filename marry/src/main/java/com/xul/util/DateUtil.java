package com.xul.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * 日期转换工具类
 */
public class DateUtil {
	/**
	 * 年月日时分秒(无下划线) yyyyMMddHHmmss 
	 */
	public static final String dtLong = "yyyyMMddHHmmss";

	/**
	 * 完整时间 yyyy-MM-dd HH:mm:ss
	 */
	public static final String simple = "yyyy-MM-dd HH:mm:ss";
	
	/**
	 * 完整时间 yyyy年MM月dd日  HH:mm:ss
	 */
	public static final String commom = "yyyy年MM月dd日  HH:mm:ss";
	
	/**
	 * 完整时间 yyyy年MM月dd日  HH时mm分ss秒
	 */
	public static final String commom_zh = "yyyy年MM月dd日  HH时mm分ss秒";

	/**
	 *  年月日(无下划线) yyyyMMdd 
	 */
	public static final String dtShort = "yyyyMMdd";

	/**
	 * 返回系统当前时间(精确到毫秒),作为一个唯一的订单编号
	 * 
	 * @return String <以yyyyMMddHHmmss为格式的当前系统时间>
	 */
	public static String getDateLong() {
		Date date = new Date();
		DateFormat df = new SimpleDateFormat(dtLong);
		return df.format(date);
	}

	/**
	 * 获取系统当前日期(精确到毫秒)，格式：yyyy-MM-dd HH:mm:ss
	 * 
	 * @return String
	 */
	public static String getDateFormatter() {
		Date date = new Date();
		DateFormat df = new SimpleDateFormat(simple);
		return df.format(date);
	}
	
	/**
	 * 获取系统当前日期(精确到毫秒)，格式：yyyy年MM月dd日  HH:mm:ss
	 * 
	 * @return String
	 */
	public static String getDateFormatterCommom() {
		Date date = new Date();
		DateFormat df = new SimpleDateFormat(commom);
		return df.format(date);
	}
	
	/**
	 * 获取系统当前日期(精确到毫秒)，格式：yyyy年MM月dd日  HH时mm分ss秒
	 * 
	 * @return String
	 */
	public static String getDateCommom_ZH() {
		Date date = new Date();
		DateFormat df = new SimpleDateFormat(commom_zh);
		return df.format(date);
	}
	
	/**
	 * 获取系统当期年月日(精确到天)，格式：yyyyMMdd
	 * 
	 * @return String
	 */
	public static String getDate() {
		Date date = new Date();
		DateFormat df = new SimpleDateFormat(dtShort);
		return df.format(date);
	}
	/**
	 *获取精确日期 
	 * 
	 * @return void
	 */
	public static String now() {
		return LocalDateTime.now().toString().replace("T", " ");
	}
}
