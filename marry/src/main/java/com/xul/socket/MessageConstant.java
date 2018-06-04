package com.xul.socket;

/**
 * 常量类
 * @author 徐良
 *
 */
public class MessageConstant {

	public static final String NAME_ACTIVE = "ACTIVE";
	public static final String NAME_EVENT = "EVENT";
	
	/** 消息类型-请求 **/
	public static final String TYPE_REQ = "REQ";
	/** 消息类型-响应 **/
	public static final String TYPE_RSP = "RSP";

	/** 事件号-进入教室 **/
	public static final String EVENTNO_CLASSROOM_IN = "1";

	/** 事件号-离开教室 **/
	public static final String EVENTNO_CLASSROOM_OUT = "2";

	/** 事件号-吃饭 **/
	public static final String EVENTNO_EAT = "3";

	/** 事件号-XXX **/
	
	
	/** 事件名-进入教室 **/
	public static final String EVENTNAME_CLASSROOM_IN = "进入教室";

	/** 事件名-离开教室 **/
	public static final String EVENTNAME_CLASSROOM_OUT = "离开教室";

	/** 事件名-吃饭 **/
	public static final String EVENTNAME_EAT = "吃饭";
	
	/** 超时时间 **/
	public static int time_timeout = 60;
	
	
}
