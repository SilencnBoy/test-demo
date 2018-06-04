package com.hoyatod.applet.config;

public class WechatSDK {
	
	/**
	 * H5通过code获取token
	 */
	public final static String OAUTH_TOKEN_URL = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=APPID&secret=APPSECRET&code=CODE&grant_type=authorization_code";
	
	/**
	 * http：GET(请使用https协议)如果网页授权作用域为snsapi_userinfo，则此时开发者可以通过access_token和openid拉取用户信息了。
	 */
	public final static String USERINFO_URL = "https://api.weixin.qq.com/sns/userinfo?access_token=ACCESS_TOKEN&openid=OPENID&lang=zh_CN";
	
	/**
	 * 小程序搜权获取用户基本信息
	 */
	public final static String APPLET_OAUTH_USERINFO_URL = "https://api.weixin.qq.com/sns/jscode2session?appid=APPID&secret=APPSECRET&js_code=CODE&grant_type=authorization_code";
	
	/**
	 * 微信支付统一下单接口
	 */
	public final static String WECHAT_ORDER_URL = "https://api.mch.weixin.qq.com/pay/unifiedorder";
	
	/**
	 * 小程序授权获取TOKEN
	 */
	public final static String APPLET_OAUTH_TOKEN_URL = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET";
	
	/**
	 * 小程序获取二维码接口
	 */
	public final static String APPLET_CODE = "https://api.weixin.qq.com/cgi-bin/wxaapp/createwxaqrcode?access_token=ACCESS_TOKEN";
	
	/**
	 * 小程序模板接口
	 */
	public final static String APPLET_TEMPLATE_NEWS = "https://api.weixin.qq.com/cgi-bin/message/wxopen/template/send?access_token=ACCESS_TOKEN";

}
