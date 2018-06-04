package com.hoyatod.applet.config;

public class WechatConfig {
	
	public final static String APPID = "wx3550fc1dff6a71f4" ; // 小程序appId
	public final static String APPSECRET = "a19e3a374db4597c39be03f244938212" ; // 小程序密匙
	
	public final static String MCH_ID = "1238627002";//微信支付分配的商户号
	public final static String API_KEY = "WIRAdZ6FFe0cq2oiETis3ICIFlrpcbE9";// 商户API密钥 自行去商户平台设置
	
	//微信公共号
	public final static String APP_ID = "" ;//微信公众账号ID
	public final static String APP_SECRET = "" ;//微信公众号密钥
	
	public final static int TOTAL_FEE = 200;//支付金额配置
	
	public final static String NOTIFY_URL = "http://123.56.15.82:8888/ssm/smallCallBack";//小程序回调
	public final static String SCANCODE_NOTIFY_URL = "http://123.56.15.82:9999/hoyatod-applet-server/remoting/sweepCallBack";//pc扫码回调
}
