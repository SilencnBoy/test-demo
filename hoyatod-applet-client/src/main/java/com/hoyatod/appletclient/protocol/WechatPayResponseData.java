package com.hoyatod.appletclient.protocol;

import java.io.Serializable;

public class WechatPayResponseData implements Serializable{

	private static final long serialVersionUID = 1L;
	
	//以下字段在return_code为SUCCESS的时候有返回 
 	private String appid; 	//小程序ID 是 	String(32) 	调用接口提交的小程序ID
 	private String mch_id; 	//商户号 是 	String(32) 	1900000109 	调用接口提交的商户号
 	private String device_info; 	//设备号 否 	String(32) 	013467007045764 	调用接口提交的终端设备号，
 	private String nonce_str; 	//随机字符串 是 	String(32) 		微信返回的随机字符串
 	private String sign; 	//签名 是 	String(32) 	 	微信返回的签名，详见签名算法
 	private String result_code; 	//业务结果  是 	String(16) 	SUCCESS 	SUCCESS/FAIL
 	private String err_code; 	//错误代码  否 	String(32) 	SYSTEMERROR 	详细参见第6节错误列表
 	private String err_code_des; 	//错误代码描述  否 	String(128) 	系统错误 	错误返回的信息描述

	//	以下字段在return_code 和result_code都为SUCCESS的时候有返回
 	private String trade_type; 	//交易类型 是 	String(16) 	JSAPI 	调用接口提交的交易类型，取值如下：JSAPI，详细说明见参数规定
 	private String prepay_id;  //预支付交易会话标识 是 	String(64) 	 微信生成的预支付回话标识，用于后续接口调用中使用，该值有效期为2小时
 	
 	private String code_url; //二维码链接 否 String URl：weixin：//wxpay/s/An4baqw trade_type为NATIVE时有返回，用于生成二维码，展示给用户进行扫码支付
 	private String timeStamp; //随机字符串
 	private String signType; //签名类型 MD5
 	
 	public String getAppid() {
		return appid;
	}

	public void setAppid(String appid) {
		this.appid = appid;
	}

	public String getMch_id() {
		return mch_id;
	}

	public void setMch_id(String mch_id) {
		this.mch_id = mch_id;
	}

	public String getDevice_info() {
		return device_info;
	}

	public void setDevice_info(String device_info) {
		this.device_info = device_info;
	}

	public String getNonce_str() {
		return nonce_str;
	}

	public void setNonce_str(String nonce_str) {
		this.nonce_str = nonce_str;
	}

	public String getSign() {
		return sign;
	}

	public void setSign(String sign) {
		this.sign = sign;
	}

	public String getResult_code() {
		return result_code;
	}

	public void setResult_code(String result_code) {
		this.result_code = result_code;
	}

	public String getErr_code() {
		return err_code;
	}

	public void setErr_code(String err_code) {
		this.err_code = err_code;
	}

	public String getErr_code_des() {
		return err_code_des;
	}

	public void setErr_code_des(String err_code_des) {
		this.err_code_des = err_code_des;
	}

	public String getTrade_type() {
		return trade_type;
	}

	public void setTrade_type(String trade_type) {
		this.trade_type = trade_type;
	}

	public String getPrepay_id() {
		return prepay_id;
	}

	public void setPrepay_id(String prepay_id) {
		this.prepay_id = prepay_id;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getCode_url() {
		return code_url;
	}

	public void setCode_url(String code_url) {
		this.code_url = code_url;
	}

	public String getSignType() {
		return signType;
	}

	public void setSignType(String signType) {
		this.signType = signType;
	}

	public String getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(String timeStamp) {
		this.timeStamp = timeStamp;
	}

	@Override
	public String toString() {
		return "WechatPayResponseData [appid=" + appid + ", mch_id=" + mch_id + ", device_info=" + device_info
				+ ", nonce_str=" + nonce_str + ", sign=" + sign + ", result_code=" + result_code + ", err_code="
				+ err_code + ", err_code_des=" + err_code_des + ", trade_type=" + trade_type + ", prepay_id="
				+ prepay_id + ", code_url=" + code_url + ", timeStamp=" + timeStamp + ", signType=" + signType + "]";
	}
}
