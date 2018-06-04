package com.hoyatod.appletclient.interfaces;

import java.util.Map;

import com.hoyatod.appletclient.protocol.WechatAppletRequestData;
import com.hoyatod.appletclient.protocol.WechatData;
import com.hoyatod.appletclient.protocol.WechatScanCodeRequestData;

public interface IWechatPayInterface {
	
	/**
	 * 	微信小程序统一下单接口(只针对小程序下单)	
	 * 	
	 *  @param wechatAppletRequestData (请求数据对象) body 商品描述(中文) attach(附加描述数据) total_fee(金额) spbill_create_ip(终端IP)
	 *  @return Map<String, Object> key:returnState或wechatPayResponseData -1-授权失败(用户授权失败),0-下单成功(returnState==0时，才有key:wechatPayResponseData) ,1-下单失败,2-数据异常,或者不匹配(余额不足，商户号不正确，appid不正确等等..),3-签名有误，或者参数不合法,4-参数不合法,5-金额有误,9-系统异常
	 */
	public Map<String, Object> wechatAppletUnifiedOrder(WechatAppletRequestData wechatAppletRequestData);
	
	/**
	 * 	微信H5扫码统一下单接口	
	 * 
	 *  @param wechatAppletRequestData (请求数据对象) body 商品描述(中文) total_fee(金额) spbill_create_ip(终端IP)
	 *  @return Map<String, Object>key:returnState或code_url -1-授权失败(用户授权失败),0-下单成功(returnState==0时，才有key:code_url) ,1-下单失败,2-数据异常,或者不匹配(余额不足，商户号不正确，appid不正确等等..),3-签名有误，或者参数不合法,4-参数不合法,5-金额有误,9-系统异常
	 */
	public Map<String, Object> wechatScanCodeUnifiedOrder(WechatScanCodeRequestData wechatScanCodeRequestData);
	
	/**
	 * 	网页版H5支付	
	 * 
	 *  @param wechatData
	 * 	@return Map<String, Object>key:returnState或code_url -1-授权失败(用户授权失败),0-下单成功(returnState==0时，才有key:wechatData) ,1-下单失败,2-数据异常,或者不匹配(余额不足，商户号不正确，appid不正确等等..),3-签名有误，或者参数不合法,4-参数不合法,5-金额有误,9-系统异常
	 */
	public Map<String, Object> wechatWebpageUnifiedOrder(WechatData wechatData);
}
