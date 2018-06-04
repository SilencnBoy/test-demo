package com.hoyatod.appletclient.protocol;

import java.io.Serializable;

/**
 * TODO WechatData 微信支付基本信息实体类
 * 
 * @author Sliencn
 * @date 2017年4月17日 下午3:25:57
 */
public class WechatData implements Serializable {
	private static final long serialVersionUID = 1L;

	private String openid;     // 微信唯一标识
	private String trade_type; // 交易类型 是 String(16) MWEB调用接口提交的交易类型，取值如下：JSAPI，NATIVE，APP，,H5支付固定传MWEB
	private String prepay_id; // 预支付交 会话标识 是 String(64)微信生成的预支付回话标识，用于后续接口调用中使用，该值有效期为2小时,针对H5支付此参数无特殊用途
	private String mweb_url; // 支付跳转链接 是 String(64)mweb_url为拉起微信支付收银台的中间页面，可通过访问该url来拉起微信客户端，完成支付,mweb_url的有效期为5分钟。
	
	private String attach;
	private String body;
	private int total_fee;
	private String spbill_create_ip;
	
	private String code;
	
	public String getOpenid() {
		return openid;
	}

	public void setOpenid(String openid) {
		this.openid = openid;
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

	public String getMweb_url() {
		return mweb_url;
	}

	public void setMweb_url(String mweb_url) {
		this.mweb_url = mweb_url;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getAttach() {
		return attach;
	}

	public void setAttach(String attach) {
		this.attach = attach;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public int getTotal_fee() {
		return total_fee;
	}

	public void setTotal_fee(int total_fee) {
		this.total_fee = total_fee;
	}

	public String getSpbill_create_ip() {
		return spbill_create_ip;
	}

	public void setSpbill_create_ip(String spbill_create_ip) {
		this.spbill_create_ip = spbill_create_ip;
	}

	@Override
	public String toString() {
		return "WechatData [openid=" + openid + ", trade_type=" + trade_type + ", prepay_id=" + prepay_id
				+ ", mweb_url=" + mweb_url + ", attach=" + attach + ", body=" + body + ", total_fee=" + total_fee
				+ ", spbill_create_ip=" + spbill_create_ip + ", code=" + code + "]";
	}

}
