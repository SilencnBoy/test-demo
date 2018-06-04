package com.hoyatod.appletclient.protocol;

import java.io.Serializable;


public class WechatAppletRequestData implements Serializable {

	private static final long serialVersionUID = 1L;

	private String appid; // 小程序ID 是 String(32) wxd678efh567hg6787 微信分配的小程序ID
	private String mch_id; // 商户号 是 String(32) 1230000109 微信支付分配的商户号
	private String device_info; // 设备号 否 String(32) 013467007045764终端设备号(门店号或收银设备ID)，注意：PC网页或公众号内支付请传"WEB"
	private String nonce_str; // 随机字符串 是 String(32) 5K8264ILTKCH16CQ2502SI8ZNMTM67VS  随机字符串，不长于32位。推荐随机数生成算法
	
	private String sign; // 签名 是 String(32)
	private String sign_type; // 签名类型 否 String(32) HMAC-SHA256 持HMAC-SHA256和MD5，默认为MD5
	
	private String body; // 商品描述 是 String(128) 腾讯充值中心-QQ会员充值  商品简单描述，该字段须严格按照规范传递，具体请见参数规定
	private String detail; // 商品详情 否 String(6000) 单品优惠字段(暂未上线)
	private String attach; // 附加数据 否 String(127) 深圳分店  附加数据，在查询API和支付通知中原样返回，该字段主要用于商户携带订单的自定义数据
	private String out_trade_no; // 商户订单号 是 String(32) 20150806125346 商户系统内部的订单号,32个字符内、可包含字母, 其他说明见商户订单号
	
	private String fee_type; // 货币类型 否 String(16) CNY 符合ISO 4217标准的三位字母代码，默认人民币：CNY，其他值列表详见货币类型
	private int total_fee; // 总金额 是 Int 888 订单总金额，单位为分，详见支付金额
	
	private String spbill_create_ip; // 终端IP 是 String(16) APP和网页支付提交用户端ip，Native支付填调用微信支付API的机器IP。
	private String time_start; // 交易起始时间 否 String(14) 20091225091010  订单生成时间，格式为yyyyMMddHHmmss，如2009年12月25日9点10分10秒表示为20091225091010。其他详见时间规则
	private String time_expire; // 交易结束时间 否 String(14) 20091227091010订单失效时间，格式为yyyyMMddHHmmss，如2009年12月27日9点10分10秒表示为20091227091010。其他详见时间规则 注意：最短失效时间间隔必须大于5分钟
	private String goods_tag; // 商品标记 否 String(32) WXG  商品标记，代金券或立减优惠功能的参数，说明详见代金券或立减优惠
	private String notify_url; // 通知地址 是 String(256) 接收微信支付异步通知回调地址，通知url必须为直接可访问的url，不能携带参数。
	private String trade_type; // 交易类型 是 String(16) JSAPI 小程序取值如下：JSAPI，详细说明见参数规定
	private String limit_pay; // 指定支付方式 否 String(32) no_credit  no_credit--指定不能使用信用卡支付
	private String openid; // 是 此参数必传，用户在商户appid下的唯一标识。openid如何获取
	
	private String api_key;// 是 商户API密钥 自行去商户平台设置
	private String code;//用户允许登录后，回调内容会带上 code（有效期五分钟），开发者需要将 code 发送到开发者服务器后台，使用code 换取 session_key api，将 code 换成 openid 和 session_key
	
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

	public String getSign_type() {
		return sign_type;
	}

	public void setSign_type(String sign_type) {
		this.sign_type = sign_type;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	public String getAttach() {
		return attach;
	}

	public void setAttach(String attach) {
		this.attach = attach;
	}

	public String getOut_trade_no() {
		return out_trade_no;
	}

	public void setOut_trade_no(String out_trade_no) {
		this.out_trade_no = out_trade_no;
	}

	public String getFee_type() {
		return fee_type;
	}

	public void setFee_type(String fee_type) {
		this.fee_type = fee_type;
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

	public String getTime_start() {
		return time_start;
	}

	public void setTime_start(String time_start) {
		this.time_start = time_start;
	}

	public String getTime_expire() {
		return time_expire;
	}

	public void setTime_expire(String time_expire) {
		this.time_expire = time_expire;
	}

	public String getGoods_tag() {
		return goods_tag;
	}

	public void setGoods_tag(String goods_tag) {
		this.goods_tag = goods_tag;
	}

	public String getNotify_url() {
		return notify_url;
	}

	public void setNotify_url(String notify_url) {
		this.notify_url = notify_url;
	}

	public String getTrade_type() {
		return trade_type;
	}

	public void setTrade_type(String trade_type) {
		this.trade_type = trade_type;
	}

	public String getLimit_pay() {
		return limit_pay;
	}

	public void setLimit_pay(String limit_pay) {
		this.limit_pay = limit_pay;
	}

	public String getOpenid() {
		return openid;
	}

	public void setOpenid(String openid) {
		this.openid = openid;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getApi_key() {
		return api_key;
	}

	public void setApi_key(String api_key) {
		this.api_key = api_key;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	@Override
	public String toString() {
		return "WechatAppletRequestData [appid=" + appid + ", mch_id=" + mch_id + ", device_info=" + device_info
				+ ", nonce_str=" + nonce_str + ", sign=" + sign + ", sign_type=" + sign_type + ", body=" + body
				+ ", detail=" + detail + ", attach=" + attach + ", out_trade_no=" + out_trade_no + ", fee_type="
				+ fee_type + ", total_fee=" + total_fee + ", spbill_create_ip=" + spbill_create_ip + ", time_start="
				+ time_start + ", time_expire=" + time_expire + ", goods_tag=" + goods_tag + ", notify_url="
				+ notify_url + ", trade_type=" + trade_type + ", limit_pay=" + limit_pay + ", openid=" + openid
				+ ", api_key=" + api_key + ", code=" + code + "]";
	}
	
}
